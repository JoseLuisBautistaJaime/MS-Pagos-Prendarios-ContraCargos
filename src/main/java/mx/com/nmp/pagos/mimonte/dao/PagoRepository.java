package mx.com.nmp.pagos.mimonte.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Pago;

/**
 * Nombre: PagoRepository Descripcion: Repositorio que realiza consultas
 * referentes a pagos
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 23/11/2018 10:44 hrs.
 * @version: 0.1
 */
@Repository("pagoRepository")
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	/**
	 * Metodo que consulta si ya existe un pago a registrar en base de datos para no
	 * duplicarlo
	 * 
	 * @param idTransaccionMidas
	 * @return
	 * @throws DataIntegrityViolationException
	 * @throws SQLDataException
	 * @throws SQLException
	 */
	@Query("SELECT COUNT(p.id) FROM Pago p WHERE p.idTransaccionMidas = :idTransaccionMidas")
	public Integer countByIdTransaccionMidas(@Param("idTransaccionMidas") Integer idTransaccionMidas)
			throws DataIntegrityViolationException, SQLDataException, SQLException;
}
