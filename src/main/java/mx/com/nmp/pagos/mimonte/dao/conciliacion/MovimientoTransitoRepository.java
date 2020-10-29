/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
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
	 * @param folio
	 * @param estatus
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt JOIN FETCH mt.movimientoMidas WHERE mt.idConciliacion = :folio AND mt.estatus = (SELECT et.id FROM EstatusTransito et WHERE et.nombre LIKE CONCAT('%',:estatus,'%'))")
	public List<MovimientoTransito> findByIdConciliacionPagos(@Param("folio") Long folio, @Param("estatus") final String estatus);

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
	 * Regresa una bandera indicando con un 1 si los estatus a actualizar son
	 * correctos y con un 0 si son incorrectos
	 * 
	 * @param ids
	 * @param estatus
	 * @param estatus2
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT COUNT(mt.id) FROM to_movimiento_transito mt WHERE mt.id IN(:ids) AND mt.estatus <> :estatus AND mt.estatus <> :estatus2) = :tam THEN 1 ELSE 0 END AS RESULT")
	public Object verifyIfIdsHaveRightEstatus(@Param("ids") final List<Integer> ids,
			@Param("estatus") final Integer estatus, @Param("estatus2") final Integer estatus2,
			@Param("tam") final Integer tam);

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
	 * Regresa un valor de 1 cuando los ids de movimientos en transito ingrsados
	 * existen indiferentemente de su id o estatus de alta, de lo contrario regresa
	 * un 0
	 * 
	 * @param movTransitoIds
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mt.id) FROM to_movimiento_transito mt WHERE mt.id IN :movTransitoIds) = (SELECT :tam) ) THEN 1 ELSE 0 END")
	public Object checkIfIdsExistOnly(@Param("movTransitoIds") final List<Integer> movTransitoIds,
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
	public Object checkIdsAndFolioRelationship(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam);

	/**
	 * Regresa un valor 1 si los ids proporcionados son ids de movs. transito dados
	 * de alta por el suuario y realcionados a la conciliaicon especificada, de lo
	 * contrario regresa unvalor 0
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mt.id) FROM to_movimiento_transito mt INNER JOIN to_movimiento_conciliacion mcon ON mt.id = mcon.id WHERE mcon.nuevo = 1 AND mt.id IN :ids  AND mcon.id_conciliacion = :folio) = (SELECT :tam)) THEN 1 ELSE 0 END")
	public Object checkRightStatus(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam);

	/**
	 * Regresa un valor de 1 cuando los ids ingresados no estan en un estatus de los
	 * indicados
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @param estatusList
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mt.id) FROM to_movimiento_transito mt INNER JOIN to_movimiento_conciliacion mcon ON mt.id = mcon.id WHERE mt.id IN :ids AND mcon.id_conciliacion = :folio AND mt.estatus NOT IN :estatusList) = (SELECT :tam) ) THEN 1 ELSE 0 END")
	public Object verifyRightStatus(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam, @Param("estatusList") final List<Integer> estatusList);

	/**
	 * Obtiene el listado de movimientos en transito de dias anteriores con el estatus no identificado en openpay y en conciliaciones no completadas
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estatusId
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt INNER JOIN Conciliacion c ON c.id = mt.idConciliacion WHERE c.estatus.id = :idEstatusConciliacion AND mt.estatus.id = :estatusId AND mt.transaccion in (:transacciones)")
	public List<MovimientoTransito> findMovsTransitoByTransaccion(@Param("transacciones") List<String> transacciones, @Param("idEstatusConciliacion") Integer idEstatusConciliacion, @Param("estatusId") Integer estatusId);

}