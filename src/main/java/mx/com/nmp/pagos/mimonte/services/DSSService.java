package mx.com.nmp.pagos.mimonte.services;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: DSSService Descripcion: Interfaz que contiene las operaciones
 * encargadas de realizar consultas relacionadas con el DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:06 hrs.
 * @version 0.1
 */
public interface DSSService {

	/**
	 * Metodo que consulta todas las reglas de negocio asociadas a un cliente
	 * especifico
	 * 
	 * @return
	 */
	public abstract List<ReglaNegocio> getReglasNegocio();

	/**
	 * 
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * un cliente en especifico
	 * 
	 * @param String query
	 * @return ReglaNegocioResumenDTO
	 */
	public abstract Object execQuery(String query)
			throws DataIntegrityViolationException, SQLDataException, SQLException;

	/**
	 * Obtiene un tipo de autorizacion por id
	 * 
	 * @param id
	 * @return
	 */
	public abstract TipoAutorizacionDTO getTipoAutorizacionById(final Integer id);

}
