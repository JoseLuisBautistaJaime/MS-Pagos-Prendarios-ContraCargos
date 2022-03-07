/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MovimientosEstadoCuentaBroker;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosEstadoCuentaRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.SimpleDateFormat;
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

	private Logger LOGGER = LoggerFactory.getLogger(MovimientosEstadoCuentaBrokerBus.class);

	@Autowired
	private BusMovimientosEstadoCuentaRestService busMovimientosEstadoCuentaRestService;

	/**
	 * Temporal format para los LOGs de timers
	 */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * {@inheritDoc}
	 */

	@Override
	public MovimientosEstadoCuentaResponseDTO cargarMovimientosEstadoCuenta(Long folio, Date fechaInicial, Date fechaFinal) throws ConciliacionException {
		LOGGER.info(">> ejecuta proceso de carga de movimientos del estado de cuenta ({}/{}/{})", folio, sdf.format(fechaInicial), sdf.format(fechaFinal));

		MovimientosEstadoCuentaResponseDTO resultado = new MovimientosEstadoCuentaResponseDTO();

		BusRestMovimientosEstadoCuentaDTO request = new BusRestMovimientosEstadoCuentaDTO(folio, fechaInicial, fechaFinal);

		Map<String, Object> response = null;
		try {
			response = busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(request);
		}
		catch (HttpClientErrorException ex) {
			//throw ex;
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		boolean statusResponse = (null != response && null != response.get("code") &&  null == response.get("codigoError"));

		LOGGER.debug(statusResponse? "Carga correcta" : "Error al cargar los movimientos del estado de cuenta");

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

		return resultado;
	}
}
