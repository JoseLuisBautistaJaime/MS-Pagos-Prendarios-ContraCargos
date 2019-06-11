/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneralBusMailDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TokenServiceReponseDTO;

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
public class BusMailRestService {

	/**
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	private MailServiceConstants mc;

	public BusMailRestService() {
		super();
	}

	/**
	 * Consume servicio de endpoint para obtencion de token
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public String postForGetToken(final String user, final String password) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(mc.headerGrantTypeKey, mc.headerGrantTypeValue);
		map.add(mc.headerScopeKey, mc.headerScopeValue);
		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map,
				createHeadersToken(user, password));
		ResponseEntity<TokenServiceReponseDTO> response = restTemplate.exchange(mc.urlGetToken, HttpMethod.POST,
				request2, TokenServiceReponseDTO.class);
		TokenServiceReponseDTO obj = response.getBody();
		return null != obj && null != obj.getAccess_token() ? obj.getAccess_token() : null;
	}

	/**
	 * Consume servicio de endpoint de envio de e-mail
	 * 
	 * @param user
	 * @param password
	 * @param generalBusMailDTO
	 * @param bearerToken
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean postMail(final String user, final String password, final GeneralBusMailDTO generalBusMailDTO,
			final String bearerToken) {
		Map obj = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<GeneralBusMailDTO> request = new HttpEntity<>(generalBusMailDTO,
				createHeadersSend(user, password, bearerToken));
		Object resp = restTemplate.postForObject(mc.urlSendEmail, request, Object.class);
		obj = new LinkedHashMap<>();
		obj = (LinkedHashMap) resp;
		return null != obj && null != obj.get(mc.responseRespKey)
				&& null != ((LinkedHashMap) (obj.get(mc.responseRespKey))).get(mc.responseCodpKey)
				&& mc.flagSendingSuccess
						.equals(((LinkedHashMap) (obj.get(mc.responseRespKey))).get(mc.responseCodpKey).toString());
	}

	/**
	 * Construye los headers para el endpoint de obtencion del token
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private HttpHeaders createHeadersToken(String username, String password) {
		String plainCreds = username.concat(":").concat(password);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add(mc.headerAuthKey, mc.headerAuthValue.concat(" ") + base64Creds);
		headers.add(mc.contentTypeKey, mc.tokenContentTypeValue);
		headers.add(mc.idConsumidorKey, mc.idConsumidorValue);
		headers.add(mc.idDestinoKey, mc.idDestinoValue);
		headers.add(mc.usuarioKey, mc.usuarioValue);
		return headers;
	}

	/**
	 * Construye los headers para el consumo del enpoint de envio de e-mail
	 * 
	 * @param username
	 * @param password
	 * @param bearerToken
	 * @return
	 */
	private HttpHeaders createHeadersSend(String username, String password, final String bearerToken) {
		String plainCreds = username.concat(":").concat(password);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add(mc.headerAuthKey, mc.headerAuthValue.concat(" ") + base64Creds);
		headers.add(mc.contentTypeKey, mc.senMailContentTypeValue);
		headers.add(mc.idConsumidorKey, mc.idConsumidorValue);
		headers.add(mc.idDestinoKey, mc.idDestinoValue);
		headers.add(mc.usuarioKey, mc.usuarioValue);
		headers.add(mc.oauthBearer, bearerToken);
		return headers;
	}

}
