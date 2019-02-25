package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.EstatusTarjeta;

@Repository
public interface EstatusTarjetaRepository extends JpaRepository<EstatusTarjeta, Integer> {

	/**
	 * Método que obtiene el id del estatus de la tarjeta.
	 * 
	 * @param id
	 * @return EstatusTarjeta
	 */
	@Query(value = " from EstatusTarjeta t where id = :id ")
	EstatusTarjeta encontrar(@Param("id") Integer id);

}
