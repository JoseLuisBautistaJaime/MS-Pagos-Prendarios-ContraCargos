/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestBodyDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestParamDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestTokenReponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;

/**
 * @name AbstractOAuth2RestService
 * @description Clase para consumir servicios rest relacionados con el envio de
 *              e-mail y que son expuestos por BUS
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:46 hrs.
 */
public abstract class AbstractOAuth2RestService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	protected static final Logger LOG = LoggerFactory.getLogger(SolicitarPagosService.class);

	/**
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	protected MailServiceConstants mc;

	@Autowired
	protected ApplicationProperties applicationProperties;

	@Autowired
	protected RetryTemplate retryTemplate;

	public AbstractOAuth2RestService() {
		super();
	}

	/**
	 * Consume servicio de endpoint para obtencion de token
	 * 
	 * @param auth
	 * @return
	 */
	public String postForGetToken(final BusRestAuthDTO auth, String url) throws ConciliacionException {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "client_credentials");
		map.add("scope", "UserProfile.me");

		HttpHeaders headers = createHeadersToken(auth);
		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		// Retries
		BusRestTokenReponseDTO obj = retryTemplate
				.execute(new RetryCallback<BusRestTokenReponseDTO, ConciliacionException>() {
					public BusRestTokenReponseDTO doWithRetry(RetryContext context) throws ConciliacionException {
						RestTemplate restTemplate = new RestTemplate();
						ResponseEntity<BusRestTokenReponseDTO> response = null;
						LOG.info("postForGetToken: {}, intento: #{}", url, context.getRetryCount());
						try {
							response = restTemplate.exchange(url, HttpMethod.POST, request2,
									BusRestTokenReponseDTO.class);
						} catch (Exception ex) {
							ex.printStackTrace();
							throw ex;
						}
						return response.getBody();
					}
				});

		String bearerToken = null != obj && null != obj.getAccess_token() ? obj.getAccess_token() : null;

		if (null == bearerToken || "".equals(bearerToken))
			throw new ConciliacionException(ConciliacionConstants.CANNOT_GET_TOKEN,
					CodigoError.NMP_PMIMONTE_BUSINESS_064);

		return bearerToken;
	}

	/**
	 * Consume servicio de endpoint
	 * 
	 * @param auth
	 * @param body
	 * @param header
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BusRestBodyDTO> Map<String, Object> postForObject(final BusRestAuthDTO auth, final T body,
			final BusRestHeaderDTO header, String url) {

		HttpHeaders headers = createHeadersPostTo(auth, header);
		HttpEntity<T> request = new HttpEntity<T>(body, headers);

		Object resp = retryTemplate.execute(new RetryCallback<Object, ConciliacionException>() {
			public Object doWithRetry(RetryContext context) throws ConciliacionException {
				Object resp = null;
				LOG.info("postForObject: {}, intento: #{}", url, context.getRetryCount());
				try {
					RestTemplate restTemplate = new RestTemplate();
					resp = restTemplate.postForObject(url, request, Object.class);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
				return resp;
			}
		});

		Map<String, Object> obj = (LinkedHashMap<String, Object>) resp;

		return obj;
	}

	/**
	 * Consume servicio de endpoint
	 * 
	 * @param auth
	 * @param params
	 * @param header
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BusRestParamDTO> Map<String, Object> getForObject(final BusRestAuthDTO auth, final T params,
			final BusRestHeaderDTO header, final String url) {

		HttpHeaders headers = createHeadersPostTo(auth, header);
		HttpEntity<T> request = new HttpEntity<T>(params, headers);

		Object resp = retryTemplate.execute(new RetryCallback<Object, ConciliacionException>() {
			public Object doWithRetry(RetryContext context) throws ConciliacionException {
				ResponseEntity<Object> response = null;
				String uri = url + params.getUri();
				LOG.info("getForObject: {}, intento: #{}", uri, context.getRetryCount());
				try {
					RestTemplate restTemplate = new RestTemplate();
					response = restTemplate.exchange(uri, HttpMethod.GET, request, Object.class);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
				return response.getBody();
			}
		});

		Map<String, Object> obj = (LinkedHashMap<String, Object>) resp;

		return obj;
	}

	// PRIVATES //////////////////////////////////

	/**
	 * Retry template
	 * 
	 * @return
	 */
	@Bean
	private RetryTemplate retryTemplate() {
		int maxAttempt = applicationProperties.getMimonte().getVariables().getRestTemplate().getMaxAttempt();
		int retryTimeInterval = applicationProperties.getMimonte().getVariables().getRestTemplate()
				.getRetryTimeInterval();
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(maxAttempt);
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(retryTimeInterval);

		RetryTemplate template = new RetryTemplate();
		template.setRetryPolicy(retryPolicy);
		template.setBackOffPolicy(backOffPolicy);

		return template;
	}

	/**
	 * Construye los headers para el endpoint de obtencion del token
	 * 
	 * @param auth
	 * @return
	 */
	private HttpHeaders createHeadersToken(BusRestAuthDTO auth) {
		String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		headers.add(mc.idConsumidorKey, mc.idConsumidorValue);
		headers.add(mc.idDestinoKey, mc.idDestinoValue);
		headers.add(mc.usuarioKey, mc.usuarioValue);
		return headers;
	}

	/**
	 * Construye los headers para el consumo del enpoint
	 * 
	 * @param auth
	 * @return
	 */
	protected abstract HttpHeaders createHeadersPostTo(BusRestAuthDTO auth, BusRestHeaderDTO headers);

	/**
	 * Build base 64 hash para autenticacion basic
	 * 
	 * @param auth
	 * @return
	 */
	protected String buildBase64Hash(BusRestAuthDTO auth) {
		String plainCreds = auth.getUser().concat(":").concat(auth.getPassword());
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		return base64Creds;
	}



	/**
	 * Consume servicio de endpoint
	 *
	 * @param auth
	 * @param body
	 * @param header
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BusRestBodyDTO> Map<String, Object> postForObjectHttpClient(final BusRestAuthDTO auth, final T body,
																		final BusRestHeaderDTO header, String url) {

		HttpHeaders headers = createHeadersPostTo(auth, header);
		HttpEntity<T> request = new HttpEntity<T>(body, headers);

		Object resp = retryTemplate.execute(new RetryCallback<Object, ConciliacionException>() {
			public Object doWithRetry(RetryContext context) throws ConciliacionException {
				Object resp = null;
				LOG.info("postForObject: {}, intento: #{}", url, context.getRetryCount());
				try {
					RestTemplate restTemplate = new RestTemplate();
					resp = restTemplate.postForObject(url, request, Object.class);
				} catch (HttpClientErrorException ex) {
					ex.printStackTrace();
					throw ex;
				}
				return resp;
			}
		});

		Map<String, Object> obj = (LinkedHashMap<String, Object>) resp;

		return obj;
	}

}
