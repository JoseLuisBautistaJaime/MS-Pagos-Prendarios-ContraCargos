/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository("estatusTransitoRepository")
public interface EstatusTransitoRepository extends JpaRepository<EstatusTransito, Integer>{

}
