/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.conector.MovimientosEstadoCuentaBroker;
import mx.com.nmp.pagos.mimonte.consumer.rest.AbstractOAuth2RestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;

import java.util.Map;

/**
 * @name BusGeneracionLayoutRestService
 * @description Clase para consumir servicios rest del envio de Layouts
 *
 * @author Omar Rodriguez orodriguez@quarksoft.net
 * @version 1.0
 * @createdDate 03/02/2022 19:23 hrs.
 */

@Component("BusGeneracionLayouRestService")
public class BusGeneracionLayoutRestService extends AbstractOAuth2RestService {

    public BusGeneracionLayoutRestService() {
        super();
    }

    /**
     * .@description
     *
     * @param body
     */
    public Map<String, Object> cargarGeneracionLayout(BusGeneracionLayoutRestService body) {

        Map<String, Object> response = null;
        String url;
        String bearerToken;
        BusRestAuthDTO auth;
        BusRestHeaderDTO header;

        try {

            // Se obtiene el token
            auth = new BusRestAuthDTO(
                    applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getUsuario(),
                    applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getAuth().getPassword());
            bearerToken = postForGetToken(auth, mc.urlGetToken);

            // Se obtiene la url del servicio
            url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlGenerarLayout();

            // Se crea el Header de la petición
            header = new BusRestHeaderDTO(bearerToken);

            // Se lanza el proceso
            response = postForObject(auth, body, header, url);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return response;

        @Override
        protected HttpHeaders createHeadersPostTo (BusRestAuthDTO auth, BusRestHeaderDTO header){
            String base64Creds = buildBase64Hash(auth);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Basic " + base64Creds);
            headers.add("Content-Type", "application/json");
            headers.add(mc.idConsumidorKey, applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdConsumidor());
            headers.add(mc.idDestinoKey, applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getIdDestino());
            headers.add(mc.usuarioKey, applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getHeader().getUsuario());
            headers.add("oauth.bearer", header.getBearerToken());
            return headers;
        }

    }