/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO2;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;

/**
 * @name SolicitarDevolucionesService
 * @description Clase de capa de servicios que se encarga de construir y enviar
 *              el mensaje para la solicitud de devoluciones
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
	 * Repository de Conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

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
	 * Se encarga de enviar la solicitud de devoluciones por email a los contactos
	 * de la entidad bancaria
	 * 
	 * @param devoluciones
	 * @param contactos
	 */
	public void enviarSolicitudDevoluciones(List<DevolucionEntidadDTO2> devoluciones, final Long folio)
			throws ConciliacionException {

		// Objetos necesarios
		Map<String, Object> res = null;
		String entidad = null;
		String cuenta = null;

		// Se obtienen los contactos de midas
		Set<Contactos> contactos = contactoRespository.findByEntidades_Id(devoluciones.get(0).getEntidad().getId());
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_056);

		// Se obtiene la cuenta y entidad para mostrar en la leyenda
		if (!devoluciones.isEmpty() && null != devoluciones.get(0) && null != devoluciones.get(0).getEntidad()
				&& null != devoluciones.get(0).getEntidad().getNombre() && null != devoluciones.get(0).getCuenta()) {
			entidad = devoluciones.get(0).getEntidad().getNombre();
			cuenta = devoluciones.get(0).getCuenta();
		}
		else if (null != folio) {
			res = conciliacionRepository.getEntidadNombreAndCuentaNumeroByConciliacionId(folio);
			if (null != res && !res.isEmpty()) {
				entidad = null != res.get("entidad") ? String.valueOf(res.get("entidad")) : null;
				cuenta = null != res.get("cuenta") ? String.valueOf(res.get("cuenta")) : null;
			} else {
				entidad = "";
				cuenta = "";
			}
		} else {
			entidad = "";
			cuenta = "";
		}

		// Construye el objeto email
		BusRestMailDTO generalBusMailDTO = buildBusMailDTO(devoluciones, contactos, entidad, cuenta);

		// Envia e-mail
		try {
			LOG.debug("Enviando email {}", generalBusMailDTO);
			this.busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_057);
		}

	}

	/**
	 * Realiza el envio de solicitudes de devolucion, se invoca por cada grupo de
	 * devoluciones que comparten una misma entidad
	 * 
	 * @param lst
	 * @throws ConciliacionException
	 */
	public void enviarSolicitudDevoluciones(final List<DevolucionEntidadDTO2> lst) throws ConciliacionException {

		// Objetos necesarios
		String entidad = null;
		String cuenta = null;

		// Se obtienen los contactos de midas
		Set<Contactos> contactos = contactoRespository.findByEntidades_Id(lst.get(0).getEntidad().getId());
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_056);

		// Se obtiene la cuenta y entidad para mostrar en la leyenda
		entidad = lst.get(0).getEntidad().getNombre();
		cuenta = "";

		// Construye el objeto email
		BusRestMailDTO generalBusMailDTO = buildBusMailDTO(lst, contactos, entidad, cuenta);

		// Envia e-mail
		try {
			LOG.debug("Enviando email {}", generalBusMailDTO);
			this.busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_057);
		}

	}

	/**
	 * Construye el correo electronico a ser enviado a los contactos de midas
	 * 
	 * @return
	 */
	public BusRestMailDTO buildBusMailDTO(List<DevolucionEntidadDTO2> devoluciones, Set<Contactos> contactos,
			final String entidad, final String cuenta) throws ConciliacionException {

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudDevolucion()
				.getVelocityTemplate();
		String contenidoHtml = getContenidoHtml(devoluciones, template, entidad, cuenta);

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
	 * 
	 * @param devoluciones
	 * @param template
	 * @return
	 * @throws ConciliacionException
	 */
	private String getContenidoHtml(List<DevolucionEntidadDTO2> devoluciones, String template, final String entidad,
			final String cuenta) throws ConciliacionException {
		Map<String, Object> modelo = new LinkedHashMap<String, Object>();
		// Se sustituyen los valores
		modelo.put("numeroCuenta", cuenta);
		modelo.put("entidad", entidad);
		modelo.put("devoluciones", devoluciones);
		// Se reemplazan posibles EL en las leyendas y el modelo
		modelo.put("devoluciones", replaceNullValues(modelo.get("devoluciones")));

		String contenidoHtml = "";
		try {
			contenidoHtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", modelo);
		} catch (VelocityException ve) {
			ve.printStackTrace();
			throw new ConciliacionException("Error al obtener el contenido del email para la solicitud",
					CodigoError.NMP_PMIMONTE_BUSINESS_058);
		}
		return contenidoHtml;
	}

	/**
	 * Reeemplaza posibles valores nulos con cadenas vacias o valores por default
	 * para atributos primitivos u objetos complejos
	 * 
	 * @param devolucionesObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DevolucionEntidadDTO2> replaceNullValues(Object devolucionesObj) {
		List<DevolucionEntidadDTO2> devoluciones;
		List<DevolucionEntidadDTO2> respDev = null;
		DevolucionEntidadDTO2 devolucion = null;
		if (null != devolucionesObj) {
			devoluciones = (List<DevolucionEntidadDTO2>) devolucionesObj;
			if (!devoluciones.isEmpty()) {
				respDev = new ArrayList<>();
				for (DevolucionEntidadDTO2 dev : devoluciones) {
					devolucion = new DevolucionEntidadDTO2();
					devolucion.setCodigoAutorizacion(
							null != dev.getCodigoAutorizacion() ? dev.getCodigoAutorizacion() : "");
					devolucion.setEntidad(null != dev.getEntidad() ? dev.getEntidad() : new BaseEntidadDTO());
					devolucion.setEsquemaTarjeta(null != dev.getEsquemaTarjeta() ? dev.getEsquemaTarjeta() : "");
					devolucion.setEstatus(null != dev.getEstatus() ? dev.getEstatus() : new EstatusDevolucionDTO());
					devolucion.setFecha(null != dev.getFecha() ? dev.getFecha() : new Date());
					devolucion.setFechaLiquidacion(
							null != dev.getFechaLiquidacion() ? dev.getFechaLiquidacion() : new Date());
					devolucion.setHora(null != dev.getHora() ? dev.getHoraComleteFormat() : new Date());
					devolucion.setId(null != dev.getId() ? dev.getId() : 0);
					devolucion.setIdentificadorCuenta(
							null != dev.getIdentificadorCuenta() ? dev.getIdentificadorCuenta() : "");
					devolucion.setMonto(null != dev.getMonto() ? dev.getMonto() : BigDecimal.ZERO);
					devolucion.setSucursal(null != dev.getSucursal() ? dev.getSucursal() : 0);
					devolucion.setTitular(null != dev.getTitular() ? dev.getTitular() : "");
					respDev.add(devolucion);
				}
			}
		}
		return respDev;
	}

}
