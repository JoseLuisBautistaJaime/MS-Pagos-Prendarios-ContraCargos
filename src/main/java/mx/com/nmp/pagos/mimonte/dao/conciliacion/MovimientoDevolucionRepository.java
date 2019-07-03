package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
 * Interface de capa DAO que sirve para realizar operaciones de base de datos relacionadas con la devoluciones.
 * @author Quarksoft
 *
 */
@Repository("movimientoDevolucionRepository")
public interface MovimientoDevolucionRepository extends JpaRepository<MovimientoDevolucion, Integer>{
	
	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByIdConciliacion(@Param("folio") Integer folio);
	
	/**
	 * Obtiene los movimientos por devolución cuando el parametro ingresado es el folio el identificador del movimiento y la fecha de la devolucion.
	 * @param folio
	 * @param idMovimiento
	 * @param fecha
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio AND md.id = :idMovimiento")
	public MovimientoDevolucion findByIdConciliacionAndIdMovimiento(
			@Param("folio") final Integer folio, 
			@Param("idMovimiento") final Integer idMovimiento);

	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByFolio(@Param("folio") Integer folio);
	
	
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.id = :idMovimiento AND md.fecha = :fecha ")
	public List<MovimientoDevolucion> findByIdMovimientoAndFecha(
			@Param("idMovimiento") final Integer idMovimiento,
			@Param("fecha") final Date fecha);
	
	@Query("SELECT md FROM MovimientoDevolucion md WHERE md.id = :idMovimiento ")
	public MovimientoDevolucion findByIdMovimiento(
			@Param("idMovimiento") final Integer idMovimiento);

}
