/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;

/**
 * @name MovimientoConciliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los movimientos de la
 *              conciliacion.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 19:04 hrs.
 * @version 0.1
 */
@Repository("movimientoConciliacionRepository")
public interface MovimientoConciliacionRepository extends JpaRepository<MovimientoConciliacion, Integer> {

	@Query("SELECT mc FROM MovimientoConciliacion mc WHERE mc.idConciliacion = :folio")
	public List<MovimientoConciliacion> findByFolio(@Param("folio") Long folio);

	/**
	 * Regresa una lista de MovimientoConciliacion por folio de conciliacion e ids
	 * de MovimientoConciliacion
	 * 
	 * @param folio
	 * @param idMovimientos
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt WHERE mt.idConciliacion = :folio AND mt.id IN :idMovimientos")
	public List<MovimientoConciliacion> findByFolioAndIds(@Param("folio") final Long folio,
			@Param("idMovimientos") final List<Integer> idMovimientos);

	/**
	 * Regresa una lista de MovimientoComision por folio de conciliacion
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("from MovimientoComision l  where l.idConciliacion = :idConciliacion")
	public List<MovimientoComision> findMovimientoComisionByConciliacionId(
			@Param("idConciliacion") final Long idConciliacion);

	/**
	 * Regresa una lista de MovimientoComision por folio de conciliacion y tipo
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("from MovimientoComision l  where l.idConciliacion = :idConciliacion AND l.tipoComision = :tipo")
	public List<MovimientoComision> findMovimientoComisionByConciliacionIdAndTipo(
			@Param("idConciliacion") final Long idConciliacion, @Param("tipo") TipoMovimientoComisionEnum tipo);

	/**
	 * Regresa una lista de MovimientoDevolucion por folio de conciliacion
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("from MovimientoDevolucion l  where l.idConciliacion = :idConciliacion AND l.estatus.id = :idEstatus")
	public List<MovimientoDevolucion> findMovimientoDevolucionByConciliacionIdAndStatus(
			@Param("idConciliacion") final Long idConciliacion,
			@Param("idEstatus") final Integer idEstatus);

	/**
	 * Regresa una lista de MovimientoPago por folio de conciliacion
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT l FROM MovimientoPago l WHERE l.idConciliacion = :idConciliacion")
	public List<MovimientoPago> findMovimientoPagoByConciliacionId(@Param("idConciliacion") final Long idConciliacion);

	/**
	 * Regresa un valor de 1 cuando todos los ids de movimientos transito ingresados
	 * existen, estan relacionados al folio especificado y no tienen un estatus 4
	 * (No reconocido por Openpay)
	 * 
	 * @param folio
	 * @param ids
	 * @param estatusNROpenpay
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT COUNT(mt.id) FROM to_movimiento_transito mt INNER JOIN to_movimiento_conciliacion mc ON mc.id = mt.id WHERE mt.id IN :ids AND mc.id_conciliacion = :folio AND mt.estatus <> :estatusNROpenpay) = :tam THEN 1 ELSE 0 END as RESULT")
	public Object validaFolioAndIdsForMovPagos(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("estatusNROpenpay") final Integer estatusNROpenpay, @Param("tam") final Integer tam);

	@Query(nativeQuery = true, value = "SELECT mc.id, mm.sucursal FROM to_movimiento_conciliacion mc INNER JOIN to_movimiento_midas mm ON mc.id_movimiento_midas = mm.id AND mc.id IN :movimientoConciliacionIdList")
	public List<Object[]> getMapSucursalesByMovimientoConciliacionIds(@Param("movimientoConciliacionIdList") final List<Integer> movimientoConciliacionIdList);
	
}
