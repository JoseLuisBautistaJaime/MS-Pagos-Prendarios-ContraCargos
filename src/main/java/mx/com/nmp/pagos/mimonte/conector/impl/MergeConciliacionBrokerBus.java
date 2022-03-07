/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MergeConciliacionBroker;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMergeConciliacionRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMergeConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MergeConciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

/**
 * Referencia al servicio web que concilia los movimientos de las distintas fuentes cargadas.
 *
 * @author jmreveles
 */
@Component
@Service("mergeConciliacionBrokerBus")
public class MergeConciliacionBrokerBus implements MergeConciliacionBroker {

	private Logger LOGGER = LoggerFactory.getLogger(MergeConciliacionBrokerBus.class);

	@Autowired
	private BusMergeConciliacionRestService busMergeConciliacionRestService;


	/**
	 * {@inheritDoc}
	 */

	@Override
	public MergeConciliacionResponseDTO generarMergeConciliacion(Long folio) throws ConciliacionException {
		LOGGER.info(">> ejecuta proceso de conciliación de los movimientos cargados ({})", folio);

		MergeConciliacionResponseDTO resultado = new MergeConciliacionResponseDTO();

		BusRestMergeConciliacionDTO request = new BusRestMergeConciliacionDTO(folio);

		Map<String, Object> response = null;
		try {
			response = busMergeConciliacionRestService.generarMergeConciliacion(request);
		}
		catch (HttpClientErrorException ex) {
			//throw ex;
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		boolean statusResponse = (null != response && null != response.get("codigo") &&  null == response.get("codigoError"));

		LOGGER.debug(statusResponse? "Se generó la conciliación de movimientos" : "Error al generar la conciliación de los movimientos cargados");

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
