package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;

/**
 * Nombre: TarjetaRepository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link Tarjetas}
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 11:34 AM
 * @version 0.1
 */
@Repository
public interface TarjetaRepository extends JpaRepository<Tarjetas, String> {
	
	/**
	 * Obtiene la cantidad de tarjetas que pertenecen a un cliente en especifico (por id Cliente)
	 * 
	 * @param idcliente Integer con el id del cliente en cuestion
	 * @return Valor Integer con la cantidad de tarjetas encontradas para ese id de cliente
	 */
	//public int countByClientes(Integer idcliente);
	
	/**
	 * Método que obtiene información de las tarjetas.
	 * 
	 * @param idCliente
	 * @return List<TarjeDTO>.
	 */
	public List<TarjeDTO> findByIdcliente(Integer idCliente);
	
	
	/**
	 * Método que obtiene información de las tarjetas.
	 * 
	 * @param idCliente
	 * @return List<TarjeDTO>.
	 */
	
	@Query(value = " from Tarjetas t where idcliente = :idCliente ")
	public List<Tarjetas> encuentraidCliente(@Param("idCliente") Integer idCliente);
	
	/**
	 * Método que obtiene información de la tarjeta.
	 * 
	 * @param idcliente
	 * @param token
	 * @return List<Tarjetas>
	 */
	public Tarjetas findByIdclienteAndToken(Integer idCliente, String token);
	

	/**
	 * Método que obtiene información de la tarjeta.
	 * 
	 * @param token
	 * @return List<Tarjetas>
	 */
	public Tarjetas findByToken(String token);
}
