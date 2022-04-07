/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;



import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneracionLayoutResponseDTO;


/**
 *  Referencia al servicio que genera y envia los Layout.
 *
 * @author omarrb
 */
public interface GeneracionLayoutBroker {

    /**
     * Ejecuta el proceso de generación y envió de layout.
     * @param folio
     * @param estadoEnvio
     */
    public GeneracionLayoutResponseDTO generarLayouts(Long folio, Integer estadoEnvio);


}
