/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * @name EntidadRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el catalogo Entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:35 hrs.
 * @version 0.1
 */
@Repository("entidadRepository")
public interface EntidadRepository extends JpaRepository<Entidad, Long> {

	/**
	 * Regresa una entidad por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Entidad> findByNombreAndEstatus(@Param("nombre") final String nombre,
			@Param("estatus") final Boolean estatus) throws EmptyResultDataAccessException;

	/**
	 * Regresa una lista de entidades por estatus
	 * 
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Entidad> findByEstatus(final Boolean estatus) throws EmptyResultDataAccessException;

	/**
	 * Regrsa una lista de entidades por nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Entidad> findByNombre(final String nombre) throws EmptyResultDataAccessException;

	/**
	 * Actualiza el estatus de un catalogo entidad por su id
	 * 
	 * @param estatus
	 * @param id
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @throws EmptyResultDataAccessException
	 */
	@Modifying
	public void setEstatusById(@Param("estatus") final Boolean estatus, @Param("id") final Long id,
			@Param("lastModifiedBy") final String lastModifiedBy,
			@Param("lastModifiedDate") final Date lastModifiedDate) throws EmptyResultDataAccessException;

	/**
	 * Regresa una entidad por id
	 */
	public Optional<Entidad> findById(final Long id);

	/**
	 * Regresa una lista de entidades por atributo nombre y description
	 * 
	 * @param nombre
	 * @param description
	 * @return
	 */
	public List<Entidad> findByNombreAndDescription(final String nombre, final String description);

	/**
	 * Regresa una lista de entidades por un id de contacto
	 * 
	 * @param idContacto
	 * @return
	 */
	public List<Entidad> findByContactos_Id(final Long idContacto);

}
