/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MovimientosProveedorBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosProveedorRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestCorresponsalDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosProveedorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.Map;

/**
 * Referencia al servicio web que carga los movimientos del proveedor
 *
 * @author jmreveles
 */
@Component
@Service("movimientosProveedorBrokerBus")
public class MovimientosProveedorBrokerBus implements MovimientosProveedorBroker {

	private Logger logger = LoggerFactory.getLogger(MovimientosProveedorBrokerBus.class);

	@Autowired
	private BusMovimientosProveedorRestService busMovimientosProveedorRestService;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public MovimientosProveedorResponseDTO cargarMovimientosProveedor(Long folio, Date fechaInicio, Date fechaFin, String corresponsal, Long estadoProceso, String proveedor)  {

		MovimientosProveedorResponseDTO resultado = new MovimientosProveedorResponseDTO();

		BusRestMovimientosProveedorDTO request = new BusRestMovimientosProveedorDTO(folio, fechaInicio, fechaFin, new BusRestCorresponsalDTO(corresponsal), estadoProceso, proveedor);

		logger.info(">> ejecuta proceso de carga de movimientos del proveedor ({}/{}/{}/{}/{})", request.getFechaInicio(), request.getFechaFin(), request.getCorresponsal().getIdCorresponsal(), request.getEstadoProceso(), request.getProveedor());

		Map<String, Object> response = null;
		try {
			response = busMovimientosProveedorRestService.cargarMovimientosProveedor(request);
		}
		catch (HttpClientErrorException ex) {
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("codigo") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Carga correcta" : "Error al cargar los movimientos del proveedor");

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

		} else {
			resultado.setCargaCorrecta(false);
			resultado.setCodigo("404");
			resultado.setMessage(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}

		return resultado;
	}
}
