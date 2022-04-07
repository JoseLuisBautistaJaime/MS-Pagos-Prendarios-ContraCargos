/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MovimientosNocturnosBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosNocturnosRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestCorresponsalDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosNocturnosResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.Map;

/**
 * Referencia al servicio web que carga los movimientos nocturnos de MIDAS
 *
 * @author jmreveles
 */
@Component
@Service("movimientosNocturnosBrokerBus")
public class MovimientosNocturnosBrokerBus implements MovimientosNocturnosBroker {

	private Logger logger = LoggerFactory.getLogger(MovimientosNocturnosBrokerBus.class);

	@Autowired
	private BusMovimientosNocturnosRestService busMovimientosNocturnosRestService;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public MovimientosNocturnosResponseDTO cargarMovimientosNocturnos(Long folio, Date fechaInicio, Date fechaFin, String corresponsal, Long estadoProceso)  {

		MovimientosNocturnosResponseDTO resultado = new MovimientosNocturnosResponseDTO();

		BusRestMovimientosNocturnosDTO request = new BusRestMovimientosNocturnosDTO(folio, fechaInicio, fechaFin, new BusRestCorresponsalDTO(corresponsal), estadoProceso);

		logger.info(">> ejecuta proceso de carga de movimientos nocturnos ({}/{}/{})", request.getFechaInicio(), request.getFechaFin(), request.getCorresponsal().getIdCorresponsal());

		Map<String, Object> response = null;
		try {
			response = busMovimientosNocturnosRestService.cargarMovimientosNocturnos(request);
		}
		catch (HttpClientErrorException ex) {
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setCargaCorrecta(false);
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("codigo") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Carga correcta" : "Error al cargar los movimientos nocturnos");

			if (!statusResponse) {
				resultado.setCodigo(response.get("codigoError").toString());
				resultado.setDescripcion(response.get("tipoError").toString());
				resultado.setMessage(response.get("descripcionError").toString());
				resultado.setCargaCorrecta(false);
			} else {
				resultado.setCodigo(response.get("codigo").toString());
				resultado.setDescripcion(response.get("descripcion").toString());
				resultado.setMessage(response.get("descripcion").toString());
				resultado.setCargaCorrecta(true);
			}

		} else {
			resultado.setCargaCorrecta(false);
			resultado.setCodigo("404");
			resultado.setDescripcion(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			resultado.setMessage(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}

		return resultado;
	}
}
