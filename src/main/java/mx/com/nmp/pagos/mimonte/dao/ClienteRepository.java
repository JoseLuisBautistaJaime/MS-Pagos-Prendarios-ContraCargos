package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Cliente;

/**
 * Nombre: ClienteRepository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link Cliente}
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 18:07 Hrs.
 * @version 0.1
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	public Cliente findByIdcliente(Integer idcliente);
}
