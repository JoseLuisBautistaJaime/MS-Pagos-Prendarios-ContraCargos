/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @name ConciliacionHelper
 * @description Clase helper para la creacion de la conciliacion semanal
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
public interface ConciliacionSemanalHelper {

	/**
	 * Construye una nueva conciliacion usando las conciliaciones seleccionadas
	 * @param fechaConciliacion
	 * @param conciliacionesIds
	 * @param createdBy
	 * @return
	 * @throws ConciliacionException
	 */
	public ConciliacionDTO crearConciliacionSemanal(Date fechaConciliacion, List<Long> conciliacionesIds, String createdBy) throws ConciliacionException;

}
