/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper;

import java.util.List;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

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
	 * @param idEstatusConciliacion
	 * @return
	 * @throws ConciliacionException
	 */
	public Conciliacion getConciliacionByFolio(Long folio, Integer idEstatusConciliacion) throws ConciliacionException;

	/**
	 * Se encarga de generar/regenerar la conciliacion en base a el(los) reporte(s) recibido(s)
	 * @param folio
	 * @param idEntidad
	 * @param reportes
	 * @param corresponsal
	 * @throws ConciliacionException
	 */
	public void generarConciliacion(Long folio, Long idEntidad, List<Reporte> reportes, CorresponsalEnum corresponsal) throws ConciliacionException;

	/**
	 * Se encarga de obtener la proyeccion asignada a la conciliacion
	 * @param folio
	 * @return
	 * @throws ConciliacionException
	 */
	public ComisionTransaccion getComisionTransaccion(Long folio) throws ConciliacionException;

	/**
	 * Obtiene el folio de la conciliacion por id
	 * @param idConciliacion
	 * @return
	 * @throws ConciliacionException
	 */
	public Long getFolioConciliacionById(Long idConciliacion) throws ConciliacionException;

}
