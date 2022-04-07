/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.GestionConciliacionBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusGestionConciliacionRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGestionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GestionConciliacionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Referencia al servicio web que crea el proceso de conciliación.
 *
 * @author jmreveles
 */
@Component
@Service("gestionConciliacionBrokerBus")
public class GestionConciliacionBrokerBus implements GestionConciliacionBroker {

	private Logger logger = LoggerFactory.getLogger(GestionConciliacionBrokerBus.class);

	@Autowired
	private BusGestionConciliacionRestService busGestionConciliacionRestService;


	/**
	 * {@inheritDoc}
	 */

	@Override
	public GestionConciliacionResponseDTO crearConciliacion(Long idCuenta, Long idEntidad, String idCorresponsal)  {
		logger.info(">> ejecuta la creación del proceso de conciliación ({}/{}/{})", idCuenta,idEntidad,idCorresponsal);

		GestionConciliacionResponseDTO resultado = new GestionConciliacionResponseDTO();

		BusRestGestionConciliacionDTO request = new BusRestGestionConciliacionDTO(idCuenta, idEntidad, idCorresponsal);

		Map<String, Object> response = null;
		try {
			response = busGestionConciliacionRestService.crearProcesoConciliacion(request);
		} catch (HttpClientErrorException ex) {
			resultado.setFolio("0");
			resultado.setCargaCorrecta(false);
			resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
			resultado.setMensaje(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
			return resultado;
		}

		if(response != null ) {

			boolean statusResponse = ( null != response.get("codigo") &&  null == response.get("codigoError"));
			logger.debug(statusResponse? "Se creó el proceso de conciliación" : "Error al crear el proceso de conciliación");

			if (!statusResponse) {
				resultado.setCargaCorrecta(false);
				resultado.setCodigo(response.get("codigoError").toString());
				resultado.setMensaje(response.get("tipoError").toString()+" - "+response.get("descripcionError").toString());
				resultado.setFolio("0");
			} else {
				resultado.setCargaCorrecta(true);
				resultado.setCodigo(response.get("codigo").toString());
				resultado.setMensaje(response.get("descripcion").toString());
				resultado.setFolio(((LinkedHashMap<String, Object>) (response.get("conciliacion"))).get("folio").toString());
			}

		} else {
			resultado.setFolio("0");
			resultado.setCargaCorrecta(false);
			resultado.setCodigo("404");
			resultado.setMensaje(ConciliacionConstants.ERROR_RESPONSE_PETICION);
			logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
		}


		return resultado;
	}
}
