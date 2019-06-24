/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;

/**
 * @name ConciliacionHelper
 * @description Clase helper con metodos comunes usados para validacion y acciones comunes
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
public interface ConciliacionHelper {

	/**
	 * Obtiene y valida si la conciliacion existe
	 * @param folio
	 * @return
	 * @throws ConciliacionException
	 */
	public Conciliacion getConciliacionByFolio(Integer folio) throws ConciliacionException;
	
}
