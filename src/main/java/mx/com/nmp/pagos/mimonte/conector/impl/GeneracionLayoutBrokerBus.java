package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.GeneracionLayoutBroker;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusGeneracionLayoutRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGeneracionLayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneracionLayoutResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @description
 *
 * @author omarrb
 */
@Component
@Service("GeneracionLayoutBrokerBus")
public class GeneracionLayoutBrokerBus implements GeneracionLayoutBroker {

    private Logger LOGGER = LoggerFactory.getLogger(GeneracionLayoutBrokerBus.class);

    @Autowired
    private BusGeneracionLayoutRestService BusGeneracionLayoutRestService;

    /**
     * Temporal format para los LOGs de timers
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public GeneracionLayoutResponseDTO cargarGenerarLayout(Long folio, String estadoEnvio) throws ConciliacionException{
    LOGGER.info(">> Se ejecuta proceso del estado de envio ({}/{}/{})", folio, estadoEnvio);

    GeneracionLayoutResponseDTO resultado = new GeneracionLayoutResponseDTO();

    BusRestGeneracionLayoutDTO request = new BusRestGeneracionLayoutDTO(folio, estadoEnvio);

    Map<String, Object> response = null;
		try {
        response = BusGeneracionLayoutRestService.cargarGeneracionLayout(request);
    }
		catch (Exception ex) {
        //throw ex;
        resultado.setRespuestaCorrecta(false);
        resultado.setMessage(ex.getMessage());
        return resultado;
    }

    boolean statusResponse = (null != response && null != response.get("codigo") &&  null == response.get("codigoError"));

		LOGGER.debug(statusResponse? "Respuesta correcta" : "Error al obtener respuesta");

		if (!statusResponse) {
        resultado.setRespuestaCorrecta(false);
        resultado.setCodigo(response.get("codigoError").toString());
        resultado.setDescripcion(response.get("tipoError").toString());
        resultado.setMessage(response.get("descripcionError").toString());
    } else {
        resultado.setRespuestaCorrecta(true);
        resultado.setCodigo(response.get("codigo").toString());
        resultado.setDescripcion(response.get("descripcion").toString());
        resultado.setMessage(response.get("descripcion").toString());
    }

		return resultado;


}







