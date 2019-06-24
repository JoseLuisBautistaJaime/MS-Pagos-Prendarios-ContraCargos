package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;

@Repository
public interface LayoutLineasRepository extends JpaRepository<LayoutLinea, Integer>{

}
