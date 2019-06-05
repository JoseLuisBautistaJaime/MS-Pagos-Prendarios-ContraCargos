/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneralBusMailDTO;

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

	@Value(value = "${mimonte.variables.mail.token.url}")
	private String urlGetToken;

	@Value(value = "${mimonte.variables.mail.send-mail.url}")
	private String urlSendEmail;

	@Value(value = "${mimonte.variables.mail.common-headers.auth}")
	private String headerAuthKey;

	@Value(value = "${mimonte.variables.mail.common-headers.auth-value}")
	private String headerAuthValue;

	@Value(value = "${mimonte.variables.mail.common-headers.content-type}")
	private String contentTypeKey;

	@Value(value = "${mimonte.variables.mail.token.headers.content-type-value}")
	private String tokenContentTypeValue;

	@Value(value = "${mimonte.variables.mail.send-mail.headers.content-type-value}")
	private String senMailContentTypeValue;

	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor}")
	private String idConsumidorKey;

	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor-value}")
	private String idConsumidorValue;

	@Value(value = "${mimonte.variables.mail.common-headers.id-destino}")
	private String idDestinoKey;

	@Value(value = "${mimonte.variables.mail.common-headers.id-destino-value}")
	private String idDestinoValue;

	@Value(value = "${mimonte.variables.mail.common-headers.usuario}")
	private String usuarioKey;

	@Value(value = "${mimonte.variables.mail.common-headers.usuario-value}")
	private String usuarioValue;

	@Value(value = "${mimonte.variables.mail.send-mail.headers.oauth-bearer-key}")
	private String oauthBearer;

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
		map.add("grant_type", "client_credentials");
		map.add("scope", "UserProfile.me");
		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map,
				createHeadersToken(user, password));
		ResponseEntity<Object> response = restTemplate.exchange(urlGetToken, HttpMethod.POST, request2, Object.class);
		Object obj = response.getBody();
		return obj.toString();
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
	public String postMail(final String user, final String password, final GeneralBusMailDTO generalBusMailDTO,
			final String bearerToken) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<GeneralBusMailDTO> request = new HttpEntity<>(generalBusMailDTO,
				createHeadersSend(user, password, bearerToken));
		Object obj = restTemplate.postForObject(urlSendEmail, request, GeneralBusMailDTO.class);
		return obj.toString();
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
		headers.add(headerAuthKey, headerAuthValue.concat(" ") + base64Creds);
		headers.add(contentTypeKey, tokenContentTypeValue);
		headers.add(idConsumidorKey, idConsumidorValue);
		headers.add(idDestinoKey, idDestinoValue);
		headers.add(usuarioKey, usuarioValue);
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
		headers.add(headerAuthKey, headerAuthValue.concat(" ") + base64Creds);
		headers.add(contentTypeKey, senMailContentTypeValue);
		headers.add(idConsumidorKey, idConsumidorValue);
		headers.add(idDestinoKey, idDestinoValue);
		headers.add(usuarioKey, usuarioValue);
		headers.add(oauthBearer, bearerToken);
		return headers;
	}

}
