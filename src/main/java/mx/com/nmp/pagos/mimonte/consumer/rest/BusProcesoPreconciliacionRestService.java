/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestPreconciliacionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name BusProcesoPreconciliacionRestService
 * @description Clase para consumir servicios rest relacionados con la ejecución
 *              del proceso de pre-conciliación expuesto por BUS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 06/12/2021 09:46 hrs.
 */
@Component("busProcesoPreconciliacionRestService")
public class BusProcesoPreconciliacionRestService extends AbstractOAuth2RestService {

	public BusProcesoPreconciliacionRestService() {
		super();
	}

	/**
	 * Lanza la ejecución del proceso de pre-conciliación.
	 * 
	 * @param body
	 */
	public Map<String, Object> ejecutarProcesoPreconciliacion(BusRestPreconciliacionDTO body) {

		Map<String, Object> response = null;
		BusRestAuthDTO auth;
		String bearerToken;
		String url;
		BusRestHeaderDTO header;

		// Se obtiene el token
		auth = new BusRestAuthDTO(
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getAuth().getUsuario() : "",
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getAuth().getPassword() : "");
		bearerToken = postForGetToken(auth, mc != null ? mc.urlGetToken: "");

		// Se obtiene la url del servicio
		url = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getUrl() : "";

		// Se crea el Header de la petición
		header = new BusRestHeaderDTO(bearerToken);

		// Se lanza la ejecución del proceso de pre-conciliación
		response = postForObjectHttpClient(auth, body, header, url);

		return response;
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
		String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-Type", "application/json");
		headers.add( mc != null ? mc.idConsumidorKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getHeader().getIdConsumidor() : "");
		headers.add( mc != null ? mc.idDestinoKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getHeader().getIdDestino() : "");
		headers.add( mc != null ? mc.usuarioKey : "",	applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getHeader().getUsuario() : "");
		headers.add("oauth.bearer", header.getBearerToken());
		return headers;
	}

}
