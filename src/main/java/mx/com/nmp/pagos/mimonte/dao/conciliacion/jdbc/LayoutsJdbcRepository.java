package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;

public interface LayoutsJdbcRepository {

	/**
	 * Crea una lista de sentencias SQL (llamadas a una funcion) para insercion en
	 * batch de layouts
	 * 
	 * @param layoutList
	 * @param functionName
	 * @throws Exception
	 */
	public void insertarListaLayout(List<Layout> layoutList, String functionName) throws Exception;

	/**
	 * Crea una lista de sentencias SQL (llamadas a una funcion) para insercion en
	 * batch de headers de layouts
	 * 
	 * @param layoutHeaderList
	 * @param functionName
	 * @throws Exception
	 */
	public void insertarListaLayoutHeader(List<LayoutHeader> layoutHeaderList, String functionName) throws Exception;

	/**
	 * Crea una lista de sentencias SQL (llamadas a una funcion) para insercion en
	 * batch de lineas de layouts
	 * 
	 * @param layoutLineaList
	 * @param functionName
	 * @throws Exception
	 */
	public void insertarListaLayoutLinea(List<LayoutLinea> layoutLineaList, String functionName) throws Exception;
}
