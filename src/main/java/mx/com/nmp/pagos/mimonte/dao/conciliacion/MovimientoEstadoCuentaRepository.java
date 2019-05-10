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

	@Query("SELECT COUNT(mm.id) FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.conciliacion.id = :folioConciliacion")
	public Long countByIdConciliacion(@Param("folioConciliacion") final Long folioConciliacion);

	@Query("SELECT mm FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.conciliacion.id = :folioConciliacion")
	public List<MovimientoEstadoCuenta> findByIdConciliacion(
			@Param("folioConciliacion") final Integer folioConciliacion, Pageable pageable);

}