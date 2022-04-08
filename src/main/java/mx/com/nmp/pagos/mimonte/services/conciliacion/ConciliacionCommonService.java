/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TrazadoEjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.util.FechasUtil;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

/**
 * Nombre: ConciliacionCommonService Descripción: Clase que proporciona los métodos comunes en
 * la ejecución del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 19/01/2022 15:18 hrs.
 * @version 0.1
 */

public abstract class ConciliacionCommonService {

	/**
	 * Instancia para impresion de LOG's
	 */
	public static final Logger logger = LoggerFactory.getLogger(ConciliacionCommonService.class);

	/**
	 * Mensaje al generarse un error
	 */
	public static final String MSG_ERROR = "Error: ";


	/**
	 * Servicios para gestionar la  calendarización de los procesos automatizados
	 */
	@Autowired
	public CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	/**
	 * Servicios para gestionar la información de la ejecución del proceso de conciliación
	 */
	@Autowired
	public EjecucionConciliacionService ejecucionConciliacionService;

	/**
	 * Servicios para gestionar la información del proceso de conciliación
	 */
	@Autowired
	public ConciliacionService conciliacionService;

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	public VelocityEngine velocityEngine;

	/**
	 * Contiene las propiedades del sistema
	 */
	@Autowired
	public ApplicationProperties applicationProperties;

	/**
	 * Repository de contactos
	 */
	@Autowired
	public ContactoRespository contactoRespository;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	public BusMailRestService busMailRestService;


	public ConciliacionCommonService() {
		super();
	}


	/**
	 * Método encargado de consultar las configuraciones de las calendarizaciones de los procesos automatizados.
	 * @param idProceso
	 * @param corresponsal
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO> obtenerCalendarizacionConciliacion(Integer idProceso, String corresponsal) {
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(idProceso);
		filtro.setCorresponsal(corresponsal);
		return calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
	}

	/**
	 * Método encargado de consultar la ejecución del proceso de conciliación apartir de la conciliación.
	 * @param conciliacion
	 * @return
	 */
	public EjecucionConciliacion buscarEjecucionConciliacion(Conciliacion conciliacion) {
		EjecucionConciliacion ejecucionConciliacion;
		try{
			ejecucionConciliacion= ejecucionConciliacionService.consultarByIdConciliacion(conciliacion.getId());
		} catch (ConciliacionException ex) {
			return null;
		}
		return ejecucionConciliacion;
	}


	/**
	 * Método encargado de consultar la ejecución del proceso de conciliación apartir de la conciliación.
	 * @param idConciliacion
	 * @param listaEstados
	 * @return
	 */
	public Conciliacion escucharSubEstatusConciliacion(Long idConciliacion, List<Long> listaEstados) {
		Conciliacion resultado = null;
		for (int i = 0; i <= 3; i++) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			try {
				resultado =conciliacionService.getById(idConciliacion);
			} catch (Exception ex) {
				continue;
			}
			if(resultado != null && listaEstados.contains(resultado.getSubEstatus().getId())){
				return resultado;
			}
		}
		return resultado;
	}

	/**
	 * Método encargado de generar el trazado de estatus de la ejecución del proceso de conciliación.
	 * @param ejecucionConciliacion
	 * @param inicioFase
	 * @param finFase
	 * @param descripcionEstatusFase
	 * @return
	 */
	public void  generarTrazadoEjecucionFase(EjecucionConciliacion ejecucionConciliacion, Date inicioFase, Date finFase, String descripcionEstatusFase) {
		TrazadoEjecucionConciliacion trazadoEjecucion = new TrazadoEjecucionConciliacion();
		trazadoEjecucion.setEjecucionConciliacion(ejecucionConciliacion);
		trazadoEjecucion.setFechaInicio(inicioFase);
		trazadoEjecucion.setFechaFin(finFase);
		trazadoEjecucion.setEstatus(ejecucionConciliacion.getEstatus());
		trazadoEjecucion.setEstatusDescripcion(descripcionEstatusFase.length() > 499 ? descripcionEstatusFase.substring(0,499) : descripcionEstatusFase );
		ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucion,"sistema");
	}

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionConciliacion
	 *
	 */
	public abstract BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion);

	/**
	 * Método que envia las notificación vía correo electrónico  cuando el proceso automatizado falla u obtiene un resultado erroneo.
	 * @param ejecucionConciliacion
	 *
	 */
	public  void enviarNotificacionEjecucionErronea(EjecucionConciliacion ejecucionConciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		// Se obtienen los contactos del proceso de conciliación
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_CONCILIACION);
		if (null == contactos || contactos.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_151);
		}

		// Construye e-mail
		try {
			generalBusMailDTO = construyeEMailProcesoConciliacion( contactos, ejecucionConciliacion);
		} catch (Exception ex) {
			logger.error(MSG_ERROR, ex);
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,	CodigoError.NMP_PMIMONTE_BUSINESS_152);
		}
		try {
			// Envia e-mail
			busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			logger.error(MSG_ERROR, ex);
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_153);
		}

	}

	/**
	 * Método que obtiene la fecha actual de acuerdo a la configuracion de zona horaria que se configure.
	 *
	 */
	public Date obtenerFechaActual() {
		String zonaHoraria = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getZonaHoraria():null;
		return FechasUtil.obtenerFechaZonaHorario(zonaHoraria);
	}

	public Date calcularFechaInicial(Date fechaActual, Integer rangoDiasResta) {
		Calendar calendarEjecucionInicial = Calendar.getInstance();
		calendarEjecucionInicial.setTime(fechaActual);
		calendarEjecucionInicial.add(Calendar.DAY_OF_YEAR, 0 - rangoDiasResta);
		Calendar ini = Calendar.getInstance();
		ini.setTime( calendarEjecucionInicial.getTime());
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 1);
		return ini.getTime();
	}

	protected Date calcularFechaFinal(Date fechaActual, Integer rangoDiasResta) {
		Calendar calendarEjecucionFin = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		calendarEjecucionFin.setTime(fechaActual);
		calendarEjecucionFin.add(Calendar.DAY_OF_YEAR, 0 - rangoDiasResta);
		fin.setTime( calendarEjecucionFin.getTime());
		fin.set(Calendar.MINUTE, 59);
		fin.set(Calendar.SECOND, 59);
		fin.set(Calendar.MILLISECOND, 59);
		fin.set(Calendar.HOUR_OF_DAY, 23);
		return fin.getTime();

	}

}
