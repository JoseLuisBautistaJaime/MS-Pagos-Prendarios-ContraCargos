/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.observer;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;

/**
 * Nombre: ActualizacionMovimientosObserver
 * Descripcion: Clase tipo Observer que se encarga de notificar cambios que se han realizado sobre los Movimientos de Conciliacion
 *
 * @author JGALVEZ
 * Fecha: 30/05/2019 9:44 PM
 * @version 0.1
 */
public class MovimientosObserver {

	/**
	 * Se encarga de verificar cambios en los movimientos de conciliacion para la actualizacion
	 * de la seccion global y la actualizacion de los movimientos en transito/pagos/devoluciones
	 * @param movimientoConciliacion
	 * @throws ConciliacionException
	 */
	public void update(MovimientoConciliacion movimientoConciliacion) throws ConciliacionException {
		
	}

}
