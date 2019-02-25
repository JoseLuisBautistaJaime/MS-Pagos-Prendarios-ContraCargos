package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;


/**
 * Nombre: TarjetaRepository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link TipoTarjeta}
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 11:34 AM
 * @version 0.1
 */
@Repository
public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, Integer> {

	/**
	 * Método que obtiene por el id el tipo de tarjeta.
	 * 
	 * @param id
	 * @return TipoTarjeta
	 */
	@Query(value = "from TipoTarjeta t where id = :id ")
	public TipoTarjeta encontrar(@Param("id") Integer id);

}
