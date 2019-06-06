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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.LayoutMailConstants;
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
	 * Mail from para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.from}")
	private String mailFrom;

	/**
	 * Usuario para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.auth.user}")
	private String mailUser;

	/**
	 * Password para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.auth.password}")
	private String mailPass;

	/**
	 * Regresa un objeto de tipo X (por definir) con la infromacion que se debe
	 * enviar por mail para la solicitud de pagos
	 * 
	 * @param folio
	 * @param idsComisiones
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void getDataByFolioAndIdMovimientos(final Integer folio, final List<Integer> idsMovimientos, final String createdBy) {
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
		// Consulta
		solicitarPagosMailDataDTOList = movimientosMidasRepository.getDataByFolioAndIdMovimientos(folio,
				idsMovimientos);
		// Actualiza
		movimientoTransitoList = movimientoTransitoRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoTransitoList || movimientoTransitoList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		for (MovimientoTransito mt : movimientoTransitoList) {
			mt.setEstatus(new EstatusTransito(2));
		}
		movimientoTransitoRepository.saveAll(movimientoTransitoList);
		// Inserta
		movimientoConciliacionList = movimientoConciliacionRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoConciliacionList || movimientoConciliacionList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		movimientosPagoRepository.saveAll(
				MovimientosBuilder.buildMovimientoPagoListFromMovimientoConciliacionList(movimientoConciliacionList, createdBy));
		if (null == solicitarPagosMailDataDTOList || solicitarPagosMailDataDTOList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
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
		generalBusMailDTO = new GeneralBusMailDTO(contacts.toString(), mailFrom, "Solicitud de Pagos", htmlMail,
				new SolicitarPagosAdjuntoDTO(new ArrayList<>()));
		bearerToken = busMailRestService.postForGetToken(mailUser, mailPass);
		if (null == bearerToken || "".equals(bearerToken))
			throw new ConciliacionException(ConciliacionConstants.CANNOT_GET_MAIL_TOKEN);
		statusResponse = busMailRestService.postMail(mailUser, mailPass, generalBusMailDTO, bearerToken);
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
