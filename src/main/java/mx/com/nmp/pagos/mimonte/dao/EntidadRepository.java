/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	@Query("SELECT ent FROM Entidad ent WHERE (:nombre IS NULL OR ent.nombre = :nombre) AND (:estatus IS NULL OR ent.estatus = :estatus)")
	public List<Entidad> findByNombreAndEstatus(final String nombre, final Boolean estatus)
			throws EmptyResultDataAccessException;

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
	@Query("SELECT ent FROM Entidad ent WHERE ent.nombre = :nombre AND ent.estatus = true")
	public List<Entidad> findByNombre(@Param("nombre") final String nombre) throws EmptyResultDataAccessException;

	/**
	 * Actualiza el estatus de un catalogo entidad por su id
	 * 
	 * @param estatus
	 * @param id
	 * @throws EmptyResultDataAccessException
	 */
	@Modifying
	@Query("UPDATE Entidad ent set ent.estatus = :estatus WHERE ent.id = :id")
	public void setEstatusById(@Param("estatus") final Boolean estatus, @Param("id") final Long id)
			throws EmptyResultDataAccessException;

	/**
	 * Regresa una entidad por id
	 */
	@Query("SELECT ent FROM Entidad ent WHERE ent.id = :id AND ent.estatus = true")
	public Optional<Entidad> findById(@Param("id") final Long id);

}
