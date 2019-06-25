package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.TipoAutorizacion;

@Repository("tipoAutorizacionRepository")
public interface TipoAutorizacionRepository extends JpaRepository<TipoAutorizacion, Integer> {
}
