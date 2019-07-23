/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Afiliacion;

/**
 * @name AfiliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el catalogo Afiliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 21:01 hrs.
 * @version 0.1
 */
@Repository("afiliacionRepository")
public interface AfiliacionRepository extends JpaRepository<Afiliacion, Long> {

	/**
	 * Regresa una lista de afiliaciones en base a el id de su cuenta asociada
	 * 
	 * @param idCuenta
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public Set<Afiliacion> findByCuentas_Id(final Long idCuenta) throws EmptyResultDataAccessException;

	/**
	 * Regresa una afiliacion por numero y que no tenga un id especifico
	 * 
	 * @param numeroAfiliacion
	 * @param id
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws                                javax.persistence.NonUniqueResultException
	 */
	@Query("SELECT af FROM Afiliacion af WHERE af.numero = :numeroAfiliacion AND af.id <> :id")
	public Afiliacion findByNumeroAndNotId(@Param("numeroAfiliacion") final String numeroAfiliacion,
			@Param("id") final Long id)
			throws EmptyResultDataAccessException, javax.persistence.NonUniqueResultException;

	/**
	 * Regresa una afiliacion por numero
	 * 
	 * @param numeroAfiliacion
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws                                javax.persistence.NonUniqueResultException
	 */
	public Afiliacion findByNumero(final String numeroAfiliacion)
			throws EmptyResultDataAccessException, javax.persistence.NonUniqueResultException;

	/**
	 * Regresa todas las afiliaciones
	 */
	public List<Afiliacion> findAll();

}
