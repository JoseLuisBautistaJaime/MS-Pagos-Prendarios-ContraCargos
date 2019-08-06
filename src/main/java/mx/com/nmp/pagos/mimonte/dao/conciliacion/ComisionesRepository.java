/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;

/**
 * @name ComisionesRepository
 * @description Interface de tipo repository que contiene metodos para realizar
 *              operaciones a nivel de base de datos sobre objetos relacionados
 *              con comisiones.
 *
 * @author Quarksoft
 * @creationDate 31-Mar-2019 6:34:10 PM
 * @version 0.1
 */
@Repository("comisionesRepository")
public interface ComisionesRepository extends JpaRepository<MovimientoComision, Integer> {

	/**
	 * Elimina una comision de la entidad de Movimientos Comisiones
	 * 
	 * @param idsComisiones
	 * @param idConciliacion
	 */
	@Modifying
	@Query("DELETE FROM MovimientoComision mc WHERE mc.id IN (SELECT mcon.id FROM MovimientoConciliacion mcon WHERE mcon.id in :idsComisiones AND mcon.nuevo = true AND mcon.idConciliacion = :idConciliacion)")
	public void deleteByIdsAndIdConciliacion(@Param("idsComisiones") final List<Integer> idsComisiones,
			@Param("idConciliacion") final Integer idConciliacion);

	/**
	 * Verfica si es posible eliminar una comision
	 * 
	 * @param idsComisiones
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT mc.id FROM MovimientoComision mc WHERE mc.id IN :idsComisiones AND mc.id IN (SELECT mcon.id FROM MovimientoConciliacion mcon WHERE mcon.id in :idsComisiones AND mcon.nuevo = true AND mcon.idConciliacion = :idConciliacion)")
	public List<Long> verifyById(@Param("idsComisiones") final List<Integer> idsComisiones,
			@Param("idConciliacion") final Integer idConciliacion);

	/**
	 * Regresa el total de movimientos de pagos que estan entre las fechas
	 * especificadas
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT COUNT(mp.id) FROM MovimientoPago mp INNER JOIN MovimientoConciliacion mc ON mp.id = mc.id WHERE mc.createdDate BETWEEN :fechaDesde AND :fechaHasta AND mc.idConciliacion = :idConciliacion")
	public Long findTransaccionesPagosByFechasAndIdConciliacion(@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta, @Param("idConciliacion") Integer idConciliacion);

	/**
	 * Regresa el id de un movimiento comision su id de conciliacion asociado y el
	 * conteo de el id de comision por un rango de fechas
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT COUNT(md.id) AS countId FROM MovimientoConciliacion mc INNER JOIN MovimientoDevolucion md ON md.id = mc.id WHERE mc.createdDate BETWEEN :fechaDesde AND :fechaHasta AND mc.idConciliacion = :idConciliacion")
	public Map<String, Object> findDataByFechasAndIdConciliacion(@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta, @Param("idConciliacion") Integer idConciliacion);

	/**
	 * Regresa la suma de los movimientos tipo COMISION y los de tipo IVA_COMISION
	 * 
	 * @param tipoComisionPago
	 * @param tipoComisionIva
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT comision, iva FROM (SELECT SUM(mc.monto) AS comision FROM to_movimiento_comision mc WHERE mc.tipo = :tipoComisionPago) comision, (SELECT SUM(mc.monto) AS iva FROM to_movimiento_comision mc WHERE mc.tipo = :tipoComisionIva) iva")
	public Map<String, BigDecimal> findMovimientosSum(@Param("tipoComisionPago") final String tipoComisionPago,
			@Param("tipoComisionIva") final String tipoComisionIva);

	/**
	 * Rehresa las comisiones por tipo de operacion (COMISION E IVA_COMISION)
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDTO(mc.id, mc.fechaOperacion, mc.fechaCargo, mc.monto, mc.descripcion, mcon.nuevo) FROM MovimientoComision mc INNER JOIN MovimientoConciliacion mcon ON mc.id = mcon.id AND mcon.idConciliacion = :folio")
	public List<ComisionDTO> findByFolio(@Param("folio") final Integer folio);

	/**
	 * Regresa un movimiento comision por id
	 * 
	 * @param idComision
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO(mcon.idConciliacion, mc.id, mc.fechaOperacion, mc.fechaCargo, mc.monto, mc.descripcion, mcon.nuevo) FROM MovimientoComision mc INNER JOIN MovimientoConciliacion mcon ON mc.id = mcon.id WHERE mc.id = :idComision")
	public ComisionSaveResponseDTO findByIdComision(@Param("idComision") final Integer idComision);

	/**
	 * Regresa un movimiento comision por id y estatus
	 * 
	 * @param idMovimientoComision
	 * @return
	 */
	public MovimientoComision findByIdAndEstatus(final Integer idMovimientoComision, final Boolean estatus);

	/**
	 * Regresa un valor de 1 cuando todos los ids de comisiones existen, de lo
	 * contrario regresa un valor 0
	 * 
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT COUNT(com.id) FROM to_movimiento_comision com WHERE com.id IN :ids)= :tam THEN 1 ELSE 0 END")
	public Object checkIfIdsExist(@Param("ids") final List<Integer> ids, @Param("tam") final Integer tam);

	/**
	 * Regresa un valor de 1 cuando todos los ids de comisiones ingresados
	 * corresponden al folio de ocnciliacion, de lo contrario regresa un 0
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT COUNT(mc.id) FROM to_movimiento_comision mc INNER JOIN to_movimiento_conciliacion mcon ON mc.id = mcon.id WHERE mc.id IN :ids AND mcon.id_conciliacion = :folio) = :tam THEN 1 ELSE 0 END")
	public Object checkIfFolioAndIdsRelationshipExit(@Param("folio") final Integer folio,
			@Param("ids") final List<Integer> ids, @Param("tam") final Integer tam);

}