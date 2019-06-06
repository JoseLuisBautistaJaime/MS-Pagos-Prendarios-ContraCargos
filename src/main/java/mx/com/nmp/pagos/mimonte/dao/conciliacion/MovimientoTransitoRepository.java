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
	 * Búsqueda de los movimientos en transito a partir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoConciliacion mc INNER JOIN MovimientoTransito mt ON mc.id = mt.id WHERE mt.idConciliacion = :folio")
	public List<MovimientoTransito> findByIdConciliacion(@Param("folio") Integer folio);

	/**
	 * Obtiene los movimientos en transito.
	 * 
	 * @param folio
	 * @param idMovimiento
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoConciliacion mc INNER JOIN MovimientoTransito mt ON mc.id = mt.id WHERE mc.idConciliacion = :folio AND mt.id = :idMovimiento ")
	public List<MovimientoTransito> findByIdFolioAndIdMovimiento(@Param("folio") final Integer folio,
			@Param("idMovimiento") final long idMovimiento);

	/**
	 * Regresa una lista de MovimientoTransito por folio e ids
	 * 
	 * @param folio
	 * @param idMovimientos
	 * @return
	 */
	@Query("SELECT mt FROM MovimientoTransito mt WHERE mt.folio = :folio AND mt.id IN :idMovimientos")
	public List<MovimientoTransito> findByFolioAndIds(@Param("folio") final Integer folio,
			@Param("idMovimientos") final List<Integer> idMovimientos);

}