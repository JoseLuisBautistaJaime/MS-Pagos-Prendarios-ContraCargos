package mx.com.nmp.pagos.mimonte.dao.conciliacion;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:05 PM
 */
@Repository("devolucionesRepository")
public interface DevolucionesRepository extends JpaRepository<MovimientoDevolucion, Integer> {

	@Query("SELECT md FROM MovimientoConciliacion mc INNER JOIN MovimientoDevolucion md ON mc.id=md.id WHERE md.idConciliacion = :folio")
	public List<MovimientoDevolucion> findByIdConciliacion(@Param("folio") Integer folio);
}