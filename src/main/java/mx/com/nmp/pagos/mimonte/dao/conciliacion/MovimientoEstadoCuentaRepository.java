/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;

/**
 * @name MovimientoEstadoCuentaRepository
 * 
 * @description Realiza operaciones a nivel base de datos a objetos relacionados
 *              con movimientos de estados de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:04 PM
 */
@Repository("movimientoEstadoCuentaRepository")
public interface MovimientoEstadoCuentaRepository extends PagingAndSortingRepository<MovimientoEstadoCuenta, Long> {

	@Query("SELECT mm FROM MovimientoEstadoCuenta mm INNER JOIN Reporte r ON mm.idReporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId")
	public List<MovimientoEstadoCuenta> findByReporteConciliacionId(@Param("conciliacionId") final Long conciliacionId,
			Pageable pageable);

	/**
	 * Regresa el total de registros de movimientos de estado de cuenta por id de
	 * conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT COUNT(mm.id) FROM MovimientoEstadoCuenta mm INNER JOIN Reporte r ON mm.idReporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId")
	public Long countByReporteConciliacionId(@Param("conciliacionId") final Long conciliacionId);

}