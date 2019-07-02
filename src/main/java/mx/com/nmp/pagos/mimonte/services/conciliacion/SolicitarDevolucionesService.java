/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;

/**
 * @name SolicitarDevolucionesService
 * @description Clase de capa de servicios que se encarga de construir y enviar el mensaje para la solicitud de devoluciones
 * @creationDate 28/06/2019 17:18 hrs.
 * @version 0.1
 */
@Service("solicitarDevolucionesService")
public class SolicitarDevolucionesService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SolicitarDevolucionesService.class);

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
	 * Se encarga de enviar la solicitud de devoluciones por email a los contactos de la entidad bancaria
	 * @param devoluciones
	 * @param contactos
	 */
	public void enviarSolicitudDevoluciones(List<DevolucionEntidadDTO> devoluciones) throws ConciliacionException {

		// Se obtienen los contactos de midas
		Set<Contactos> contactos = contactoRespository.findByEntidades_Id(ConciliacionConstants.TIPO_CONTACTO_ENTIDAD);
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL);

		// Construye el objeto email
		BusRestMailDTO generalBusMailDTO = buildBusMailDTO(devoluciones, contactos);

		// Envia e-mail
		try {
			LOG.debug("Enviando email {}", generalBusMailDTO);
			this.busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL);
		}

	}


	/**
	 * Construye el correo electronico a ser enviado a los contactos de midas
	 * @return
	 */
	public BusRestMailDTO buildBusMailDTO(List<DevolucionEntidadDTO> devoluciones, Set<Contactos> contactos) throws ConciliacionException  {
	
		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion().getTitulo();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion().getVelocityTemplate();
		String contenidoHtml = getContenidoHtml(devoluciones, template);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAdjuntos(null);
		mailDTO.setAsunto(titulo);
		mailDTO.setContenidoHTML(contenidoHtml);
		mailDTO.setDe(remitente);
		mailDTO.setPara(destinatarios);

		return mailDTO;
	}


	/**
	 * Construye el contenido del email del template velocity
	 * @param devoluciones
	 * @param template
	 * @return
	 * @throws ConciliacionException
	 */
	private String getContenidoHtml(List<DevolucionEntidadDTO> devoluciones, String template) throws ConciliacionException {
		Map<String, Object> modelo = new LinkedHashMap<String, Object>();
		modelo.put("text1", applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion().getBodyText1());
		modelo.put("text2", applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion().getBodyText2());
		modelo.put("devoluciones", devoluciones);

		String contenidoHtml = "";
		try {
			contenidoHtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", modelo);
		}
		catch (VelocityException ve) {
			ve.printStackTrace();
			throw new ConciliacionException("Error al obtener el contenido del email para la solicitud");
		}

		return contenidoHtml;
	}

}
