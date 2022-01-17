/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosProveedorResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

import java.util.Date;

/**
 * Referencia al servicio que carga los movimientos del proveedor
 *
 * @author jmreveles
 */
public interface MovimientosProveedorBroker {

    /**
     * Ejecuta el proceso de carga de los movimientos del proveedor
     * @param folio
     * @param fechaInicio
     * @param fechaFin
     * @param corresponsal
     * @param estadoProceso
     * @param proveedor
     * @return
     */
    public MovimientosProveedorResponseDTO cargarMovimientosProveedor(Long folio, Date fechaInicio, Date fechaFin, String corresponsal, Long estadoProceso, String proveedor) throws ConciliacionException;

    }
