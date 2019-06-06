/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;

/**
 * @name MovimientosMidasRepository
 * @description Realiza operaciones a nivel base de datos relacionados con
 *              movimientos de midas
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:01 PM
 */
@Repository("movimientosMidasRepository")
public interface MovimientosMidasRepository extends PagingAndSortingRepository<MovimientoMidas, Long> {

	/**
	 * Regresa los movimientos midas por id de reporte
	 * @param idReporte
	 * @return
	 */
	//@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id  = :reporteId")
	public List<MovimientoMidas> findByReporteId(@Param("reporteId") final Integer reporteId);

	/**
	 * Regresa los movimientos midas por id de conciliacion validando el ultimo reporte
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND r.id = (SELECT MAX(r1.id) FROM Reporte r1 WHERE r1.conciliacion.id = con.id)") // Obtiene ultimo reporte
	public List<MovimientoMidas> findByConciliacionId(@Param("conciliacionId") final Integer conciliacionId);

	/**
	 * Regresa los movimientos midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @param pageable
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND mm.estatus = :estatus")
	public List<MovimientoMidas> findByReporteConciliacionId(@Param("conciliacionId") final Integer conciliacionId,
			@Param("estatus") final Boolean estatus, Pageable pageable);

	/**
	 * Regresa el total de registros midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT COUNT(mm.id) FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND mm.estatus = :estatus")
	public Long countByReporteConciliacionId(@Param("conciliacionId") final Integer conciliacionId,
			@Param("estatus") final Boolean estatus);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas)
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query(" SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO( mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoAbr, mm.operacionAbr, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND (:sucursales IS NULL OR mm.sucursal IN :sucursales) AND (:fechaDesde IS NOT NULL AND :fechaHasta IS NOT NULL AND mm.fecha BETWEEN :fechaDesde AND :fechaHasta) OR (:fechaDesde IS NOT NULL AND :fechaHasta IS NULL AND mm.fecha >= :fechaDesde) OR (:fechaDesde IS NULL AND :fechaHasta IS NOT NULL AND mm.fecha <= :fechaHasta) OR (:fechaDesde IS NULL AND :fechaHasta IS NULL)")
	public List<ReportePagosLibresDTO> getReportePagosLibres(@Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("producto") final Integer producto,
			@Param("operacion") final Integer operacion, @Param("sucursales") List<Integer> sucursales,
			@Param("partida") final Long partida);

//	@Query(" SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO( mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoAbr, mm.operacionAbr, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE mm.idTipoContrato = :producto AND mm.folio= :partida AND mm.idOperacion = :operacion AND mm.sucursal IN :sucursales AND mm.fecha BETWEEN :fechaDesde AND :fechaHasta")
//	public List<ReportePagosLibresDTO> getReportePagosLibresDynamic(@Param("fechaDesde") Date fechaDesde,
//			@Param("fechaHasta") Date fechaHasta, @Param("producto") final Integer producto,
//			@Param("operacion") final Integer operacion, @Param("sucursales") List<Integer> sucursales,
//			@Param("partida") final Long partida);

}