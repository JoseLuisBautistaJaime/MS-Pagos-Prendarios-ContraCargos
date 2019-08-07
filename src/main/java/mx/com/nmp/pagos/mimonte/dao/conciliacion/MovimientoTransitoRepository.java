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

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;

/**
 * @name MovimientoTransitoRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los movimientos en transito.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("movimientoTransitoRepository")
public interface MovimientoTransitoRepository extends JpaRepository<MovimientoTransito, Integer> {

	/**
	 * Búsqueda de los movimientos en transito a partir de una lista de ids.
	 * 
	 * @param lst
	 * @return
	 */
	@Query("FROM MovimientoTransito mt WHERE mt.id IN ( :lst ) ")
	public List<MovimientoTransito> findByIds(List<Integer> lst);

	/**
	 * Busqueda de los movimientos en transito a partir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt WHERE mt.idConciliacion = :folio")
	public List<MovimientoTransito> findByIdConciliacion(@Param("folio") Long folio);

	/**
	 * Busqueda de los movimientos en transito a partir del folio (para validar
	 * cuales son candidatas a solicitud de pagos).
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt JOIN FETCH mt.movimientoMidas WHERE mt.idConciliacion = :folio AND mt.estatus = (SELECT et.id FROM EstatusTransito et WHERE et.nombre LIKE '%Solicitada%')")
	public List<MovimientoTransito> findByIdConciliacionPagos(@Param("folio") Long folio);

	/**
	 * Obtiene los movimientos en transito.
	 * 
	 * @param folio
	 * @param idMovimiento
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoConciliacion mc INNER JOIN MovimientoTransito mt ON mc.id = mt.id WHERE mc.idConciliacion = :folio AND mt.id = :idMovimiento ")
	public MovimientoTransito findByIdFolioAndIdMovimiento(@Param("folio") final Long folio,
			@Param("idMovimiento") final Integer idMovimiento);

	/**
	 * Regresa una lista de MovimientoTransito por folio e ids
	 * 
	 * @param folio
	 * @param idMovimientos
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt INNER JOIN MovimientoConciliacion mc ON mt.id = mc.id WHERE mc.idConciliacion = :folio AND mt.id IN :idMovimientos")
	public List<MovimientoTransito> findByFolioAndIds(@Param("folio") final Long folio,
			@Param("idMovimientos") final List<Integer> idMovimientos);

	/**
	 * Obtiene los movimientos en transito por movimiento midas
	 * 
	 * @param folio
	 * @param idMovimiento
	 * @return
	 */
	public List<MovimientoTransito> findByIdConciliacionAndMovimientoMidasId(Long idConciliacion,
			long idMovimientoMidas);

	/**
	 * Regresa una bandera indicando con un 0 si los estatus a actualizar son
	 * incorrectos y con un 1 si son correctos
	 * 
	 * @param ids
	 * @param estatus
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT mt.id FROM to_movimiento_transito mt WHERE mt.id IN(:ids) AND mt.estatus <> :estatus AND mt.estatus <> :estatus2) IS NOT NULL THEN 0 ELSE 1 END AS RESULT")
	public Object verifyIfIdsHaveRightEstatus(@Param("ids") final List<Integer> ids,
			@Param("estatus") final Integer estatus, @Param("estatus2") final Integer estatus2);

	/**
	 * Regresa un valor de 1 cuando los movimientos especificados son movimientos
	 * transito valios (que existan en la tabla y hayan sido dados de alta desde la
	 * aplicacion y no el merge), de lo contrario regresaa un 1
	 * 
	 * @param movTransitoIds
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(mt.id) FROM to_movimiento_transito mt INNER JOIN to_movimiento_conciliacion mc ON mt.id = mc.id WHERE mc.nuevo = 1 AND mt.id IN :movTransitoIds) = (SELECT :tam)) THEN 1 ELSE 0 END")
	public Object checkIfIdsExist(@Param("movTransitoIds") final List<Integer> movTransitoIds,
			@Param("tam") final Integer tam);

	/**
	 * Regresa un valor de 1 cuando los ids de movimientos tansito pertenecen a la
	 * conciliacion, de lo contrario regres un valor de 0
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(mt.id) FROM to_movimiento_transito mt INNER JOIN to_movimiento_conciliacion mc ON mt.id = mc.id WHERE mt.id IN :ids AND mc.id_conciliacion = :folio) = (SELECT :tam)) THEN 1 ELSE 0 END")
	public Object checkIdsAndFolioRelationship(@Param("folio") final Long folio,
			@Param("ids") final List<Integer> ids, @Param("tam") final Integer tam);

}