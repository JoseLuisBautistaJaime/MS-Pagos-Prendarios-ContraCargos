/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosNocturnosResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

import java.util.Date;

/**
 * Referencia al servicio que carga los movimientos nocturnos de MIDAS
 *
 * @author jmreveles
 */
public interface MovimientosNocturnosBroker {

    /**
     * Ejecuta el proceso de carga de los movimientos nocturnos de MIDAS
     * @param folio
     * @param fechaInicio
     * @param fechaFin
     * @param corresponsal
     * @param estadoProceso
     * @return
     */
	public MovimientosNocturnosResponseDTO cargarMovimientosNocturnos(Long folio,Date fechaInicio, Date fechaFin, String corresponsal, Long estadoProceso) throws ConciliacionException;

}
