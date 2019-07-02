/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.LayoutMailConstants;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosPagoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;

/**
 * @name SolicitarPagosService
 * @description Clase de capa de servicios que se encarga de realizar
 *              operaciones de logica de negocio relacionadas con las
 *              solicitudes de pagos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/06/2019 17:18 hrs.
 * @version 0.1
 */
@Service("solicitarPagosService")
public class SolicitarPagosService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SolicitarPagosService.class);

	/**
	 * Repository de movimientos nocturnos (midas)
	 */
	@Autowired
	@Qualifier("movimientosMidasRepository")
	private MovimientosMidasRepository movimientosMidasRepository;

	/**
	 * Repository de MovimientoConciliacionRepository
	 */
	@Autowired
	@Qualifier("movimientoConciliacionRepository")
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

	/**
	 * Repository de MovimientosPagoRepository
	 */
	@Autowired
	@Qualifier("movimientosPagoRepository")
	private MovimientosPagoRepository movimientosPagoRepository;

	/**
	 * Repository de MovimientoTransitoRepository
	 */
	@Autowired
	@Qualifier("movimientoTransitoRepository")
	private MovimientoTransitoRepository movimientoTransitoRepository;

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
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	private MailServiceConstants mc;

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * Regresa un objeto de tipo X (por definir) con la infromacion que se debe
	 * enviar por mail para la solicitud de pagos
	 * 
	 * @param folio
	 * @param idsComisiones
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void solicitarPagosService(final Integer folio, final List<Integer> idsMovimientos, final String createdBy) {
		// Declaracion de objetos y variables necesarios
		List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList = null;
		List<MovimientoConciliacion> movimientoConciliacionList = null;
		List<MovimientoTransito> movimientoTransitoList = null;
		BusRestMailDTO generalBusMailDTO = null;
		
		// Consultar datos, actualizacion y generacion de registros en pagos
		try {
			// Consulta
			solicitarPagosMailDataDTOList = consultarMovimientos(solicitarPagosMailDataDTOList, folio, idsMovimientos);
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_GET_MOVIMIENTOS_TRANSITO);
		}
		try {
			// Actualiza
			actualizarMovimientosTransito(movimientoTransitoList, folio, idsMovimientos);
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_UPDATE_MOVIMIENTOS_TRANSITO);
		}
		try {
			// Inserta
			insertaMovimientosPago(movimientoConciliacionList, folio, idsMovimientos, solicitarPagosMailDataDTOList,
					createdBy);
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_INSERT_MOVIMIENTOS_PAGO);
		}
		try {
			// Construye e-mail
//			generalBusMailDTO = construyeEMail(contactos, contacts, tipoContacto, dataTable, mainHtml, htmlMail, ct,
//					solicitarPagosMailDataDTOList, generalBusMailDTO);
			generalBusMailDTO = construyeEMailVelocity(solicitarPagosMailDataDTOList);

		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL);
		}
		try {
			// Envia e-mail
			this.busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL);
		}
	}

	/**
	 * Consulta los movimientos en transito solicitados
	 * 
	 * @param solicitarPagosMailDataDTOList
	 * @param folio
	 * @param idsMovimientos
	 * @return
	 */
	public List<SolicitarPagosMailDataDTO> consultarMovimientos(
			List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList, Integer folio,
			List<Integer> idsMovimientos) {
		return movimientosMidasRepository.getDataByFolioAndIdMovimientos(folio, idsMovimientos);
	}

	/**
	 * Actualiza el estatus de los movimientos en transito solicitados
	 * 
	 * @param movimientoTransitoList
	 * @param folio
	 * @param idsMovimientos
	 */
	public void actualizarMovimientosTransito(List<MovimientoTransito> movimientoTransitoList, Integer folio,
			List<Integer> idsMovimientos) {
		movimientoTransitoList = movimientoTransitoRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoTransitoList || movimientoTransitoList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		for (MovimientoTransito mt : movimientoTransitoList) {
			mt.setEstatus(new EstatusTransito(2));
		}
		movimientoTransitoRepository.saveAll(movimientoTransitoList);
	}

	/**
	 * Inserta los correspondientes movimientos pago equivalentes para los
	 * movimientos en transito especificados
	 * 
	 * @param movimientoConciliacionList
	 * @param folio
	 * @param idsMovimientos
	 * @param solicitarPagosMailDataDTOList
	 * @param createdBy
	 */
	public void insertaMovimientosPago(List<MovimientoConciliacion> movimientoConciliacionList, Integer folio,
			List<Integer> idsMovimientos, List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList,
			String createdBy) {
		movimientoConciliacionList = movimientoConciliacionRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoConciliacionList || movimientoConciliacionList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		movimientosPagoRepository.saveAll(MovimientosBuilder
				.buildMovimientoPagoListFromMovimientoConciliacionList(movimientoConciliacionList, createdBy));
		if (null == solicitarPagosMailDataDTOList || solicitarPagosMailDataDTOList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
	}

	/**
	 * Construye el correo electronico a ser enviado a los contactos de midas
	 * 
	 * @param contactos
	 * @param contacts
	 * @param tipoContacto
	 * @param dataTable
	 * @param mainHtml
	 * @param htmlMail
	 * @param ct
	 * @param solicitarPagosMailDataDTOList
	 * @param generalBusMailDTO
	 * @return
	 */
	public BusRestMailDTO construyeEMail(List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_MIDAS);
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL);
		// Construccion de respuesta HTML
		StringBuilder dataTable = new StringBuilder();
		String mainHtml = LayoutMailConstants.HTML_SOLICITAR_PAGOS_LAYOUT;
		dataTable.append("<TABLE>");
		buildHeaderForSolicitarPagosMailDataDTO(dataTable);
		for (SolicitarPagosMailDataDTO value : solicitarPagosMailDataDTOList) {
			buildBodyForSolicitarPagosMailDataDTO(dataTable, value);
		}
		dataTable.append("</TABLE>");
		String htmlMail = mainHtml.replace("[bodyContent]", dataTable.toString());
		// Se construye el DTO de request y se invoca el servicio de correo proveido por
		// BUS
		StringBuilder contacts = new StringBuilder();
		int ct = 0;
		for (Contactos cto : contactos) {
			if (ct > 0)
				contacts.append(",");
			contacts.append(cto.getEmail());
			ct++;
		}
		return new BusRestMailDTO(contacts.toString(), mc.mailFrom, mc.subjectMail, htmlMail,
				new BusRestAdjuntoDTO(new ArrayList<>()));
	}

	public BusRestMailDTO construyeEMailVelocity(
			List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		Map<String, Object> model;
		// Se obtienen los contactos de midas
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_MIDAS);
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL);
		// Se constrye el cuerpo de correo HTML
		model = buildMailModel(solicitarPagosMailDataDTOList);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mc.velocityLayout, mc.encodeType, model);
		// Se construye el DTO de request y se invoca el servicio de correo proveido por
		// BUS
		StringBuilder contacts = new StringBuilder();
		int ct = 0;
		for (Contactos cto : contactos) {
			if (ct > 0)
				contacts.append(",");
			contacts.append(cto.getEmail());
			ct++;
		}
		return new BusRestMailDTO(contacts.toString(), mc.mailFrom, mc.subjectMail, htmlMail,
				new BusRestAdjuntoDTO(new ArrayList<>()));
	}

	/**
	 * Construye las cabeceras de la tabla html
	 * 
	 * @param str
	 */
	public void buildHeaderForSolicitarPagosMailDataDTO(StringBuilder str) {
		str.append("<tr>");
		str.append("<th>");
		str.append("Folio");
		str.append("</th>");
		str.append("<th>");
		str.append("Sucursal");
		str.append("</th>");
		str.append("<th>");
		str.append("Fecha");
		str.append("</th>");
		str.append("<th>");
		str.append("Monto");
		str.append("</th>");
		str.append("<th>");
		str.append("Tipo Contrato Desc");
		str.append("</th>");
		str.append("<th>");
		str.append("Cuenta");
		str.append("</th>");
		str.append("<th>");
		str.append("Titular");
		str.append("</th>");
		str.append("</tr>");
	}

	/**
	 * Construye las celdas de la tabla
	 * 
	 * @param str
	 * @param value
	 */
	public void buildBodyForSolicitarPagosMailDataDTO(StringBuilder str, SolicitarPagosMailDataDTO value) {
//		str.append("<tr>");
//		str.append("<td>");
//		str.append(value.getFolio());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getSucursal());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getFecha());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getMonto());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getTipoContratoDesc());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getCuenta());
//		str.append("</td>");
//		str.append("<td>");
//		str.append(value.getTitular());
//		str.append("</td>");
//		str.append("</tr>");
	}

	/**
	 * Crea el modelo a usar en plantilla HTML de velocity
	 * 
	 * @param solicitarPagosMailDataDTOList
	 * @return
	 */
	public Map<String, Object> buildMailModel(List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		Map<String, Object> model = new HashMap<>();
		model.put("text1", mc.text1);
		model.put("text2", mc.text2);
		model.put("text3", mc.text3);
		model.put("codigoAutorizacion", mc.codigoAutorizacion);
		model.put("codigoDescuento", mc.codigoDescuento);
		model.put("codigoRespuestaAdquiriente", mc.codigoRespuestaAdquiriente);
		model.put("codigoRespuestaMotorPagosTransaccion", mc.codigoRespuestaMotorPagosTransaccion);
		model.put("fechaTransaccion", mc.fechaTransaccion);
		model.put("folioPartida", mc.folioPartida);
		model.put("fuenteTransaccion", mc.fuenteTransaccion);
		model.put("identificadorCuenta", mc.identificadorCuenta);
		model.put("idTerminalAdquiriente", mc.idTerminalAdquiriente);
		model.put("metodoPago", mc.metodoPago);
		model.put("montoTransaccion", mc.montoTransaccion);
		model.put("numeroLoteAdquiriente", mc.numeroLoteAdquiriente);
		model.put("tipoCuenta", mc.tipoCuenta);
		model.put("tipoMoneda", mc.tipoMoneda);
		model.put("tipoTransaccion", mc.tipoTransaccion);
		model.put("titularCuenta", mc.titularCuenta);
		model.put("transaccion", mc.transaccion);
		model.put("elements", solicitarPagosMailDataDTOList);
		return model;
	}

}
