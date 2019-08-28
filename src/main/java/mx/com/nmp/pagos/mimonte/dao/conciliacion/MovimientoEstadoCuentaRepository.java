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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
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

	/**
	 * Cuenta los movimientos de estado cuenta relacionados con un folio de
	 * conciliacion especifico
	 * 
	 * @param folioConciliacion
	 * @return
	 */
	@Query("SELECT COUNT(mm.id) FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.conciliacion.id = :folioConciliacion")
	public Long countMovimientos(@Param("folioConciliacion") final Long folioConciliacion);

	/**
	 * Regresa una lista de objetos de tipo MovimientoEstadoCuentaDBDTO relacionados
	 * con un folio de conciliacion, ademas de regresar la lista en base a una
	 * paginacion especifica
	 * 
	 * @param folioConciliacion
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO(mm.id, mm.fechaOperacion, mm.concepto, mm.tipoMovimiento, mm.importe, ec.cabecera.saldoInicial, ec.totalesAdicional.saldoFinal) FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.conciliacion.id = :folioConciliacion")
	public List<MovimientoEstadoCuentaDBDTO> listMovimientos(@Param("folioConciliacion") final Long folioConciliacion
			/*,Pageable pageable*/);

	/**
	 * Regresa los movimientos estado cuenta por id de reporte
	 * @param idReporte
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.id = :reporteId")
	public List<MovimientoEstadoCuenta> findByReporte(@Param("reporteId") final Integer reporteId);

	/**
	 * Regresa los movimientos estado de cuenta por id de conciliacion y claves leyenda
	 * @param idReporte
	 * @param clavesLeyenda
	 * @return
	 */
	@Query("SELECT DISTINCT mm FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte WHERE r.conciliacion.id = :idConciliacion AND mm.claveLeyenda IN (:clavesLeyenda)")
	public List<MovimientoEstadoCuenta> findByConciliacionAndClaveLeyendaIn(@Param("idConciliacion") final Long idConciliacion, @Param("clavesLeyenda") List<String> clavesLeyenda);

	/**
	 * Obtiene los movimientos de estado de cuenta asignados a una conciliacion
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoEstadoCuenta mm INNER JOIN EstadoCuenta ec ON mm.idEstadoCuenta = ec.id INNER JOIN Reporte r ON r.id = ec.idReporte INNER JOIN r.conciliacion con WHERE con.id = :idConciliacion")
	public List<MovimientoEstadoCuenta> findByConciliacion(@Param("idConciliacion") final Long idConciliacion);

}