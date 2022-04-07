package mx.com.nmp.pagos.mimonte.conector.impl;

import mx.com.nmp.pagos.mimonte.conector.GeneracionLayoutBroker;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusGeneracionLayoutRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGeneracionLayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneracionLayoutResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

/**
 * @description
 *
 * @author omarrb
 */
@Component
@Service("GeneracionLayoutBrokerBus")
public class GeneracionLayoutBrokerBus implements GeneracionLayoutBroker {

    private Logger logger = LoggerFactory.getLogger(GeneracionLayoutBrokerBus.class);

    @Autowired
    private BusGeneracionLayoutRestService busGeneracionLayoutRestService;


    /**
     * {@inheritDoc}
     */
    @Override
    public GeneracionLayoutResponseDTO generarLayouts(Long folio, Integer estadoEnvio)  {
        logger.info(">> Se ejecuta proceso de generación de Layout ({}/{})", folio, estadoEnvio);

        GeneracionLayoutResponseDTO resultado = new GeneracionLayoutResponseDTO();

        BusRestGeneracionLayoutDTO request = new BusRestGeneracionLayoutDTO(folio, estadoEnvio);

        Map<String, Object> response = null;
        try {
            response = busGeneracionLayoutRestService.generarLayouts(request);
        } catch (HttpClientErrorException ex) {
            resultado.setRespuestaCorrecta(false);
            resultado.setCodigo(String.valueOf(ex.getStatusCode().value()));
            resultado.setMessage(ex.getMessage()+" : \n "+ex.getResponseBodyAsString());
            resultado.setDescripcion(ex.getResponseBodyAsString());
            return resultado;
        }

        if(response != null ) {

            boolean statusResponse = ( null != response.get("codigo") && null == response.get("codigoError"));
            logger.debug(statusResponse ? "Respuesta correcta" : "Respuesta incorrecta");

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
        } else {
            resultado.setRespuestaCorrecta(false);
            resultado.setCodigo("404");
            resultado.setMessage(ConciliacionConstants.ERROR_RESPONSE_PETICION);
            logger.debug(ConciliacionConstants.ERROR_RESPONSE_PETICION);
        }

        return resultado;

    }

}





