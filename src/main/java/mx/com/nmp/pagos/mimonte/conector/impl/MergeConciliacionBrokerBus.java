/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.MergeConciliacionBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMergeConciliacionRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMergeConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MergeConciliacionResponseDTO;
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

	private Logger logger = LoggerFactory.getLogger(MergeConciliacionBrokerBus.class);

	@Autowired
	private BusMergeConciliacionRestService busMergeConciliacionRestService;


	/**
	 * {@inheritDoc}
	 */

	@Override
	public MergeConciliacionResponseDTO generarMergeConciliacion(Long folio)  {
		logger.info(">> ejecuta proceso de conciliación de los movimientos cargados ({})", folio);

		MergeConciliacionResponseDTO resultadoMerge = new MergeConciliacionResponseDTO();

		BusRestMergeConciliacionDTO request = new BusRestMergeConciliacionDTO(folio);

		Map<String, Object> response = null;
		try {
			response = busMergeConciliacionRestService.generarMergeConciliacion(request);
		}
		catch (HttpClientErrorException ex) {
			resultadoMerge.setCargaCorrecta(false);
			resultadoMerge.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			resultadoMerge.setCodigo(String.valueOf(ex.getStatusCode().value()));
			return resultadoMerge;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("codigo") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Se generó la conciliación de movimientos" : "Error al generar la conciliación de los movimientos cargados");

			if (!statusResponse) {
				resultadoMerge.setCargaCorrecta(false);
				resultadoMerge.setMessage(response.get("descripcionError").toString());
				resultadoMerge.setCodigo(response.get("codigoError").toString());
				resultadoMerge.setDescripcion(response.get("tipoError").toString());
			} else {
				resultadoMerge.setCargaCorrecta(true);
				resultadoMerge.setCodigo(response.get("codigo").toString());
				resultadoMerge.setMessage(response.get("descripcion").toString());
				resultadoMerge.setDescripcion(response.get("descripcion").toString());
			}

		} else {
			resultadoMerge.setCargaCorrecta(false);
			resultadoMerge.setCodigo("404");
			resultadoMerge.setMessage(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}

		return resultadoMerge;
	}
}
