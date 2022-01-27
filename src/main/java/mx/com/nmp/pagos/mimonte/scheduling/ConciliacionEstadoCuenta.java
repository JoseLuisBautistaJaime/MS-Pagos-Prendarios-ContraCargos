/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.conector.MergeConciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosEstadoCuentaBroker;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionEtapa2DTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MergeConciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Nombre: ConciliacionEstadoCuenta Descripcion: Clase que proporciona los métodos para
 * consultar y cargar los movimientos del estado de cuenta.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 19/01/2022 15:18 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionEstadoCuenta {

	/**
	 * Conector encargado de conciliar los movimientos del estado de cuenta.
	 */
	@Inject
	private  MovimientosEstadoCuentaBroker movimientosEstadoCuentaBroker;

	/**
	 * Conector encargado de conciliar los movimientos cargados.
	 */
	@Inject
	private  MergeConciliacionBroker mergeConciliacionBroker;

	/**
	 * los métodos comunes en la ejecución del proceso de conciliación
	 */
	@Autowired
	private ConciliacionCommon conciliacionCommon;

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	private  VelocityEngine velocityEngine;

	/**
	 * Contiene las propiedades del sistema
	 */
	@Autowired
	private  ApplicationProperties applicationProperties;

	/**
	 * Repository de contactos
	 */
	@Autowired
	private  ContactoRespository contactoRespository;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	private  BusMailRestService busMailRestService;

	/**
	 * Servicios para gestionar la información del proceso de conciliación
	 */
	@Autowired
	private  ConciliacionService conciliacionService;


	private ConciliacionEstadoCuenta() {
		super();
	}


	/**
	 * Método que busca los procesos de conciliación que no se encuentren con el estado de cuenta conciliado.
	 * @param calendarizacion
	 * @return
	 */
	public Conciliacion buscarConciliacionSinEstadoCuenta(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = new Date();
		com.ibm.icu.util.Calendar calendarEjecucionInicial = com.ibm.icu.util.Calendar.getInstance();
		com.ibm.icu.util.Calendar calendarEjecucionFin = com.ibm.icu.util.Calendar.getInstance();
		com.ibm.icu.util.Calendar ini = com.ibm.icu.util.Calendar.getInstance();
		com.ibm.icu.util.Calendar fin = com.ibm.icu.util.Calendar.getInstance();

		calendarEjecucionInicial.setTime(fechaActual);
		calendarEjecucionFin.setTime(fechaActual);
		calendarEjecucionInicial.add(com.ibm.icu.util.Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMin());
		calendarEjecucionFin.add(com.ibm.icu.util.Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMax());

		ini.setTime( calendarEjecucionInicial.getTime());
		fin.setTime( calendarEjecucionFin.getTime());
		ini.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY, 0);
		ini.set(com.ibm.icu.util.Calendar.MINUTE, 0);
		ini.set(com.ibm.icu.util.Calendar.SECOND, 0);
		ini.set(com.ibm.icu.util.Calendar.MILLISECOND, 0);
		fin.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY, 23);
		fin.set(com.ibm.icu.util.Calendar.MINUTE, 59);
		fin.set(com.ibm.icu.util.Calendar.SECOND, 59);
		fin.set(Calendar.MILLISECOND, 59);

		ConsultaConciliacionEtapa2DTO filtro = new ConsultaConciliacionEtapa2DTO();
		filtro.setFechaDesde(ini.getTime());
		filtro.setFechaHasta(fin.getTime());
		filtro.setListaSubEstatus(ConciliacionConstants.CON_SUB_ESTATUS_CONCILIACION_SIN_ESTADO_CUENTA);
		filtro.setCorresponsal(calendarizacion.getCorresponsal().getNombre());

		return conciliacionService.getConciliacionSinEstadoCuenta(filtro);

	}

	/**
	 * Método que ejecuta el proceso de conciliación etapa 2 - carga de movimientos del estado de cuenta.
	 * @param ejecucionConciliacion
	 *
	 */
	public void ejecutarProcesoConciliacionE2( EjecucionConciliacion ejecucionConciliacion){
		String descripcionEstatusFase="";
		Date inicioFase = new Date();
		Date finFase = null;
		Boolean flgEjecucionCorrecta = true;

		try {
			inicioFase= new Date();
			MovimientosEstadoCuentaResponseDTO response = movimientosEstadoCuentaBroker.cargarMovimientosEstadoCuenta(ejecucionConciliacion.getConciliacion().getId(),new Date(), new Date() );
			finFase = new Date();
			flgEjecucionCorrecta = response.getCargaCorrecta();
			descripcionEstatusFase = response.getMessage();
		} catch (Exception ex) {
			ex.printStackTrace();
			finFase = new Date();
			descripcionEstatusFase = ex.getMessage();
			flgEjecucionCorrecta = false;
		}

		if(flgEjecucionCorrecta) {

			Conciliacion resultadoEjecucion= conciliacionCommon.escucharSubEstatusConciliacion(ejecucionConciliacion.getConciliacion().getId(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_EC);

			if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA){
				flgEjecucionCorrecta= true;
				descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Consulta de los movimientos del estado de cuenta completada.";
				ejecucionConciliacion.setConciliacion(resultadoEjecucion);
			} else{
				flgEjecucionCorrecta= false;
				descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Falló la consulta de los movimientos del estado de cuenta.";
			}
		}

		ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_ESTADO_CUENTA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_ESTADO_CUENTA.getEstadoEjecucion()));
		conciliacionCommon.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

		if(flgEjecucionCorrecta){
			try {
				inicioFase= new Date();
				MergeConciliacionResponseDTO response = mergeConciliacionBroker.generarMergeConciliacion(ejecucionConciliacion.getConciliacion().getId());
				finFase = new Date();
				flgEjecucionCorrecta = response.getCargaCorrecta();
				descripcionEstatusFase = response.getMessage();
			} catch (Exception ex) {
				ex.printStackTrace();
				finFase = new Date();
				descripcionEstatusFase = ex.getMessage();
				flgEjecucionCorrecta = false;
			}

			if(flgEjecucionCorrecta) {

				Conciliacion resultadoEjecucion= conciliacionCommon.escucharSubEstatusConciliacion(ejecucionConciliacion.getConciliacion().getId(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_MERGE_CONCILIACION);

				if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA){
					flgEjecucionCorrecta= true;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Conciliación de los movimientos nocturnos y del proveedor completada.";
					ejecucionConciliacion.setConciliacion(resultadoEjecucion);
				} else{
					flgEjecucionCorrecta= false;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Falló la conciliación de los movimientos nocturnos y del proveedor.";
				}
			}

			ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR_ESTADOCUENTA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR_ESTADOCUENTA.getEstadoEjecucion()));
			conciliacionCommon.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

		}

		if(!flgEjecucionCorrecta){
			enviarNotificacionEjecucionErronea(ejecucionConciliacion);
		}

	}

	/**
	 * Método que envia las notificación vía correo electrónico  cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param ejecucionConciliacion
	 *
	 */
	public  void enviarNotificacionEjecucionErronea(EjecucionConciliacion ejecucionConciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		// Se obtienen los contactos del proceso de pre-conciliación
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_CONCILIACION);
		if (null == contactos || contactos.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_151);
		}

		// Construye e-mail
		try {
			generalBusMailDTO = construyeEMailProcesoConciliacion( contactos, ejecucionConciliacion);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,	CodigoError.NMP_PMIMONTE_BUSINESS_152);
		}
		try {
			// Envia e-mail
			busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_153);
		}

	}

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionConciliacion
	 *
	 */
	public  BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion) {

		BusRestMovimientosEstadoCuentaDTO request = new BusRestMovimientosEstadoCuentaDTO(ejecucionConciliacion.getConciliacion().getId(), new Date(), new Date());

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa2().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa2().getVelocityTemplate();

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
	
}
