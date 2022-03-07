/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MovimientosNocturnosBroker;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosNocturnosRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestCorresponsalDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosNocturnosResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Referencia al servicio web que carga los movimientos nocturnos de MIDAS
 *
 * @author jmreveles
 */
@Component
@Service("movimientosNocturnosBrokerBus")
public class MovimientosNocturnosBrokerBus implements MovimientosNocturnosBroker {

	private Logger LOGGER = LoggerFactory.getLogger(MovimientosNocturnosBrokerBus.class);

	@Autowired
	private BusMovimientosNocturnosRestService busMovimientosNocturnosRestService;

	/**
	 * Temporal format para los LOGs de timers
	 */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


	/**
	 * {@inheritDoc}
	 */

	@Override
	public MovimientosNocturnosResponseDTO cargarMovimientosNocturnos(Long folio, Date fechaInicio, Date fechaFin, String corresponsal, Long estadoProceso) throws ConciliacionException {

		LOGGER.info(">> ejecuta proceso de carga de movimientos nocturnos ({}/{}/{})", sdf.format(fechaInicio), sdf.format(fechaFin), corresponsal);

		MovimientosNocturnosResponseDTO resultado = new MovimientosNocturnosResponseDTO();

		BusRestMovimientosNocturnosDTO request = new BusRestMovimientosNocturnosDTO(folio, fechaInicio, fechaFin, new BusRestCorresponsalDTO(corresponsal), estadoProceso);

		Map<String, Object> response = null;
		try {
			response = busMovimientosNocturnosRestService.cargarMovimientosNocturnos(request);
		}
		catch (HttpClientErrorException ex) {
			//throw ex;
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		boolean statusResponse = (null != response && null != response.get("codigo") &&  null == response.get("codigoError"));

		LOGGER.debug(statusResponse? "Carga correcta" : "Error al cargar los movimientos nocturnos");

		if (!statusResponse) {
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(response.get("codigoError").toString());
			resultado.setDescripcion(response.get("tipoError").toString());
			resultado.setMessage(response.get("descripcionError").toString());
		} else {
			resultado.setCargaCorrecta(true);
			resultado.setCodigo(response.get("codigo").toString());
			resultado.setDescripcion(response.get("descripcion").toString());
			resultado.setMessage(response.get("descripcion").toString());
		}
		return resultado;
	}
}
