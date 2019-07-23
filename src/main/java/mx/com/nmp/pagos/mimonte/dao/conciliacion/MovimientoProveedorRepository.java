/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;

/**
 * @name MovimientoProveedorRepository
 * @description Realiza operaciones a nivel base de datos relacionados con
 *              movimientos de proveedor
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:02 PM
 */
@Repository("movimientoProveedorRepository")
public interface MovimientoProveedorRepository extends PagingAndSortingRepository<MovimientoProveedor, Long> {

	/**
	 * Regresa los movimientos midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @param pageable
	 * @return
	 */
	@Query("SELECT mp FROM MovimientoProveedor mp INNER JOIN Reporte r ON mp.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId")
	public List<MovimientoProveedor> findByReporteConciliacionId(@Param("conciliacionId") final Integer conciliacionId
			/*,Pageable pageable*/);

	/**
	 * Regresa el total de registros midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT COUNT(mp.id) FROM MovimientoProveedor mp INNER JOIN Reporte r ON mp.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId")
	public Long countByReporteConciliacionId(@Param("conciliacionId") final Integer conciliacionId);

	/**
	 * Regresa los movimientos proveedor por id de reporte
	 * @param idReporte
	 * @return
	 */
	public List<MovimientoProveedor> findByReporte(@Param("reporteId") final Integer reporteId);

}