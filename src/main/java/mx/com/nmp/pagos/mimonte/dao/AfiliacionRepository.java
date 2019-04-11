/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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
//	@Query("SELECT af FROM Afiliacion af INNER JOIN af.cuentas cta WHERE af.estatus = true AND cta.estatus = true AND cta.id = :idCuenta")
//	public Set<Afiliacion> findByCuentas_Id(@Param("idCuenta") final Long idCuenta)
//			throws EmptyResultDataAccessException;

	/**
	 * Regresa una afiliacion por numero
	 * 
	 * @param numeroCuenta
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
//	@Query("SELECT af FROM Afiliacion af WHERE af.numero = :numeroCuenta and af.estatus = true")
//	public Afiliacion findByNumero(@Param("numeroCuenta") final Long numeroCuenta)
//			throws EmptyResultDataAccessException;

	/**
	 * Regresa todas las afiliaciones
	 */
//	@Query("SELECT af FROM Afiliacion af WHERE af.estatus = true")
//	public List<Afiliacion> findAll();

}
