/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosEstadoCuentaDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name BusMovimientosEstadoCuentaRestService
 * @description Clase para consumir servicios rest relacionados con el
 *               proceso de carga de los movimientos del estado de cuenta expuesto por BUS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 20/01/2022 13:46 hrs.
 */
@Component("busMovimientosEstadoCuentaRestService")
public class BusMovimientosEstadoCuentaRestService extends AbstractOAuth2RestService {

	public BusMovimientosEstadoCuentaRestService() {
		super();
	}

	/**
	 * Lanza el proceso de carga de los movimientos del estado de cuenta.
	 * 
	 * @param body
	 */
	public Map<String, Object> cargarMovimientosEstadoCuenta(BusRestMovimientosEstadoCuentaDTO body) {

		Map<String, Object> response = null;

		// Se obtiene el token
		BusRestAuthDTO auth = new BusRestAuthDTO(
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getUsuario() : "",
				applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getPassword() : "");

		String bearerToken = postForGetToken(auth, mc != null ? mc.urlGetToken : "");

		// Se obtiene la url del servicio
		String url = applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMovimientosEstadoCuenta() : "";

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
		String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("oauth.bearer", header.getBearerToken());
		headers.add("requestUser", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getRequestUser() : "");
		headers.add(mc != null ? mc.idDestinoKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdDestino() : "");
		headers.add(mc != null ? mc.idConsumidorKey : "", applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdConsumidor() : "");
		headers.add(mc != null ? mc.usuarioKey : "",	applicationProperties != null ? applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getUsuario() : "");
		return headers;
	}

}
