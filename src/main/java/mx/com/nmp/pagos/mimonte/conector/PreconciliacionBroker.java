/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProcesoPreconciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import java.util.Date;

/**
 * Referencia al servicio que ejecuta el proceso de pre-conciliación
 *
 * @author jmreveles
 */
public interface PreconciliacionBroker {

    /**
     * Ejecuta el proceso de pre-conciliación
     * @param fechaPeriodoInicio
     * @param fechaPeriodoFin
     * @param corresponsal
     * @return
     */
    public ProcesoPreconciliacionResponseDTO ejecutarPreconciliacion(Date fechaPeriodoInicio, Date fechaPeriodoFin, String corresponsal) throws ConciliacionException;

    }
