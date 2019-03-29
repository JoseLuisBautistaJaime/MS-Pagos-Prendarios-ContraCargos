/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
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
	@Query("SELECT c FROM CodigoEstadoCuenta c WHERE c.entidad.id = :idEntidad AND c.estatus = true")
	public List<CodigoEstadoCuenta> findByEntidad_Id(@Param("idEntidad") final Long idEntidad)
			throws EmptyResultDataAccessException;

	/**
	 * Cambia el estatus a false de un catalogo CodigoEstadoCuenta
	 * 
	 * @param estatus
	 * @param idCodigo
	 */
	@Query("UPDATE CodigoEstadoCuenta cec SET cec.estatus = :estatus WHERE cec.id = :idCodigo")
	public void setEstatusWhereId(@Param("estatus") final Boolean estatus, @Param("idCodigo") final Long idCodigo);

	/**
	 * Regresa un codigo de estado de cuenta por id de entidad e id categoria
	 * 
	 * @param idEntidad
	 * @param idCategoria
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	@Query("SELECT c FROM CodigoEstadoCuenta c WHERE c.entidad.id = :idEntidad AND c.categoria.id = :idCategoria AND c.entidad.estatus = true")
	public CodigoEstadoCuenta findByEntidadIdAndCategoriaId(@Param("idEntidad") final Long idEntidad, @Param("idCategoria") final Long idCategoria)
			throws EmptyResultDataAccessException;

	/**
	 * Regresa un codigo de estado de cuenta por id
	 */
	@Query("SELECT c FROM CodigoEstadoCuenta c WHERE c.id = :id AND c.estatus = true")
	public Optional<CodigoEstadoCuenta> findById(@Param("id") final Long id);

	/**
	 * Regresa todos los codigos de estado de cuenta
	 * 
	 * @return
	 */
	@Query("SELECT c FROM CodigoEstadoCuenta c WHERE c.estatus = true")
	public List<CodigoEstadoCuenta> finAll();
}
