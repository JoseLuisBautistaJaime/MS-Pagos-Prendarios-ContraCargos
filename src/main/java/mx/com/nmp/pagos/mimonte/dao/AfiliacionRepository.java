/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
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
	public Set<Afiliacion> findByCuentas_Id(final Long idCuenta) throws EmptyResultDataAccessException;

	/**
	 * Regresa una afiliacion por numero
	 * 
	 * @param numeroCuenta
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public Afiliacion findByNumero(final Long numeroCuenta) throws EmptyResultDataAccessException;

	/**
	 * Regresa todas las afiliaciones
	 */
	public List<Afiliacion> findAll();

}
