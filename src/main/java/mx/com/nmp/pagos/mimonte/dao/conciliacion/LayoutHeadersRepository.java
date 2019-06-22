package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;

@Repository
public interface LayoutHeadersRepository extends JpaRepository<LayoutHeader, Integer>{

}

