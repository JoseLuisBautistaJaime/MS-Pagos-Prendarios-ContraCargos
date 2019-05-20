package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository("reporteRepository")
public interface ReporteRepository extends JpaRepository<Reporte, Integer>{
	
	@Query("FROM Reporte r WHERE r.id = :idConciliacion")
	public Reporte findByIdConciliacion(@Param("idConciliacion") final Integer idConciliacion);

}
