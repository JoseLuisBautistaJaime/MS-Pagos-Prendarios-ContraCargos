package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;

@Repository
public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, String>{

//	TipoTarjeta findOne(Long id);

}
