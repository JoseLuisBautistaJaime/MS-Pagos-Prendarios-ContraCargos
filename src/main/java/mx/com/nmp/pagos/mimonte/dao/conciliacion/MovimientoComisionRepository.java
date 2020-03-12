/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;

/**
 * @name MovimientoComisionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los movimientos de la comision.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:28 hrs.
 * @version 0.1
 */
@Repository("movimientoComisionRepository")
public interface MovimientoComisionRepository extends JpaRepository<MovimientoComision, Long> {

	/**
	 * Búsqueda de los movimientos de la comisión a partir de una lista de ids
	 * 
	 * @param lst
	 * @return
	 */
	@Query("FROM MovimientoComision mco WHERE mco.id IN :lst")
	public List<MovimientoComision> findByIds(List<Integer> lst);

	/**
	 * Búsqueda de los movimientos de la conciliación a partir del folio
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT mco FROM MovimientoComision mco INNER JOIN MovimientoConciliacion mc ON mc.id = mco.id WHERE mco.idConciliacion = :folio")
	public List<MovimientoComision> findByIdConciliacion(@Param("folio") Long folio);

	/**
	 * Regresa un movimiento comision por el id de movimiento estado cuenta
	 * 
	 * @param idMovimientoEstadoCuenta
	 * @return
	 */
	public MovimientoComision findByIdMovimientoEstadoCuenta(Long idMovimientoEstadoCuenta);

	/**
	 * Regresa un valor 1 si existen todos los movimientos de comision especificados
	 * de lo contrario regresa un 0
	 * 
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mc.id) FROM to_movimiento_comision mc WHERE mc.id IN :ids AND mc.tipo = 'COMISION') = (SELECT :tam) ) THEN 1 ELSE 0 END")
	public Object checkIfIdsExist(@Param("ids") final List<Integer> ids, @Param("tam") final Integer tam);

	/**
	 * Regresa un valor de 1 cuando todos los ids de comision especificados
	 * pertenecen a la conciliacion, de lo contrario regresa un valor de 0
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(mc.id) FROM to_movimiento_comision mc INNER JOIN to_movimiento_conciliacion mcon ON mc.id = mcon.id WHERE mc.id IN :ids AND mcon.id_conciliacion = :folio) = (SELECT :tam)) THEN 1 ELSE 0 END")
	public Object checkIdsAndFolioRelationship(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam);

	/**
	 * Regresa un valor de 1 cuando los ids de comisiones especificados pertenecen a
	 * la conciliaicon especificada y ademas fueron dados de alta por el usuario
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mc.id) FROM to_movimiento_comision mc INNER JOIN to_movimiento_conciliacion mcon ON mc.id = mcon.id WHERE mcon.nuevo = 1 AND mc.id IN :ids AND mcon.id_conciliacion = :folio ) = (SELECT :tam)) THEN 1 ELSE 0 END")
	public Object checkRightStatus(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam);

	/**
	 * Elimina un grupo de movimientos comision por ids
	 * 
	 * @param ids
	 */
	@Modifying
	@Query("DELETE FROM MovimientoComision mc WHERE mc.id IN :ids")
	public void deleteByIds(@Param("ids") final List<Integer> ids);

	/**
	 * Regresa un valor de 1 cuando ninguno de los ids especificados tiene alguno de
	 * los estatus especificados, de lo ocntrario regresa un 0
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @param estatusList
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ( (SELECT COUNT(mc.id) FROM to_movimiento_comision mc INNER JOIN to_movimiento_conciliacion mcon ON mc.id = mcon.id WHERE mc.id IN :ids AND mcon.id_conciliacion = :folio AND mc.estatus NOT IN :estatusList) = (SELECT :tam) ) THEN 1 ELSE 0 END")
	public Object verifyRightStatus(@Param("folio") final Long folio, @Param("ids") final List<Integer> ids,
			@Param("tam") final Integer tam, @Param("estatusList") final List<Integer> estatusList);

}
