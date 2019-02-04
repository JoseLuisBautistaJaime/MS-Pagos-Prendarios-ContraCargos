package mx.com.nmp.pagos.mimonte.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

/**
 * Nombre: DSSRepositoryCustom Descripcion: Interfaz que define las operaciones
 * encargadas de consultar informacion referente a DSS de la aplicacion en
 * consultas nativas dinamicas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 18/12/2018 14:54 hrs.
 * @version 0.1
 */
@Repository("dSSRepositoryCustom")
public interface DSSRepositoryCustom {

	/**
	 * Metodo que ejecuta una consulta contenida en un tipo de dato String
	 * 
	 * @param query
	 * @return
	 * @throws DataIntegrityViolationException
	 * @throws SQLDataException
	 * @throws SQLException
	 */
	<S extends Object> S execQuery(String query) throws DataIntegrityViolationException, SQLDataException, SQLException;

}
