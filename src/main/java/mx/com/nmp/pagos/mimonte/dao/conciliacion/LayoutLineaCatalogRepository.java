package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;

@Repository
public interface LayoutLineaCatalogRepository extends JpaRepository<LayoutLineaCatalog, Long>{

}
