/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;

/**
 * @name BusMailRestService
 * @description Clase para consumir servicios rest relacionados con el envio de
 *              e-mail y que son expuestos por BUS
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:46 hrs.
 */
@Component("busEstadoCuentaRestService")
public class BusEstadoCuentaRestService extends AbstractOAuth2RestService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SolicitarPagosService.class);


	public BusEstadoCuentaRestService() {
		super();
	}


	/**
	 * Envia el correo electronico el atributo bearerToken y statusResponse pueden
	 * ser enviados nulos o con con valores por deault el unico que debe ir completo
	 * (o al menos con la informacion real) es el objeto generalBusMailDTO
	 * 
	 * @param body
	 */
	public String consultaEstadoCuenta(BusRestEstadoCuentaDTO body) {

		// Se obtiene el token
		BusRestAuthDTO auth = new BusRestAuthDTO(
			applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getAuth().getUsuario(),
			applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getAuth().getPassword()
		);
		String bearerToken = postForGetToken(auth, mc.urlGetToken);

		// Se consulta el archivo de estado de cuenta
		String url = applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getUrl();
		BusRestHeaderDTO header = new BusRestHeaderDTO(
			bearerToken
		);

		Map<String, Object> response = getForObject(auth, body, header, url);
		LOG.debug(response != null ? "Consulta correcta" : "Error al consulta estado cuenta");

		if (response == null || response.get("documento") == null)
			throw new ConciliacionException(ConciliacionConstants.ESTADO_CUENTA_CANNOT_BE_CONSULT);

		return (String) response.get("documento");
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.consumer.rest.AbstractOAuth2RestService#createHeadersPostTo(mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO, mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO)
	 */
	@Override
	protected HttpHeaders createHeadersPostTo(BusRestAuthDTO auth, BusRestHeaderDTO header) {
		String base64Creds = buildBase64Hash(auth);
		HttpHeaders headers = new HttpHeaders();
		headers.add(mc.headerAuthKey, mc.headerAuthValue.concat(" ") + base64Creds);
		headers.add(mc.contentTypeKey, mc.senMailContentTypeValue);
		headers.add(mc.idConsumidorKey, applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getHeader().getIdConsumidor());
		headers.add(mc.idDestinoKey, applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getHeader().getIdDestino());
		headers.add(mc.usuarioKey, applicationProperties.getMimonte().getVariables().getConsultaEstadoCuenta().getHeader().getUsuario());
		headers.add(mc.oauthBearer, header.getBearerToken());
		return headers;
	}

}
