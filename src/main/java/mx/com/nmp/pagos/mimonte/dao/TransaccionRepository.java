package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Transaccion;

@Repository("transaccionRepository")
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{
}
