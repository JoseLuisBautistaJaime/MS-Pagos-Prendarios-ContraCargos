package mx.com.nmp.pagos.mimonte.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: DSSRepository Descripcion: Interfaz que define las operaciones
 * encargadas de consultar informacion referente a DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:21 hrs.
 * @version 0.1
 */
@Repository("dssRepository")
public interface DSSRepository extends JpaRepository<ReglaNegocio, Integer>, DSSRepositoryCustom {

	/**
	 * 
	 * Metodo que consulta todas las reglas de negocio asociadas a un cliente
	 * especifico
	 * 
	 * @param Integer idCliente
	 * @return objeto List<ReglaNegocio>
	 */
	@Query("SELECT DISTINCT rn FROM ReglaNegocio rn JOIN rn.clientes cls ON cls.idcliente = :idCliente")
	public List<ReglaNegocio> getReglasNegocio(@Param("idCliente") Long idCliente)
			throws DataIntegrityViolationException, SQLDataException, SQLException;

	/**
	 * 
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * un cliente en especifico
	 * 
	 * @param String query
	 * @return ReglaNegocioResumenDTO
	 */
	@SuppressWarnings("unchecked")
	@Query(value = "query", nativeQuery = true)
	public Object[] execQuery(String query) throws DataIntegrityViolationException;

}
