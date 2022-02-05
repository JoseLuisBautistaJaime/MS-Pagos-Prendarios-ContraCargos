/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;



import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneracionLayoutResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

import java.util.Date;

/**
 * Referencia al servicio que carga los movimientos de estado de cuenta.
 *
 * @author omarrb
 */
public interface GeneracionLayoutBroker {

    /**
     * Ejecuta el proceso de carga de los movimientos del estado de cuenta.
     * @param folio
     * @param estadoEnvio
     */
    public GeneracionLayoutResponseDTO cargarGenerarLayout(Long folio, String estadoEnvio) throws ConciliacionException;


}
