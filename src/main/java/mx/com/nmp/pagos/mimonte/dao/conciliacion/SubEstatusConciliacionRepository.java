package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository("subEstatusConciliacionRepository")
public interface SubEstatusConciliacionRepository extends JpaRepository<SubEstatusConciliacion, Integer>{
	
	@Query("FROM SubEstatusConciliacion sec WHERE sec.description = :descripcion")
	public SubEstatusConciliacion findByDescripcion(@Param("descripcion") final String descripcion);

}
