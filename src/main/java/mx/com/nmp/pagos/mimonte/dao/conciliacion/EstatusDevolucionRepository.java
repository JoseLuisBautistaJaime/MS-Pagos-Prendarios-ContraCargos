/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository("estatusDevolucionRepository")
public interface EstatusDevolucionRepository extends JpaRepository<EstatusDevolucion, Integer>{
	
	@Query("FROM EstatusDevolucion ed WHERE ed.nombre = :nombre")
	public EstatusDevolucion findByNombre(String nombre);

}
