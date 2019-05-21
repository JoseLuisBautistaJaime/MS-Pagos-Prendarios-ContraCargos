/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.ws.EstadoCuentaEndpointService;
import mx.com.nmp.pagos.mimonte.conector.ws.EstadoCuentaService;
import mx.com.nmp.pagos.mimonte.util.WSSecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Se encarga de crear una referencia hacia el Servicio Web Estado de Cuenta
 *
 * @author jgalvez
 */
//@Component
public class EstadoCuentaConector {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstadoCuentaConector.class);

    /**
     * Contienen la URL donde se encuentra publicado el Servicio Web OpenPay
     */
    @Value("${estadocuenta.wsdlLocation}")
    private String wsdlLocation;

    /**
     * Header name
     */
    @Value("${estadocuenta.header.api.name}")
    private String apiName;

    /**
     * Token value
     */
    @Value("${estadocuenta.header.api.key}")
    private String apiKey;

    /**
     * Referencia hacia el Servicio Web
     */
    private EstadoCuentaService estadoCuentaService;

    /**
     * Regresa la referencia hacia el Servicio Web Estado Cuenta
     *
     * @return Referencia hacia el Servicio Web
     */
    public EstadoCuentaService getReferenciaWsEstadoCuenta() {
        if (ObjectUtils.isEmpty(estadoCuentaService)) {
        	crearReferenciaEstadoCuenta();
        }

        LOGGER.info("Recuperando referencia al WS.");
        return estadoCuentaService;
    }

    /**
     * Crea una referencia hacia el Servicio Web
     */
    private void crearReferenciaEstadoCuenta() {
        EstadoCuentaEndpointService estadoCuentaService = null;

        URL url = getLocalURL();

        if (ObjectUtils.isEmpty(url)) {
            LOGGER.info("Creando referencia al WS con valores por defecto");
            url = null;
            estadoCuentaService = new EstadoCuentaEndpointService(url);
        } else {
            LOGGER.info("Creando referencia al WS con URL {}", url);
            estadoCuentaService = new EstadoCuentaEndpointService(url);
        }

        estadoCuentaService = (EstadoCuentaEndpointService) WSSecurityUtils.createService(
    		estadoCuentaService.getEstadoCuentaEndpointPort(),
    		getURL(),
    		apiName,
    		apiKey,
    		"http://nmp.com.mx/ms/pagos/mimonte/ws/estadocuenta/"
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
        String wsdlLocalLocation = "client-api-definition/EstadoCuenta.wsdl";

        URL url = null;
        try {
        	url = EstadoCuentaConector.class.getResource(wsdlLocalLocation);
            LOGGER.info("Creando URL con {}", wsdlLocalLocation);
        } catch (Exception e) {
            LOGGER.warn("La URL no es valida. {}", wsdlLocalLocation, e);
        }

        return url;
    }

}
