/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosNocturnosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

/**
 * @name BusMovimientosNocturnosRestService
 * @description Clase para consumir servicios rest relacionados con el
 *               proceso de carga de los movimientos nocturnos de MIDAS expuesto por BUS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 07/01/2022 13:46 hrs.
 */
@Component("busMovimientosNocturnosRestService")
public class BusMovimientosNocturnosRestService extends AbstractOAuth2RestService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BusMovimientosNocturnosRestService.class);

	public BusMovimientosNocturnosRestService() {
		super();
	}

	/**
	 * Lanza el proceso de carga de los movimientos nocturnos de MIDAS.
	 * 
	 * @param body
	 */
	public Map<String, Object> cargarMovimientosNocturnos(BusRestMovimientosNocturnosDTO body) {

		Map<String, Object> response = null;
		BusRestAuthDTO auth;
		String bearerToken;
		String url;
		BusRestHeaderDTO header;

		try {

			// Se obtiene el token
			auth = new BusRestAuthDTO(
					applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getUsuario(),
					applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getPassword());
			bearerToken = postForGetToken(auth, mc.urlGetToken);

			// Se obtiene la url del servicio
			url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMovimientosNocturnos();

			// Se crea el Header de la petición
			header = new BusRestHeaderDTO(bearerToken);

			// Se lanza el proceso
			response = postForObjectHttpClient(auth, body, header, url);

		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
			throw ex;
		}

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
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/json");
		headers.add(mc.idConsumidorKey, applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdConsumidor());
		headers.add(mc.idDestinoKey, applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdDestino());
		headers.add(mc.usuarioKey,	applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getUsuario());
		headers.add("oauth.bearer", header.getBearerToken());
		return headers;
	}

}
