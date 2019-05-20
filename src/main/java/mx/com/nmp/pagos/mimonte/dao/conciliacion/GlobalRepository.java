/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository("globalRepository")
public interface GlobalRepository extends JpaRepository<Global, Long>{
	
	@Query("FROM Global g WHERE g.conciliacion.id = :idConciliacion")
	public Global findByIdConciliacion(@Param("idConciliacion") final Integer idConciliacion);
	
}
