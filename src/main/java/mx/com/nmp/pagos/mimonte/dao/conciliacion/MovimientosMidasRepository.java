/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

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
	 * 
	 * @param idReporte
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id AND r.id = :reporteId")
	public List<MovimientoMidas> findByReporteId(@Param("reporteId") final Integer reporteId);

	/**
	 * Regresa los movimientos midas por id de conciliacion validando el ultimo
	 * reporte
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId")
	public List<MovimientoMidas> findByConciliacionId(@Param("conciliacionId") final Long conciliacionId);

	/**
	 * Regresa los movimientos midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @param pageable
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND mm.estatus = :estatus AND r.id = (SELECT MAX(rr.id) FROM Reporte rr WHERE rr.conciliacion.id = :conciliacionId AND rr.tipo = :tipoReporte)")
	public List<MovimientoMidas> findByReporteConciliacionIdAndEstatus(
			@Param("conciliacionId") final Long conciliacionId,
			@Param("estatus") final Boolean estatus,
			@Param("tipoReporte") TipoReporteEnum tipoReporte/* , Pageable pageable */);

	/**
	 * Regresa una lista de entities de tipo MovimientoMidas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT mm FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND r.id = (SELECT MAX(rr.id) FROM Reporte rr WHERE rr.conciliacion.id = :conciliacionId AND rr.tipo = :tipoReporte)")
	public List<MovimientoMidas> findByReporteConciliacionId(@Param("conciliacionId") final Long conciliacionId, @Param("tipoReporte") TipoReporteEnum tipoReporte);

	/**
	 * Regresa el total de registros movimientos midas por id de conciliacion y
	 * estatus
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT COUNT(mm.id) FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND mm.estatus = :estatus AND r.id = (SELECT MAX(rr.id) FROM Reporte rr WHERE rr.conciliacion.id = :conciliacionId AND rr.tipo = :tipoReporte)")
	public Long countByReporteConciliacionIdAndEstatus(@Param("conciliacionId") final Long conciliacionId,
			@Param("estatus") final Boolean estatus, @Param("tipoReporte") TipoReporteEnum tipoReporte);

	/**
	 * Regresa el total de registros movimientos midas por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT COUNT(mm.id) FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id INNER JOIN r.conciliacion con WHERE con.id = :conciliacionId AND r.id = (SELECT MAX(rr.id) FROM Reporte rr WHERE rr.conciliacion.id = :conciliacionId AND rr.tipo = :tipoReporte)")
	public Long countByReporteConciliacionId(@Param("conciliacionId") final Long conciliacionId, @Param("tipoReporte") TipoReporteEnum tipoReporte);

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
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND (mm.sucursal IN (:sucursales)) AND mm.fecha BETWEEN :fechaDesde AND :fechaHasta")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechas(@Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("producto") final Integer producto,
			@Param("operacion") final Integer operacion, @Param("sucursales") List<Integer> sucursales,
			@Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda y una fecha inicial
	 * 
	 * @param fechaDesde
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND (mm.sucursal IN (:sucursales)) AND mm.fecha >= :fechaDesde")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechaDesde(@Param("fechaDesde") Date fechaDesde,
			@Param("producto") final Integer producto, @Param("operacion") final Integer operacion,
			@Param("sucursales") List<Integer> sucursales, @Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda y una fecha final
	 * 
	 * @param fechaHasta
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND (mm.sucursal IN (:sucursales)) AND mm.fecha <= :fechaHasta")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechaHasta(@Param("fechaHasta") Date fechaHasta,
			@Param("producto") final Integer producto, @Param("operacion") final Integer operacion,
			@Param("sucursales") List<Integer> sucursales, @Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda
	 * 
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND (mm.sucursal IN (:sucursales))")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithoutFechas(@Param("producto") final Integer producto,
			@Param("operacion") final Integer operacion, @Param("sucursales") List<Integer> sucursales,
			@Param("partida") final Long partida);

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
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND mm.fecha BETWEEN :fechaDesde AND :fechaHasta")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechasNS(@Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("producto") final Integer producto,
			@Param("operacion") final Integer operacion, @Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda y una fecha inicial
	 * 
	 * @param fechaDesde
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND mm.fecha >= :fechaDesde")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechaDesdeNS(@Param("fechaDesde") Date fechaDesde,
			@Param("producto") final Integer producto, @Param("operacion") final Integer operacion,
			@Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda y una fecha final
	 * 
	 * @param fechaHasta
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion) AND mm.fecha <= :fechaHasta")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithFechaHastaNS(@Param("fechaHasta") Date fechaHasta,
			@Param("producto") final Integer producto, @Param("operacion") final Integer operacion,
			@Param("partida") final Long partida);

	/**
	 * Regresa un reporte de movimientos de pagos en linea (midas) de acuerdo a
	 * algunos parametros de busqueda
	 * 
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoDesc, mm.operacionDesc, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE (:producto IS NULL OR mm.idTipoContrato = :producto) AND (:partida IS NULL OR mm.folio= :partida) AND (:operacion IS NULL OR mm.idOperacion = :operacion)")
	public List<ReportePagosEnLineaDTO> getReportePagosEnLineaWithoutFechasNS(@Param("producto") final Integer producto,
			@Param("operacion") final Integer operacion, @Param("partida") final Long partida);

	/**
	 * Regresa un objeto de tipo X (por definir) para envio de correo
	 * 
	 * @param folio
	 * @param idsComisiones
	 * @return
	 */
	@Query("SELECT DISTINCT new mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO(mp.operationDate, mt.folio, mp.orderId, mt.operacionDesc, mt.monto, mp.currency, mp.tarjetaMovimientosProveedor.type, mp.status, mp.tarjetaMovimientosProveedor.brand, mp.tarjetaMovimientosProveedor.cardNumber, mp.tarjetaMovimientosProveedor.holderName, mp.authorization, mt.movimientoMidas.consumidor) FROM MovimientoTransito mt INNER JOIN MovimientoProveedor mp ON mt.movimientoMidas.numAutorizacion = mp.authorization WHERE mt.idConciliacion = :folio AND mt.id IN :idsComisiones")
	public List<SolicitarPagosMailDataDTO> getDataByFolioAndIdMovimientos(@Param("folio") final Long folio,
			@Param("idsComisiones") final List<Integer> idsComisiones);

	/**
	 * Regresa un listado de movimientos midas agrupados por sucursal y operacion.
	 * Los atributos que devuelve son: Monto, Sucursal y Operacion
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO(mm.sucursal, SUM(mm.monto)) FROM MovimientoMidas mm INNER JOIN Reporte r ON mm.reporte = r.id WHERE r.conciliacion.id = :conciliacionId GROUP BY mm.sucursal")
	public List<MovimientoMidasDTO> getMovimientosMidasBySucursal(@Param("conciliacionId") final Long conciliacionId);

	/**
	 * Regresa un listado de operaciones unicas (no repetidas entre conciliaciones) agrupadas por sucursal y especificados por el rango de fechas capturadas en la seccion proyeccion
	 * @param idConciliacion
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	@Query(value =
		"SELECT " +
			//"new mx.com.nmp.pagos.mimonte.dto.conciliacion.OperacionesPorSucursalDTO(" +
			    "movMidas.sucursal, " +
			    "COUNT(movMidas.folio) " +
			//") " +
		"FROM " +
			"(SELECT " +
				"DISTINCT folio, sucursal, operacion_abr, operacion_desc, monto, tipo_contrato_abr, tipo_contrato_desc, num_autorizacion, capital, comisiones, interes, estatus, " +
				"transaccion, fecha, estado_transaccion, consumidor, id_operacion, id_tipo_contrato, codigo_error, mensaje_error, id_tarjeta, marca_tarjeta, tipo_tarjeta, tarjeta, moneda_pago, " +
				"importe_transaccion " +
			"FROM " +
				"to_movimiento_midas mm " +
				"INNER JOIN to_reporte r ON r.id = mm.id_reporte " +
				"INNER JOIN to_conciliacion c ON c.id = r.id_conciliacion " +
		        //"INNER JOIN to_comision_transaccion ct ON mm.fecha >= ct.fecha_desde AND mm.fecha < DATE_ADD(ct.fecha_hasta, INTERVAL 1 DAY) " +
			"WHERE " +
				//"ct.id_conciliacion = :idConciliacion " +
				"mm.fecha >= :fechaDesde AND mm.fecha < DATE_ADD(:fechaHasta, INTERVAL 1 DAY) " +
			") AS movMidas " +
		"GROUP BY " +
			"movMidas.sucursal",
		nativeQuery = true
	)
	public List<Object[]> getTotalOperacionesRealesPorSucursal(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

}