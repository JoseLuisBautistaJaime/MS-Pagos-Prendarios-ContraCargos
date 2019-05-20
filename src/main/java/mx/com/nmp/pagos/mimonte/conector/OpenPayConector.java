/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.ws.OpenPayEndpointService;
import mx.com.nmp.pagos.mimonte.conector.ws.OpenPayService;
import mx.com.nmp.pagos.mimonte.util.WSSecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Se encarga de crear una referencia hacia el Servicio Web OpenPay
 *
 * @author ecancino
 */
//@Component
public class OpenPayConector {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenPayConector.class);

    /**
     * Contienen la URL donde se encuentra publicado el Servicio Web OpenPay
     */
//    @Value("${openpay.wsdlLocation}")
    private String wsdlLocation;

    /**
     * Header name
     */
//    @Value("${openpay.header.api.name}")
    private String apiName;

    /**
     * Token value
     */
//    @Value("${openpay.header.api.key}")
    private String apiKey;

    /**
     * Referencia hacia el Servicio Web
     */
    private OpenPayService openPayService;

    /**
     * Regresa la referencia hacia el Servicio Web OpenPay
     *
     * @return Referencia hacia el Servicio Web
     */
    public OpenPayService getReferenciaWsOpenPay() {
        if (ObjectUtils.isEmpty(openPayService)) {
        	crearReferenciaOpenPay();
        }

        LOGGER.info("Recuperando referencia al WS.");
        return openPayService;
    }

    /**
     * Crea una referencia hacia el Servicio Web
     */
    private void crearReferenciaOpenPay() {
        OpenPayEndpointService openPayservice = null;

        URL url = getLocalURL();

        if (ObjectUtils.isEmpty(url)) {
            LOGGER.info("Creando referencia al WS con valores por defecto");
            url = null;
            openPayservice = new OpenPayEndpointService(url);
        } else {
            LOGGER.info("Creando referencia al WS con URL {}", url);
            openPayservice = new OpenPayEndpointService(url);
        }

        openPayservice = (OpenPayEndpointService) WSSecurityUtils.createService(
    		openPayservice.getOpenPayEndpointPort(),
    		getURL(),
    		apiName,
    		apiKey,
    		"http://nmp.com.mx/ms/pagos/mimonte/ws/openpay/"
		);
    }

    /**
     * Recupera la URL de las propiedades de entorno.
     *
     * @return URL.
     */
    private URL getURL() {
        URL url = null;

        if (!ObjectUtils.isEmpty(wsdlLocation)) {
            try {
                LOGGER.info("Creando URL con {}", wsdlLocation);
                url = new URL(wsdlLocation);
            } catch (MalformedURLException e) {
                LOGGER.warn("La URL no es accesible. {}", wsdlLocation, e);
            }
        }
        return url;
    }

    private URL getLocalURL() {
        String wsdlLocalLocation = "client-api-definition/ConsultaTipoCambio.wsdl";

        URL url = null;
        try {
        	url = OpenPayConector.class.getResource(wsdlLocalLocation);
            LOGGER.info("Creando URL con {}", wsdlLocalLocation);
        } catch (Exception e) {
            LOGGER.warn("La URL no es valida. {}", wsdlLocalLocation, e);
        }

        return url;
    }

}
