/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestConsultaPagosDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestConsultaPagosResponseDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @name BusConsultaPagosRestService
 * @description Clase para consultar el listado de pagos por referencia
 * @version 1.0
 */
@Component("busConsultaPagosRestService")
public class BusConsultaPagosRestService extends AbstractOAuth2RestService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BusConsultaPagosRestService.class);

	public BusConsultaPagosRestService() {
		super();
	}


	public BusRestConsultaPagosResponseDTO consultaPagos(BusRestConsultaPagosDTO body) {

		// Se consulta el archivo de estado de cuenta
		String url = applicationProperties.getMimonte().getVariables().getConsultaPagos().getUrl();

		BusRestHeaderDTO busHeader = new BusRestHeaderDTO("");
		
		BusRestConsultaPagosResponseDTO response = null;
		try {
			response = postForObject(body, busHeader, url);
			LOG.debug("res=" + response);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("No se puede consultar el servicio de consulta de pagos",
					CodigoError.NMP_PMIMONTE_BUSINESS_SERVICIO_PAGOS);
		}

		LOG.debug(response != null ? "Consulta correcta" : "Error al consultar servicio de pagos");

		/*if (response == null)
			throw new ConciliacionException("No se puede consultar el servicio de consulta de pagos",
					CodigoError.NMP_PMIMONTE_BUSINESS_SERVICIO_PAGOS);
*/
		return response;
	}

	/**
	 * Consume servicio de endpoint para obtencion de token
	 * 
	 * @param auth
	 * @return
	 */
	public BusRestConsultaPagosResponseDTO postForObjectJson(final BusRestConsultaPagosDTO params,
			final BusRestHeaderDTO header, final String url) throws ConciliacionException {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("corresponsalFolio", params.getCorresponsalFolio());
		map.add("idPagoCorresponsal", params.getIdPagoCorresponsal());

		HttpHeaders headers = createHeadersPostTo(null, null);
		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		// Retries
		BusRestConsultaPagosResponseDTO obj = retryTemplate
				.execute(new RetryCallback<BusRestConsultaPagosResponseDTO, ConciliacionException>() {
					public BusRestConsultaPagosResponseDTO doWithRetry(RetryContext context) throws ConciliacionException {
						RestTemplate restTemplate = new RestTemplate();
						ResponseEntity<BusRestConsultaPagosResponseDTO> response = null;
						LOG.info("postForObjectJson: {}, intento: #{}", url, context.getRetryCount());
						try {
							response = restTemplate.exchange(url, HttpMethod.POST, request2,
									BusRestConsultaPagosResponseDTO.class);
						} catch (Exception ex) {
							ex.printStackTrace();
							throw ex;
						}
						return response.getBody();
					}
				});

		return obj;
	}

	/**
	 * Consume servicio de endpoint para obtencion de token
	 * 
	 * @param auth
	 * @return
	 */
	public BusRestConsultaPagosResponseDTO postForObject(final BusRestConsultaPagosDTO params,
			final BusRestHeaderDTO header, final String url) throws ConciliacionException {

		String json = "{" + 
			    "\"idPagoCorresponsal\": \"OXXO\"," +
			    "\"corresponsalFolio\" : \"" + params.getCorresponsalFolio() + "\"" +
			"}";

		HttpHeaders headers = createHeadersPostTo(null, null);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		BusRestConsultaPagosResponseDTO resp = retryTemplate.execute(new RetryCallback<BusRestConsultaPagosResponseDTO, ConciliacionException>() {
			public BusRestConsultaPagosResponseDTO doWithRetry(RetryContext context) throws ConciliacionException {
				BusRestConsultaPagosResponseDTO resp = null;
				LOG.info("postForObject: {}, intento: #{}", url, context.getRetryCount());
				try {
					RestTemplate restTemplate = new RestTemplate();
					resp = restTemplate.postForObject(url, request, BusRestConsultaPagosResponseDTO.class);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
				return resp;
			}
		});

		return resp;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.consumer.rest.AbstractOAuth2RestService#
	 * createHeadersPostTo(mx.com.nmp.pagos.mimonte.consumer.rest.dto.
	 * BusRestAuthDTO, mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO)
	 */
	@Override
	protected HttpHeaders createHeadersPostTo(BusRestAuthDTO auth, BusRestHeaderDTO header) {
		//String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Basic " + base64Creds);
		//headers.add("Content-Type", "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add(mc.idConsumidorKey, applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta()
		//		.getHeader().getIdConsumidor());
		//headers.add(mc.idDestinoKey,
		//		applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getHeader().getIdDestino());
		//headers.add(mc.usuarioKey,
		//		applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getHeader().getUsuario());
		//headers.add("oauth.bearer", header.getBearerToken());
		return headers;
	}

}
