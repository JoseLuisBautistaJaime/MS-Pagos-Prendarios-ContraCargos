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
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacion;

/**
 * @name MovimientoBonificacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con la devoluciones.
 *
 * @author Quarksoft
 * @creationDate unknown hrs.
 * @version 0.1
 */
@Repository("movimientoBonificacionRepository")
public interface MovimientoBonificacionRepository extends JpaRepository<MovimientoBonificacion, Long> {

	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoBonificacion md WHERE md.idConciliacion = :folio")
	public List<MovimientoBonificacion> findByIdConciliacion(@Param("folio") Long folio);

	/**
	 * Regresa un movimiento devolucion por id
	 * 
	 * @param idMovimiento
	 * @return
	 */
	@Query("SELECT md FROM MovimientoBonificacion md WHERE md.id = :idMovimiento ")
	public MovimientoBonificacion findByIdMovimiento(@Param("idMovimiento") final Integer idMovimiento);

	/**
	 * Obtiene los movimientos devolucion por folio y estatus
	 * 
	 * @param folio
	 * @param ids
	 * @return
	 */
	public List<MovimientoBonificacion> findByIdConciliacionAndEstatusIdIn(Long folio, List<Integer> ids);

}
