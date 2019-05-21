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

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
 * @name DevolucionesRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los movimientos de las
 *              devoluciones.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("devolucionesRepository")
public interface DevolucionesRepository extends JpaRepository<MovimientoDevolucion, Integer> {

	/**
	 * Busqueda de los movimientos de las devoluciones apartir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT md FROM MovimientoConciliacion mc INNER JOIN MovimientoDevolucion md ON mc.id=md.id WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByIdConciliacion(@Param("folio") Integer folio);
}