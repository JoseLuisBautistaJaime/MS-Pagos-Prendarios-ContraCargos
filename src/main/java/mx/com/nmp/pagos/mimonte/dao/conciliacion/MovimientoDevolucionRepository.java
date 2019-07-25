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

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
 * @name MovimientoDevolucionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con la devoluciones.
 *
 * @author Quarksoft
 * @creationDate unknown hrs.
 * @version 0.1
 */
@Repository("movimientoDevolucionRepository")
public interface MovimientoDevolucionRepository extends JpaRepository<MovimientoDevolucion, Integer> {

	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByIdConciliacion(@Param("folio") Integer folio);

	/**
	 * Obtiene los movimientos por devolución cuando el parametro ingresado es el
	 * folio el identificador del movimiento y la fecha de la devolucion.
	 * 
	 * @param folio
	 * @param idMovimiento
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio AND md.id = :idMovimiento")
	public MovimientoDevolucion findByIdConciliacionAndIdMovimiento(@Param("folio") final Integer folio,
			@Param("idMovimiento") final Integer idMovimiento);

	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByFolio(@Param("folio") Integer folio);

	/**
	 * Regresa una lista de movimientos devolucion por ids y fecha
	 * 
	 * @param idMovimiento
	 * @param fecha
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.id = :idMovimiento AND md.fecha = :fecha ")
	public List<MovimientoDevolucion> findByIdMovimientoAndFecha(@Param("idMovimiento") final Integer idMovimiento,
			@Param("fecha") final Date fecha);

	/**
	 * Regresa un movimiento devolucion por id
	 * 
	 * @param idMovimiento
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.id = :idMovimiento ")
	public MovimientoDevolucion findByIdMovimiento(@Param("idMovimiento") final Integer idMovimiento);

	/**
	 * Obtiene los movimientos devolucion por folio y estatus
	 * 
	 * @param folio
	 * @param ids
	 * @return
	 */
	public List<MovimientoDevolucion> findByIdConciliacionAndEstatusIdIn(Integer folio, List<Integer> ids);

}
