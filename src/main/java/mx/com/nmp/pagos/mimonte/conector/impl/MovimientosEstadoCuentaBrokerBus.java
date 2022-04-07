/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MovimientosEstadoCuentaBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosEstadoCuentaRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Date;
import java.util.Map;

/**
 * Referencia al servicio web que carga los movimientos del estado de cuenta
 *
 * @author jmreveles
 */
@Component
@Service("MovimientosEstadoCuentaBrokerBus")
public class MovimientosEstadoCuentaBrokerBus implements MovimientosEstadoCuentaBroker {

	private Logger logger = LoggerFactory.getLogger(MovimientosEstadoCuentaBrokerBus.class);

	@Autowired
	private BusMovimientosEstadoCuentaRestService busMovimientosEstadoCuentaRestService;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public MovimientosEstadoCuentaResponseDTO cargarMovimientosEstadoCuenta(Long folio, Date fechaInicial, Date fechaFinal)  {

		MovimientosEstadoCuentaResponseDTO resultado = new MovimientosEstadoCuentaResponseDTO();

		BusRestMovimientosEstadoCuentaDTO request = new BusRestMovimientosEstadoCuentaDTO(folio, fechaInicial, fechaFinal);

		logger.info(">> ejecuta proceso de carga de movimientos del estado de cuenta ({}/{}/{})", request.getFolio(), request.getFechaInicial(),request.getFechaFinal());

		Map<String, Object> response = null;
		try {
			response = busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(request);
		}
		catch (HttpClientErrorException ex) {
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			resultado.setCargaCorrecta(false);
			return resultado;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("code") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Carga correcta" : "Error al cargar los movimientos del estado de cuenta");

			if (statusResponse) {
				resultado.setCargaCorrecta(true);
				resultado.setCodigo(response.get("code").toString());
				resultado.setDescripcion(response.get("message").toString());
				resultado.setMessage(response.get("message").toString());
			} else {
				resultado.setCargaCorrecta(false);
				resultado.setCodigo(response.get("code").toString());
				resultado.setDescripcion(response.get("message").toString());
				resultado.setMessage("Error al cargar los movimientos del estado de cuenta");
			}

		} else {
			resultado.setCargaCorrecta(false);
			resultado.setCodigo("404");
			resultado.setMessage(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}

		return resultado;
	}
}
