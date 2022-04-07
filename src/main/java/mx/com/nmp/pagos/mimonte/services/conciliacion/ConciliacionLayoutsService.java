/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.conector.GeneracionLayoutBroker;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Nombre: ConciliacionLayoutsService Descripcion: Clase que proporciona los métodos para
 * generar y enviar los layouts de pagos, comisiones y devoluciones.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 01/02/2022 12:18 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionLayoutsService extends ConciliacionCommonService {

	/**
	 * Conector encargado de conciliar los movimientos del estado de cuenta.
	 */
	@Inject
	private GeneracionLayoutBroker generacionLayoutBroker;


	private ConciliacionLayoutsService() {
		super();
	}


	/**
	 * Método que busca los procesos de conciliación que no se encuentren con sus layouts generados y enviados
	 * @param calendarizacion
	 * @return
	 */
	public List<Conciliacion> buscarConciliacionSinLayouts(CalendarioEjecucionProcesoDTO calendarizacion) {
		Date fechaActual = obtenerFechaActual();
		ConsultaConciliacionEtapa3DTO filtro = new ConsultaConciliacionEtapa3DTO( this.calcularFechaInicial(fechaActual,calendarizacion.getRangoDiasCoberturaMin()), this.calcularFechaFinal(fechaActual,calendarizacion.getRangoDiasCoberturaMax()), ConciliacionConstants.CON_SUB_ESTATUS_CONCILIACION_SIN_LAYOUT, calendarizacion.getCorresponsal().getNombre());
		return conciliacionService.getConciliacionSinLayout(filtro);
	}

	/**
	 * Método que ejecuta el proceso de conciliación etapa 3 - generación y envió de los layouts.
	 * @param ejecucionConciliacion
	 *
	 */
	public void ejecutarProcesoConciliacionE3( EjecucionConciliacion ejecucionConciliacion){
		String descripcionEstatusFase="";
		Date inicioFase = obtenerFechaActual();
		Date finFase = null;
		Boolean flgEjecucionCorrecta = true;
		Boolean flgLayoutCorrectos = true;

		try {
			inicioFase= obtenerFechaActual();
			GeneracionLayoutResponseDTO response = generacionLayoutBroker.generarLayouts(ejecucionConciliacion.getConciliacion().getId(),ConciliacionConstants.GENERAR_LAYOUT );
			finFase = obtenerFechaActual();
			flgEjecucionCorrecta = response.getRespuestaCorrecta();
			descripcionEstatusFase = response.getMessage();
			if(Integer.valueOf(response.getCodigo()) == HttpStatus.BAD_REQUEST.value()){
				flgLayoutCorrectos = false;
			}
		} catch (Exception ex) {
			logger.error(MSG_ERROR, ex);
			finFase = obtenerFechaActual();
			descripcionEstatusFase = ex.getMessage();
			flgEjecucionCorrecta = false;
		}

		if(flgEjecucionCorrecta && flgLayoutCorrectos) {

			List<MontoLayoutConciliacionDTO> montosLayoutsConciliacion= conciliacionService.calcularMontosLayoutsConciliacion(ejecucionConciliacion.getConciliacion().getId());

			if(montosLayoutsConciliacion.isEmpty()){
				flgLayoutCorrectos = false;
				descripcionEstatusFase = ConciliacionConstants.ERROR_GENERACION_LAYOUT ;
			} else {
				Long montoValidacion = montosLayoutsConciliacion.stream().mapToLong(MontoLayoutConciliacionDTO :: getAcumulado).sum();
				if(montoValidacion !=  0){
					flgLayoutCorrectos = false;
					descripcionEstatusFase = ConciliacionConstants.ERROR_VALIDAR_MONTOS_LAYOUT;
				}
			}

		}

		ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.GENERAR_LAYOUTS.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.GENERAR_LAYOUTS.getEstadoEjecucion()));
		generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

		if(flgEjecucionCorrecta  && flgLayoutCorrectos){
			try {
				inicioFase= obtenerFechaActual();
				GeneracionLayoutResponseDTO response = generacionLayoutBroker.generarLayouts(ejecucionConciliacion.getConciliacion().getId(),ConciliacionConstants.ENVIAR_LAYOUT);
				finFase = obtenerFechaActual();
				flgEjecucionCorrecta = response.getRespuestaCorrecta();
				descripcionEstatusFase = response.getMessage();
			} catch (Exception ex) {
				logger.error(MSG_ERROR, ex);
				finFase = obtenerFechaActual();
				descripcionEstatusFase = ex.getMessage();
				flgEjecucionCorrecta = false;
			}

			ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.ENVIAR_LAYOUTS.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.ENVIAR_LAYOUTS.getEstadoEjecucion()));
			generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

		}

		if(!flgEjecucionCorrecta && flgLayoutCorrectos){
			enviarNotificacionEjecucionErronea(ejecucionConciliacion);
		} else if (!flgLayoutCorrectos){
			enviarNotificacionLayoutsIncorrectos(ejecucionConciliacion);
		}

	}

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionConciliacion
	 *
	 */
	@Override
	public  BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion) {

		DatosNotificacionDTO datos = new DatosNotificacionDTO(ejecucionConciliacion.getConciliacion().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getNombre(),ejecucionConciliacion.getConciliacion().getCuenta().getId(),ejecucionConciliacion.getConciliacion().getCuenta().getNumeroCuenta(), ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(), ejecucionConciliacion.getProveedor().getNombre().getNombre());

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa3Error().getTitle() : "";
		String remitente = applicationProperties != null ?applicationProperties.getMimonte().getVariables().getMail().getFrom() : "";
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties != null ?applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa3Error().getVelocityTemplate() : "";

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elementoE3", datos);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAsunto(titulo);
		mailDTO.setAdjuntos(new BusRestAdjuntoDTO(new ArrayList<>()));
		mailDTO.setContenidoHTML(htmlMail);
		mailDTO.setDe(remitente);
		mailDTO.setPara(destinatarios);

		return mailDTO;
	}


	/**
	 * Método que envia las notificación vía correo electrónico  cuando la validación de los montos acumulados de los layout generados es incorrecto.
	 * @param ejecucionConciliacion
	 *
	 */
	public  void enviarNotificacionLayoutsIncorrectos(EjecucionConciliacion ejecucionConciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_CONCILIACION);
		if (contactos != null && !contactos.isEmpty()) {
			// Construye e-mail
			try {
				generalBusMailDTO = construyeEMailLayoutsIncorrectos(contactos, ejecucionConciliacion);
			} catch (Exception ex) {
				logger.error(MSG_ERROR, ex);
				throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_152);
			}
			try {
				// Envia e-mail
				busMailRestService.enviaEmail(generalBusMailDTO);
			} catch (Exception ex) {
				logger.error(MSG_ERROR, ex);
				throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_153);
			}
		} else {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_151);
		}

	}

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando la validación de los montos acumulados de los layout generados es incorrecto.
	 * @param contactos
	 * @param ejecucionConciliacion
	 *
	 */
	public  BusRestMailDTO construyeEMailLayoutsIncorrectos(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion) {

		DatosNotificacionDTO datos = new DatosNotificacionDTO(ejecucionConciliacion.getConciliacion().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getNombre(),ejecucionConciliacion.getConciliacion().getCuenta().getId(),ejecucionConciliacion.getConciliacion().getCuenta().getNumeroCuenta(), ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(), ejecucionConciliacion.getProveedor().getNombre().getNombre());

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa3Layout().getTitle() : "";
		String remitente = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getFrom() : "";
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa3Layout().getVelocityTemplate() : "";

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elementoLI", datos);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAsunto(titulo);
		mailDTO.setDe(remitente);
		mailDTO.setPara(destinatarios);
		mailDTO.setContenidoHTML(htmlMail);
		mailDTO.setAdjuntos(new BusRestAdjuntoDTO(new ArrayList<>()));
		return mailDTO;
	}
	
}
