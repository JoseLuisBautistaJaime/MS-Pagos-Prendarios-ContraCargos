/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
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
	 * @throws EmptyResultDataAccessException
	 */
	public List<CodigoEstadoCuenta> findByEntidad_Id(final Long idEntidad) throws EmptyResultDataAccessException;

	/**
	 * Regresa un codigo de estado de cuenta por id de entidad e id categoria
	 * 
	 * @param idEntidad
	 * @param idCategoria
	 * @param codigo
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public CodigoEstadoCuenta findByEntidadIdAndCategoriaIdAndCodigo(final Long idEntidad, final Long idCategoria,
			final String codigo) throws EmptyResultDataAccessException;

	/**
	 * Regresa el listado de codigos de estado de cuenta por id categoria
	 * 
	 * @param idCategoria
	 * @param status
	 * @param idEntidad
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<CodigoEstadoCuenta> findByCategoriaIdAndEstatusAndEntidadId(final Long idCategoria, final boolean status, Long idEntidad)
			throws EmptyResultDataAccessException;

	/**
	 * Regresa un codigo de estado de cuenta por id
	 */
	public Optional<CodigoEstadoCuenta> findById(final Long id);

	/**
	 * Regresa todos los codigos de estado de cuenta
	 */
	public List<CodigoEstadoCuenta> findAll();

	/**
	 * Regresa todos los codigos de estado de cuenta por estatus
	 * @param estatus
	 * @param idEntidad
	 * @return
	 */
	public List<CodigoEstadoCuenta> findAllByEstatusAndEntidadId(boolean estatus, Long idEntidad);
}
