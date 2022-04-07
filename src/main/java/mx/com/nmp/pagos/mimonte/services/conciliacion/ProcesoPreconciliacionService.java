/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.conector.*;
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
import mx.com.nmp.pagos.mimonte.util.FechasUtil;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Nombre: ProcesoPreconciliacionService Descripcion: Clase que proporciona los métodos para
 * ejecutar el proceso de pre-conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 21/01/2022 15:18 hrs.
 * @version 0.1
 */
@Component
public class ProcesoPreconciliacionService {

	/**
	 * Instancia para impresion de LOG's
	 */
	public static final Logger logger = LoggerFactory.getLogger(ProcesoPreconciliacionService.class);

	/**
	 * Mensaje al generarse un error
	 */
	public static final String MSG_ERROR = "Error: ";

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
	 * Método que ejecuta el proceso de pre-conciliación.
	 * @param calendarizacion
	 * @param ejecucionPreconciliacion
	 *
	 */
	public void ejecutarProcesoPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion, EjecucionPreconciliacion ejecucionPreconciliacion) {
		try {
			for (int i = 0; i <= calendarizacion.getReintentos(); i++) {

				ProcesoPreconciliacionResponseDTO response = preconciliacionBrokerBus.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(), ejecucionPreconciliacion.getFechaPeriodoFin(), ejecucionPreconciliacion.getProveedor().getNombre().getNombre());

				if (response.getEjecucionCorrecta()) {
					ejecucionPreconciliacion.getEstatus().setId(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion());
					ejecucionPreconciliacion.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());
					break;
				} else {
					if (i == calendarizacion.getReintentos()) {
						ejecucionPreconciliacion.getEstatus().setId(EstatusEjecucionPreconciliacionEnum.DESCARGAINCORRECTA.getIdEstadoEjecucion());
						ejecucionPreconciliacion.setEstatusDescripcion(response.getMensaje().length() > 499 ? response.getMensaje().substring(0, 499) : response.getMensaje());
					}
				}

			}
		} catch (Exception ex) {
			logger.error(MSG_ERROR, ex);
			ejecucionPreconciliacion.getEstatus().setId(EstatusEjecucionPreconciliacionEnum.DESCARGAINCORRECTA.getIdEstadoEjecucion());
			ejecucionPreconciliacion.setEstatusDescripcion(ex.getMessage().length() > 499 ? ex.getMessage().substring(0, 499) : ex.getMessage());
		}

		if(ejecucionPreconciliacion.getEstatus().getId().equals(EstatusEjecucionPreconciliacionEnum.DESCARGAINCORRECTA.getIdEstadoEjecucion())){
			this.enviarNotificacionEjecucionErronea(ejecucionPreconciliacion);
		}

		ejecucionPreconciliacionService.save(ejecucionPreconciliacion, "sistema");

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
			logger.error(MSG_ERROR, ex);
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,	CodigoError.NMP_PMIMONTE_BUSINESS_146);
		}
		try {
			// Envia e-mail
			busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			logger.error(MSG_ERROR, ex);
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
		String titulo = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getTitle() : "";
		String remitente = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getFrom() : "";
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionPreconciliacion().getVelocityTemplate() : "";

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elemento", request);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAdjuntos(new BusRestAdjuntoDTO(new ArrayList<>()));
		mailDTO.setDe(remitente);
		mailDTO.setAsunto(titulo);
		mailDTO.setPara(destinatarios);
		mailDTO.setContenidoHTML(htmlMail);

		return mailDTO;
	}

	/**
	 * Método encargado de construir la ejecución del proceso de pre-conciliación.
	 * @param calendarizacion
	 * @return
	 *
	 */
	public EjecucionPreconciliacion crearEjecucionPreconciliacion(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = obtenerFechaActual();

		Calendar calendarEjecucionInicial = Calendar.getInstance();
		Calendar ini = Calendar.getInstance();
		calendarEjecucionInicial.setTime(fechaActual);
		calendarEjecucionInicial.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMin());
		ini.setTime( calendarEjecucionInicial.getTime());
		ini.set(Calendar.MILLISECOND, 1);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.HOUR_OF_DAY, 0);

		Calendar calendarEjecucionFin = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		calendarEjecucionFin.setTime(fechaActual);
		calendarEjecucionFin.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMax());
		fin.setTime( calendarEjecucionFin.getTime());
		fin.set(Calendar.MILLISECOND, 59);
		fin.set(Calendar.SECOND, 59);
		fin.set(Calendar.MINUTE, 59);
		fin.set(Calendar.HOUR_OF_DAY, 23);


		EjecucionPreconciliacion ejecucion =  new EjecucionPreconciliacion();
		ejecucion.setFechaEjecucion(obtenerFechaActual());
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
		Calendar ini = Calendar.getInstance();
		ini.setTime( ejecucionPreconciliacion.getFechaPeriodoInicio() );
		ini.set(Calendar.MILLISECOND, 0);
		Calendar fin = Calendar.getInstance();
		fin.setTime( ejecucionPreconciliacion.getFechaPeriodoFin() );
		fin.set(Calendar.MILLISECOND, 0);
		filtro.setFechaPeriodoInicio(ini.getTime());
		filtro.setFechaPeriodoFin(fin.getTime());
		filtro.setCorresponsal(ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		filtro.setIdEstatus(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion());
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtro);
		return !(listaResultados != null && !listaResultados.isEmpty());
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
	 * Método que obtiene la fecha actual de acuerdo a la configuracion de zona horaria que se configure.
	 *
	 */
	public Date obtenerFechaActual() {
		String zonaHoraria = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getZonaHoraria() : "";
		return FechasUtil.obtenerFechaZonaHorario(zonaHoraria);
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
		return calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
	}

}
