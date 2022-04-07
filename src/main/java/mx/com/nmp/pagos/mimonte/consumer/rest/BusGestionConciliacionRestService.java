/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGestionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name BusGestionConciliacionRestService
 * @description Clase para consumir servicios rest relacionados con la
 *               creación del proceso de conciliación.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 24/01/2022 13:46 hrs.
 */
@Component("busGestionConciliacionRestService")
public class BusGestionConciliacionRestService extends AbstractOAuth2RestService {


	public BusGestionConciliacionRestService() {
		super();
	}

	/**
	 * Lanza la  creación del  proceso de conciliación.
	 * 
	 * @param body
	 */
	public Map<String, Object> crearProcesoConciliacion(BusRestGestionConciliacionDTO body) {

		String url;
		String bearerToken;
		Map<String, Object> response = null;
		BusRestAuthDTO auth;

		// Se obtiene el token
		auth = new BusRestAuthDTO(
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getUsuario() : "",
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getPassword() : "");
		bearerToken = postForGetToken(auth, mc != null ? mc.urlGetToken : "");

		// Se obtiene la url del servicio
		url = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlGestionConciliacion() : "";

		// Se crea el Header de la petición
		BusRestHeaderDTO header = new BusRestHeaderDTO(bearerToken);

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

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("oauth.bearer", header.getBearerToken());
		headers.add("Content-Type", "application/json");
		headers.add( mc != null ? mc.idConsumidorKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdConsumidor() : "");
		headers.add( mc != null ? mc.usuarioKey : "",	applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getUsuario() : "");
		headers.add( mc != null ? mc.idDestinoKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdDestino() : "");
		String base64Creds = buildBase64Hash(auth);
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}

}
