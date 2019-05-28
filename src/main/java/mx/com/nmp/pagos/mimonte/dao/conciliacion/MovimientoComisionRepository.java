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
	public List<MovimientoComision> findByIdConciliacion(@Param("folio") Integer folio);

}
