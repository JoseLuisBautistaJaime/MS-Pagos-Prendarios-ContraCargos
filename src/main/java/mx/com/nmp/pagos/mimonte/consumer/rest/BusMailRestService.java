/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;

/**
 * @name BusMailRestService
 * @description Clase para consumir servicios rest relacionados con el envio de
 *              e-mail y que son expuestos por BUS
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:46 hrs.
 */
@Component("busMailRestService")
public class BusMailRestService extends AbstractOAuth2RestService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SolicitarPagosService.class);

	public BusMailRestService() {
		super();
	}

	/**
	 * Envia el correo electronico el atributo bearerToken y statusResponse pueden
	 * ser enviados nulos o con con valores por deault el unico que debe ir completo
	 * (o al menos con la informacion real) es el objeto generalBusMailDTO
	 * 
	 * @param body
	 */
	@SuppressWarnings("unchecked")
	public void enviaEmail(BusRestMailDTO body) {
		// Se obtiene el token
		BusRestAuthDTO auth = new BusRestAuthDTO(mc.mailUser, mc.mailPass);
		String bearerToken = postForGetToken(auth, mc.urlGetToken);
		// Se manda el correo usando el token
		BusRestHeaderDTO header = new BusRestHeaderDTO(bearerToken);
		Map<String, Object> response = postForObject(auth, body, header, mc.urlSendEmail);

		boolean statusResponse = (null != response && null != response.get("respuesta")
				&& null != ((LinkedHashMap<String, Object>) (response.get("respuesta"))).get("codigo")
				&& "success".equals(((LinkedHashMap<String, Object>) (response.get("respuesta")))
						.get("codigo").toString()));

		LOG.debug(statusResponse ? "Mail enviado correctamente" : "Error al enviar el e-mail");

		if (!statusResponse)
			throw new ConciliacionException(ConciliacionConstants.MAIL_CANNOT_BE_SEND,
					CodigoError.NMP_PMIMONTE_BUSINESS_066);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.consumer.rest.AbstractOAuth2RestService#
	 * createHeadersPostTo(mx.com.nmp.pagos.mimonte.consumer.rest.dto.
	 * BusRestAuthDTO, mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO)
	 */
	protected HttpHeaders createHeadersPostTo(BusRestAuthDTO auth, BusRestHeaderDTO header) {
		String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/json");
		headers.add(mc.idConsumidorKey, mc.idConsumidorValue);
		headers.add(mc.idDestinoKey, mc.idDestinoValue);
		headers.add(mc.usuarioKey, mc.usuarioValue);
		headers.add("oauth.bearer", header.getBearerToken());
		return headers;
	}

}
