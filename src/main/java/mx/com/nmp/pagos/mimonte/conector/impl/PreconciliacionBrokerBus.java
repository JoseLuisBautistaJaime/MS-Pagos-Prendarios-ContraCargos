/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.PreconciliacionBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusProcesoPreconciliacionRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestCorresponsalDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestRangoFechasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProcesoPreconciliacionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;

/**
 * Referencia al servicio web que ejecuta el proceso de pre-conciliación
 *
 * @author jmreveles
 */
@Component
@Service("preconciliacionBrokerBus")
public class PreconciliacionBrokerBus implements PreconciliacionBroker {

	private Logger logger = LoggerFactory.getLogger(PreconciliacionBrokerBus.class);

	@Autowired
	private BusProcesoPreconciliacionRestService busProcesoPreconciliacionRestService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcesoPreconciliacionResponseDTO ejecutarPreconciliacion(Date fechaPeriodoInicio, Date fechaPeriodoFin, String corresponsal) {

		ProcesoPreconciliacionResponseDTO resultado = new ProcesoPreconciliacionResponseDTO();

		// ejecuta el proceso y obtiene el resultado de la ejecución
		BusRestPreconciliacionDTO request = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(fechaPeriodoInicio, fechaPeriodoFin), new BusRestCorresponsalDTO(corresponsal));


		logger.info(">> ejecuta proceso pre-conciliación({}/{}/{})", request.getRangoFecha().getFechaInicio(), request.getRangoFecha().getFechaFin(), request.getCorresponsal().getIdCorresponsal());


		Map<String, Object> response = null;
		try {
			response = busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(request);
		}
		catch (HttpClientErrorException ex) {
			resultado.setEjecucionCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMensaje(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			resultado.setDescripcion(ex.getResponseBodyAsString());
			return resultado;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("codigo") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Ejecución correcta" : "Error al ejecutar el proceso de pre-conciliación");

			if (!statusResponse) {
				resultado.setEjecucionCorrecta(false);
				resultado.setCodigo(response.get("codigoError").toString());
				resultado.setDescripcion(response.get("tipoError").toString());
				resultado.setMensaje(response.get("descripcionError").toString());
			} else {
				resultado.setEjecucionCorrecta(true);
				resultado.setCodigo(response.get("codigo").toString());
				resultado.setDescripcion(response.get("descripcion").toString());
				resultado.setMensaje(response.get("descripcion").toString());
			}

		} else {
			resultado.setEjecucionCorrecta(false);
			resultado.setCodigo("404");
			resultado.setDescripcion(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			resultado.setMensaje(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}

		return resultado;
	}

}
