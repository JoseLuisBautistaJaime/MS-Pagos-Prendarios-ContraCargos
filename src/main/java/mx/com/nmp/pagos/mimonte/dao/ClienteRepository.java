package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;

/**
 * Nombre: ClienteRepository Descripcion: Repositorio que realiza las consultas
 * sobre la entidad {@link Cliente}
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 21/11/2018 18:07 Hrs.
 * @version 0.1
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Consulta que obtiene las tarjetas registradas por medio del parametro
	 * idCliente.
	 * 
	 * @param idcliente
	 * @return List<Tarjetas>
	 */
	@Query(value = "select c.tarjetas from Cliente c where c.idcliente = :idcliente")
	public List<Tarjetas> findByIdCliente(@Param("idcliente") Long idcliente);

	/**
	 * Método que obtiene por id del cliente al cliente.
	 * 
	 * @param idcliente.
	 * @return Cliente.
	 */
	public Cliente findByIdcliente(Long idcliente);
	
	/**
	 * Consulta que obtiene las tarjetas registradas por medio del parametro
	 * idCliente.
	 * 
	 * @param idcliente
	 * @return List<Tarjetas>
	 */
	@Query(value = "select c.tarjetas from Cliente c where c.idcliente = :idcliente")
	public Cliente encuentraIdCliente(@Param("idcliente") Integer idcliente);

}
