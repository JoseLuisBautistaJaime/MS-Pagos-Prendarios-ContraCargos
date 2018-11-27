package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
	
}
