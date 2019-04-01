package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:10 PM
 */
public interface ComisionesRepository extends JpaRepository<MovimientoComision, Long> {

}