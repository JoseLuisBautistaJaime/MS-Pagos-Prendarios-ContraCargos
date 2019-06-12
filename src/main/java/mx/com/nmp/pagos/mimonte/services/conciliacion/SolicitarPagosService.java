/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.LayoutMailConstants;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.TipoContactoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosPagoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneralBusMailDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;
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
	 * Repository de tipo contacto
	 */
	@Autowired
	private TipoContactoRepository tipoContactoRepository;

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
		List<Contactos> contactos = null;
		List<MovimientoConciliacion> movimientoConciliacionList = null;
		List<MovimientoTransito> movimientoTransitoList = null;
		GeneralBusMailDTO generalBusMailDTO = null;
		StringBuilder contacts = null;
		TipoContacto tipoContacto = null;
		StringBuilder dataTable = null;
		String bearerToken = null;
		boolean statusResponse = true;
		String mainHtml = null;
		String htmlMail = null;
		int ct = 0;
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
			generalBusMailDTO = construyeEMail(contactos, contacts, tipoContacto, dataTable, mainHtml, htmlMail, ct,
					solicitarPagosMailDataDTOList, generalBusMailDTO);

		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL);
		}
		try {
			// Envia e-mail
			enviaEmail(bearerToken, statusResponse, generalBusMailDTO);
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
	public GeneralBusMailDTO construyeEMail(List<Contactos> contactos, StringBuilder contacts,
			TipoContacto tipoContacto, StringBuilder dataTable, String mainHtml, String htmlMail, int ct,
			List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList, GeneralBusMailDTO generalBusMailDTO) {
		// Se obtienen los contactos de midas
		tipoContacto = tipoContactoRepository.findByDescriptionContaining("Midas");
		if (null == tipoContacto || null == tipoContacto.getId())
			throw new InformationNotFoundException(CatalogConstants.TIPO_CONTACTO_NOT_FOUND);
		contactos = contactoRespository.findByIdTipoContacto(tipoContacto.getId());
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL);
		// Construccion de respuesta HTML
		dataTable = new StringBuilder();
		mainHtml = LayoutMailConstants.HTML_SOLICITAR_PAGOS_LAYOUT;
		dataTable.append("<TABLE>");
		buildHeaderForSolicitarPagosMailDataDTO(dataTable);
		for (SolicitarPagosMailDataDTO value : solicitarPagosMailDataDTOList) {
			buildBodyForSolicitarPagosMailDataDTO(dataTable, value);
		}
		dataTable.append("</TABLE>");
		htmlMail = mainHtml.replace("[bodyContent]", dataTable.toString());
		// Se construye el DTO de request y se invoca el servicio de correo proveido por
		// BUS
		contacts = new StringBuilder();
		for (Contactos cto : contactos) {
			if (ct > 0)
				contacts.append(",");
			contacts.append(cto.getEmail());
			ct++;
		}
		return new GeneralBusMailDTO(contacts.toString(), mc.mailFrom, "Solicitud de Pagos", htmlMail,
				new SolicitarPagosAdjuntoDTO(new ArrayList<>()));
	}

	/**
	 * Envia el correo electronico el atributo bearerToken y statusResponse pueden
	 * ser enviados nulos o con con valores por deault el unico que debe ir completo
	 * (o al menos con la informacion real) es el objeto generalBusMailDTO
	 * 
	 * @param bearerToken
	 * @param statusResponse
	 * @param generalBusMailDTO
	 */
	public void enviaEmail(String bearerToken, boolean statusResponse, GeneralBusMailDTO generalBusMailDTO) {
		bearerToken = busMailRestService.postForGetToken(mc.mailUser, mc.mailPass);
		if (null == bearerToken || "".equals(bearerToken))
			throw new ConciliacionException(ConciliacionConstants.CANNOT_GET_MAIL_TOKEN);
		statusResponse = busMailRestService.postMail(mc.mailUser, mc.mailPass, generalBusMailDTO, bearerToken);
		LOG.debug(statusResponse ? "Mail enviado correctamente" : "Error al enviar el e-mail");
		if (!statusResponse)
			throw new ConciliacionException(ConciliacionConstants.MAIL_CANNOT_BE_SEND);
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
		str.append("<tr>");
		str.append("<td>");
		str.append(value.getFolio());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getSucursal());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getFecha());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getMonto());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getTipoContratoDesc());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getCuenta());
		str.append("</td>");
		str.append("<td>");
		str.append(value.getTitular());
		str.append("</td>");
		str.append("</tr>");
	}

}
