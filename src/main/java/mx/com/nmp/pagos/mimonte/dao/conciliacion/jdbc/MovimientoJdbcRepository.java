package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;

/**
 * Interface con las operaciones de insercion/actualizacion de movimientos en batch
 * @author Quarksoft
 *
 */
public interface MovimientoJdbcRepository {

	/**
	 * Inserta en batch los movimientos de tipo reporte
	 * @param lista
	 * @throws Exception
	 */
	public void insertarLista(List<? extends MovimientoReporte> lista) throws Exception;
	
}
