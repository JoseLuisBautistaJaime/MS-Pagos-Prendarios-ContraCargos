package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

@Repository("movimientoDevolucionRepository")
public interface MovimientoDevolucionRepository extends JpaRepository<MovimientoDevolucion, Integer>{
	
	@Query("FROM MovimientoDevolucion md WHERE md.id = :idConciliacion")
	public MovimientoDevolucion findByIdConciliacion(@Param("idConciliacion") final Integer idConciliacion);

}
