/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;

/**
 * @name MovimientoJdbcRepository
 * @description Interface con las operaciones de insercion/actualizacion de
 *              movimientos en batch
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate unknown .
 * @version 0.1
 */
public interface MovimientoJdbcRepository {

	/**
	 * Inserta en batch los movimientos de tipo reporte
	 * 
	 * @param lista
	 * @throws Exception
	 */
	public <T extends MovimientoReporte> void insertarLista(List<T> lista) throws Exception;

	/**
	 * Inserta en batch los movimientos de tipo reporte
	 * 
	 * @param lista
	 * @param storedProcedure
	 * @throws Exception
	 */
	public <T extends MovimientoConciliacion> void insertarLista(List<T> lista, String storedProcedure)
			throws Exception;

}
