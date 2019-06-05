/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.LayoutMailConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.TipoContactoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneralBusMailDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;

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
	 * Repository de movimientos nocturnos (midas)
	 */
	@Autowired
	@Qualifier("movimientosMidasRepository")
	private MovimientosMidasRepository movimientosMidasRepository;

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

	@Autowired
	@Qualifier("busMailRestService")
	private BusMailRestService busMailRestService;
	
	/**
	 * Regresa un objeto de tipo X (por definir) con la infromacion que se debe
	 * enviar por mail para la solicitud de pagos
	 * 
	 * @param folio
	 * @param idsComisiones
	 */
	public void getDataByFolioAndIdMovimientos(final Integer folio, final List<Integer> idsComisiones) {
		// Declaracion de objetos y variables necesrios
		List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList = null;
		List<Contactos> contactos = null;
		GeneralBusMailDTO generalBusMailDTO = null;
		StringBuilder contacts = null;
		TipoContacto tipoContacto = null;
		StringBuilder dataTable = null;
		String bearerToken = null;
		@SuppressWarnings("unused")
		String response = null;
		String mainHtml = null;
		String htmlMail = null;
		int ct = 0;
		// Consultar datos
		solicitarPagosMailDataDTOList = movimientosMidasRepository.getDataByFolioAndIdMovimientos(folio, idsComisiones);
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
		generalBusMailDTO = new GeneralBusMailDTO(contacts.toString(), "mail@nmp.com", "Solicitud de Pagos", htmlMail,
				null);
		bearerToken = busMailRestService.postForGetToken("mimonte", "iXjqY2ccS783PTF0");
		response = busMailRestService.postMail("mimonte", "iXjqY2ccS783PTF0",generalBusMailDTO, bearerToken);
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
