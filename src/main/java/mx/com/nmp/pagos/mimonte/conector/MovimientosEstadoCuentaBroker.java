/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

import java.util.Date;

/**
 * Referencia al servicio que carga los movimientos de estado de cuenta.
 *
 * @author jmreveles
 */
public interface MovimientosEstadoCuentaBroker {

    /**
     * Ejecuta el proceso de carga de los movimientos del estado de cuenta.
     * @param folio
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
	public MovimientosEstadoCuentaResponseDTO cargarMovimientosEstadoCuenta(Long folio, Date fechaInicial, Date fechaFinal) throws ConciliacionException;

}
