/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutReporte;

/**
 * @name LayoutsJdbcRepository
 * @description interfaz que define las operaciones de inserciones en base de
 *              datos relacionadas con layouts mediante JDBC
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 28/11/2019 17:28 hrs.
 * @version 0.1
 */
public interface LayoutsJdbcRepository {

	/**
	 * Inserta una lista de objetos que implementan LayoutReporte mediante
	 * generacion de inserts y ejecucion de los mismos en JDBC
	 * 
	 * @param layoutHeaderList
	 * @throws Exception
	 */
	public <T extends LayoutReporte> void insertarLista(List<T> layoutHeaderList) throws Exception;

}
