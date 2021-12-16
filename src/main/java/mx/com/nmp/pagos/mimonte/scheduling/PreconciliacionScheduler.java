/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusProcesoPreconciliacionRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestRangoFechasDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @name PreconciliacionScheduler
 * @description Componente para la calendarización del proceso de pre-conciliación que sirve para automatizar la ejecución  del proceso.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 29/11/2021 10:48 hrs.
 * @version 0.1
 */

@Configuration
@EnableScheduling
public class PreconciliacionScheduler implements SchedulingConfigurer {

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
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	private MailServiceConstants mc;

	@Autowired
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	@Autowired
	private DiaInhabilService diaInhabilService;

	@Autowired
	private EjecucionPreconciliacionService ejecucionPreconciliacionService;

	@Autowired
	private BusProcesoPreconciliacionRestService busProcesoPreconciliacionRestService;

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
	 Ejecuta tareas de programadas.
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		Runnable runnableTask = () ->  lanzarPreconciliacionAutomatizada();

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpressions = obtenerCalendarizacionPreconciliacion().getConfiguracionAutomatizacion();
				if (StringUtils.isEmpty(cronExpressions)) {
					return null;
				}
				CronTrigger crontrigger = new CronTrigger(cronExpressions);
				return crontrigger.nextExecutionTime(triggerContext);
			}
		};

		taskRegistrar.addTriggerTask(runnableTask , trigger);

	}

	public void lanzarPreconciliacionAutomatizada() {

        CalendarioEjecucionProcesoDTO calendarizacion = this.obtenerCalendarizacionPreconciliacion();
        EjecucionPreconciliacion ejecucionPreconciliacion  = this.crearEjecucionPreconciliacion(calendarizacion);
        if(this.validarFechaInhabil(ejecucionPreconciliacion.getFechaPeriodoInicio())) {
			if(this.validarDuplicidadProceso(ejecucionPreconciliacion)) {
				this.ejecutarProcesoPreconciliacion(calendarizacion, ejecucionPreconciliacion);
			}
		}

	}

	public void ejecutarProcesoPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion, EjecucionPreconciliacion ejecucionPreconciliacion) {
		for (int i = 0; i <= calendarizacion.getReintentos(); i++) {
			BusRestPreconciliacionDTO request = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(ejecucionPreconciliacion.getFechaPeriodoInicio(), ejecucionPreconciliacion.getFechaPeriodoFin()));
			//ProcesoPreconciliacionResponseDTO response = busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(request);
            ProcesoPreconciliacionResponseDTO response = new ProcesoPreconciliacionResponseDTO("Test", "Test", false);
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

		EjecucionPreconciliacion ejecucionPreconciliacionSave= ejecucionPreconciliacionService.save(ejecucionPreconciliacion, "Sistema");

	}

	public  void enviarNotificacionEjecucionErronea(EjecucionPreconciliacion ejecucionPreconciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		// Se obtienen los contactos del proceso de pre-conciliación
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_PRECONCILIACION);
		if (null == contactos || contactos.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_043);
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

	public BusRestMailDTO construyeEMailProcesoPreconciliacion(List<Contactos> contactos, EjecucionPreconciliacion ejecucionPreconciliacion) {

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getVelocityTemplate();

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elemento", ejecucionPreconciliacion);
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

	private EjecucionPreconciliacion crearEjecucionPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = new Date();
		Calendar calendarEjecucion = Calendar.getInstance();
		calendarEjecucion.setTime(fechaActual);
		calendarEjecucion.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCobertura());
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();

		ini.setTime( calendarEjecucion.getTime());
		fin.setTime( calendarEjecucion.getTime());
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

	public boolean validarDuplicidadProceso(EjecucionPreconciliacion ejecucionPreconciliacion) {
		FiltroEjecucionPreconciliacionDTO filtro = new FiltroEjecucionPreconciliacionDTO();
		filtro.setFechaPeriodoInicio(ejecucionPreconciliacion.getFechaPeriodoInicio());
		filtro.setFechaPeriodoFin(ejecucionPreconciliacion.getFechaPeriodoFin());
		filtro.setCorresponsal(ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		filtro.setIdEstatus(2);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			return false;
		}
		return true;
	}

	public boolean validarFechaInhabil(Date fecha ) {
		CatalogoDiaInhabil diaInhabil = diaInhabilService.buscarByFecha(fecha);
		boolean validacion = (diaInhabil != null);
		return !validacion;
	}

	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionPreconciliacion() {
		CalendarioEjecucionProcesoDTO calendarioEjecucionProceso = new CalendarioEjecucionProcesoDTO();
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(1);
		filtro.setCorresponsal(CorresponsalEnum.OPENPAY.getNombre());
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			calendarioEjecucionProceso =  listaResultados.get(0);
		}
		return calendarioEjecucionProceso;
	}

}
