/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import java.util.LinkedHashMap;
import java.util.Map;

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
	 * URL para servicio que provee el token
	 */
	@Value(value = "${mimonte.variables.mail.token.url}")
	private String urlGetToken;

	/**
	 * URL para el servicio de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.url}")
	private String urlSendEmail;

	/**
	 * Key de Cabecera de autenticacion
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.auth}")
	private String headerAuthKey;

	/**
	 * Valor de cabecera de autenticacion
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.auth-value}")
	private String headerAuthValue;

	/**
	 * Key de cabecera para tipo de contenido
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.content-type}")
	private String contentTypeKey;

	/**
	 * Valor para cabecera con tipo de contenido para servicio de token
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.content-type-value}")
	private String tokenContentTypeValue;

	/**
	 * Valor para cabecera con tipo de contenido para servicio de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.headers.content-type-value}")
	private String senMailContentTypeValue;

	/**
	 * Key de Cabecera para id de consumidor
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor}")
	private String idConsumidorKey;

	/**
	 * Valor de cabecera para id consumidor
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor-value}")
	private String idConsumidorValue;

	/**
	 * Key para cabecera de id de destino
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-destino}")
	private String idDestinoKey;

	/**
	 * Valor para cabecera de id de destino
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-destino-value}")
	private String idDestinoValue;

	/**
	 * Key para cabecera de usuario
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.usuario}")
	private String usuarioKey;

	/**
	 * Valor para cabecera de usuario
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.usuario-value}")
	private String usuarioValue;

	/**
	 * Key para cabecera de envio de mail que contiene el token
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.headers.oauth-bearer-key}")
	private String oauthBearer;

	/**
	 * Bandera que indica si la operacion de envio se realizo correctamente
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.success-flag}")
	private String flagSendingSuccess;

	/**
	 * Key de cabecera para propiedad GrantTypeKey
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.grant-key}")
	private String headerGrantTypeKey;

	/**
	 * Valor de cabecera para propiedad GrantTypeKey
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.grant-value}")
	private String headerGrantTypeValue;

	/**
	 * Key de cabecera para propiedad scope
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.scope-key}")
	private String headerScopeKey;

	/**
	 * Valor de cabecera para propiedad scope
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.scope-value}")
	private String headerScopeValue;

	/**
	 * Key para el objeto de respuesta 'respuesta' de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.response.resp-key}")
	private String responseRespKey;

	/**
	 * Key para el objeto de respuesta 'codigo' de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.response.cod-key}")
	private String responseCodpKey;

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
		map.add(headerGrantTypeKey, headerGrantTypeValue);
		map.add(headerScopeKey, headerScopeValue);
		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map,
				createHeadersToken(user, password));
		ResponseEntity<TokenServiceReponseDTO> response = restTemplate.exchange(urlGetToken, HttpMethod.POST, request2,
				TokenServiceReponseDTO.class);
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
		Object resp = restTemplate.postForObject(urlSendEmail, request, Object.class);
		obj = new LinkedHashMap<>();
		obj = (LinkedHashMap) resp;
		return null != obj && null != obj.get(responseRespKey)
				&& null != ((LinkedHashMap) (obj.get(responseRespKey))).get(responseCodpKey) && flagSendingSuccess
						.equals(((LinkedHashMap) (obj.get(responseRespKey))).get(responseCodpKey).toString());
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
