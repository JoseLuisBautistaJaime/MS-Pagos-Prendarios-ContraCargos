package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * @name CodigoEstadoCuentaRepository
 * @description Interface en donde se realizan operaciones de base de datos
 *              relacionadas con el catalogo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:42 hrs.
 * @version 0.1
 */
@Repository("codigoEstadoCuentaRepository")
public interface CodigoEstadoCuentaRepository extends JpaRepository<CodigoEstadoCuenta, Long> {

	/**
	 * Regresa una lista de catalogos de tipo CodigoEstadoCuenta en base a el id de
	 * una de sus entidades asociadas
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<CodigoEstadoCuenta> findByEntidades_Id(final Long idEntidad);

	/**
	 * Cambia el estatus a false de un catalogo CodigoEstadoCuenta
	 * 
	 * @param estatus
	 * @param idCodigo
	 */
	@Query("UPDATE CodigoEstadoCuenta cec SET cec.estatus = :estatus WHERE cec.id = :idCodigo")
	public void setEstatusWhereId(@Param("estatus") final Boolean estatus, @Param("idCodigo") final Long idCodigo);

}
