/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMergeConciliacionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name BusMergeConciliacionRestService
 * @description Clase para consumir servicios rest relacionados con la generación de la
 *               conciliación de los movimientos cargados de las distintas fuentes de datos.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 20/01/2022 13:46 hrs.
 */
@Component("busMergeConciliacionRestService")
public class BusMergeConciliacionRestService extends AbstractOAuth2RestService {


	public BusMergeConciliacionRestService() {
		super();
	}

	/**
	 * Lanza la generación de la conciliación de los movimientos cargados de las distintas fuentes de datos.
	 * 
	 * @param body
	 */
	public Map<String, Object> generarMergeConciliacion(BusRestMergeConciliacionDTO body) {

		String url;
		String bearerToken;
		BusRestAuthDTO auth;
		BusRestHeaderDTO header;
		Map<String, Object> response = null;

        // Se obtiene el token
		auth = new BusRestAuthDTO(
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getUsuario() : "",
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getPassword() : "");
		bearerToken = postForGetToken(auth, mc != null ? mc.urlGetToken : "");

		// Se obtiene la url del servicio
		url = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMergeConciliacion() : "";

		// Se crea el Header de la petición
		header = new BusRestHeaderDTO(bearerToken);

		// Se lanza el proceso
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
		headers.add(mc != null ? mc.idConsumidorKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdConsumidor() : "");
		headers.add("oauth.bearer", header.getBearerToken());
		headers.add("requestUser", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getRequestUser() : "");
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(mc != null ? mc.idDestinoKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdDestino() : "");
		headers.add(mc != null ? mc.usuarioKey : "",	applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getUsuario() : "");

		return headers;
	}

}
