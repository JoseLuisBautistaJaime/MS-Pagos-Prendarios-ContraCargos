/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.conector.PreconciliacionBroker;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.*;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;


/**
 * @name PreconciliacionScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de pre-conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 29/11/2021 10:48 hrs.
 * @version 0.1
 */

@Component
public class PreconciliacionScheduler {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger LOG = LoggerFactory.getLogger(PreconciliacionScheduler.class);

	/**
	 * Conector encargado de ejecutar el proceso de pre-conciliación.
	 */
	@Inject
	private PreconciliacionBroker preconciliacionBrokerBus;

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * Contiene las propiedades del sistema
	 */
	@Autowired
	private ApplicationProperties applicationProperties;

	/**
	 * Servicios para gestionar la  calendarización de los procesos automatizados
	 */
	@Autowired
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	/**
	 * Servicios para gestionar el catalogo de días inhábiles.
	 */
	@Autowired
	private DiaInhabilService diaInhabilService;

	/**
	 * Servicios para gestionar la información de la ejecución del proceso de pre-conciliación
	 */
	@Autowired
	private EjecucionPreconciliacionService ejecucionPreconciliacionService;

	/**
	 * Repository de contactos
	 */
	@Autowired
	private ContactoRespository contactoRespository;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	private BusMailRestService busMailRestService;

	/**
	 * Programa la ejecución de las  tareas automatizadas.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		createSchedulers();
	}

	/**
	 * Ejecuta tareas de programadas.
	 */
	private void createSchedulers(){

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = obtenerCalendarizacionPreconciliacion();

		for (CalendarioEjecucionProcesoDTO configuracion : listaConfiguraciones) {
			String cronExpressions = configuracion.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				scheduler.schedule( () -> lanzarPreconciliacionAutomatizada(configuracion), new CronTrigger(cronExpressions));
			}
		}

	}

	/**
	 * Método encargado de lanzar la ejecución del proceso de pre-conciliación.
	 *
	 */
	public void lanzarPreconciliacionAutomatizada(CalendarioEjecucionProcesoDTO calendarizacion) {
        EjecucionPreconciliacion ejecucionPreconciliacion  = this.crearEjecucionPreconciliacion(calendarizacion);
        if(this.validarFechaInhabil(ejecucionPreconciliacion.getFechaPeriodoInicio())) {
			if(this.validarDuplicidadProceso(ejecucionPreconciliacion)) {
				this.ejecutarProcesoPreconciliacion(calendarizacion, ejecucionPreconciliacion);
			}
		}
	}

	/**
	 * Método que ejecuta el proceso de pre-conciliación.
	 * @param calendarizacion
	 * @param ejecucionPreconciliacion
	 *
	 */
	public void ejecutarProcesoPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion, EjecucionPreconciliacion ejecucionPreconciliacion) {
		for (int i = 0; i <= calendarizacion.getReintentos(); i++) {
			ProcesoPreconciliacionResponseDTO response = preconciliacionBrokerBus.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(), ejecucionPreconciliacion.getFechaPeriodoFin(), ejecucionPreconciliacion.getProveedor().getNombre().getNombre());

			if (response.getEjecucionCorrecta()) {
				ejecucionPreconciliacion.getEstatus().setId(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion());
				ejecucionPreconciliacion.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());
				break;
			}else{
				if(i == calendarizacion.getReintentos()) {
					ejecucionPreconciliacion.getEstatus().setId(EstatusEjecucionPreconciliacionEnum.DESCARGAINCORRECTA.getIdEstadoEjecucion());
					ejecucionPreconciliacion.setEstatusDescripcion(response.getMensaje());
				}
				continue;
			}
		}

		if(ejecucionPreconciliacion.getEstatus().getId() == EstatusEjecucionPreconciliacionEnum.DESCARGAINCORRECTA.getIdEstadoEjecucion()){
			this.enviarNotificacionEjecucionErronea(ejecucionPreconciliacion);
		}

		EjecucionPreconciliacion ejecucionPreconciliacionSave= ejecucionPreconciliacionService.save(ejecucionPreconciliacion, "sistema");

	}

	/**
	 * Método que envia las notificación vía correo electrónico  cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param ejecucionPreconciliacion
	 *
	 */
	public  void enviarNotificacionEjecucionErronea(EjecucionPreconciliacion ejecucionPreconciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		// Se obtienen los contactos del proceso de pre-conciliación
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_PRECONCILIACION);
		if (null == contactos || contactos.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_150);
		}

		// Construye e-mail
		try {
			generalBusMailDTO = construyeEMailProcesoPreconciliacion( contactos, ejecucionPreconciliacion);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,	CodigoError.NMP_PMIMONTE_BUSINESS_146);
		}
		try {
			// Envia e-mail
			busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_147);
		}

	}

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionPreconciliacion
	 *
	 */
	public BusRestMailDTO construyeEMailProcesoPreconciliacion(List<Contactos> contactos, EjecucionPreconciliacion ejecucionPreconciliacion) {

		BusRestPreconciliacionDTO request = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(ejecucionPreconciliacion.getFechaPeriodoInicio(), ejecucionPreconciliacion.getFechaPeriodoFin()), new BusRestCorresponsalDTO(ejecucionPreconciliacion.getProveedor().getNombre().getNombre()));

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getVelocityTemplate();

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elemento", request);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAdjuntos(new BusRestAdjuntoDTO(new ArrayList<>()));
		mailDTO.setAsunto(titulo);
		mailDTO.setContenidoHTML(htmlMail);
		mailDTO.setDe(remitente);
		mailDTO.setPara(destinatarios);

		return mailDTO;
	}

	/**
	 * Método encargado de construir la ejecución del proceso de pre-conciliación.
	 * @param calendarizacion
	 * @return
	 *
	 */
	private EjecucionPreconciliacion crearEjecucionPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = new Date();
		Calendar calendarEjecucionInicial = Calendar.getInstance();
		Calendar calendarEjecucionFin = Calendar.getInstance();
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();

		calendarEjecucionInicial.setTime(fechaActual);
		calendarEjecucionFin.setTime(fechaActual);

		calendarEjecucionInicial.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMin());
		calendarEjecucionInicial.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMax());

		ini.setTime( calendarEjecucionInicial.getTime());
		fin.setTime( calendarEjecucionFin.getTime());
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 0);
		fin.set(Calendar.HOUR_OF_DAY, 23);
		fin.set(Calendar.MINUTE, 59);
		fin.set(Calendar.SECOND, 59);
		fin.set(Calendar.MILLISECOND, 59);

		EjecucionPreconciliacion ejecucion =  new EjecucionPreconciliacion();
		ejecucion.setFechaEjecucion(new Date());
		ejecucion.setFechaPeriodoInicio(ini.getTime());
		ejecucion.setFechaPeriodoFin(fin.getTime());
		ejecucion.setProveedor(new Proveedor(calendarizacion.getCorresponsal()));
		ejecucion.setEstatus(new EstatusEjecucionPreconciliacion(EstatusEjecucionPreconciliacionEnum.SOLICITUD.getIdEstadoEjecucion()));
		ejecucion.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.SOLICITUD.getEstadoEjecucion());

		return ejecucion;

	}

	/**
	 * Método que valida que la ejecución del proceso de pre-conciliación no se duplique.
	 * @param ejecucionPreconciliacion
	 * @return
	 *
	 */
	public boolean validarDuplicidadProceso(EjecucionPreconciliacion ejecucionPreconciliacion) {
		FiltroEjecucionPreconciliacionDTO filtro = new FiltroEjecucionPreconciliacionDTO();
		filtro.setFechaPeriodoInicio(ejecucionPreconciliacion.getFechaPeriodoInicio());
		filtro.setFechaPeriodoFin(ejecucionPreconciliacion.getFechaPeriodoFin());
		filtro.setCorresponsal(ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		filtro.setIdEstatus(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion());
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			return false;
		}
		return true;
	}

	/**
	 * Método que valida que la fecha de ejecución no corresponda a un día inhábil.
	 * @param fecha
	 * @return
	 *
	 */
	public boolean validarFechaInhabil(Date fecha ) {
		CatalogoDiaInhabil diaInhabil = diaInhabilService.buscarByFecha(fecha);
		boolean validacion = (diaInhabil != null);
		return !validacion;
	}

	/**
	 * Método encargado de consultar las configuraciones de la calendarizacion del proceso de pre-conciliación.
	 * @return
	 *
	 */
	public List<CalendarioEjecucionProcesoDTO> obtenerCalendarizacionPreconciliacion() {
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(ProcesoEnum.PRE_CONCILIACION.getIdProceso());
		filtro.setCorresponsal(CorresponsalEnum.OPENPAY.getNombre());
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
		return listaResultados;
	}

}
