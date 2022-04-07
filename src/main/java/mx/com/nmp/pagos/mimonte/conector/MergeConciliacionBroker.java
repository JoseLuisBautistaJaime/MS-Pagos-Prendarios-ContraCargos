/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MergeConciliacionResponseDTO;

/**
 * Referencia al servicio que carga los movimientos de estado de cuenta.
 *
 * @author jmreveles
 */
public interface MergeConciliacionBroker {

    /**
     * Ejecuta el proceso de conciliacion entre las distintas fuentes de movimientos cargadas.
     * @param folio
     * @return
     */
	public MergeConciliacionResponseDTO generarMergeConciliacion(Long folio) ;

}
