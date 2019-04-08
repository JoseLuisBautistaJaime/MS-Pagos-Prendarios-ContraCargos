/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Cuenta;

/**
 * @name CuentaRepository
 * @description Clase que encapsula la informacion de una cuenta a enviar como
 *              respuesta dentro del objeto Entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 07/03/2019 10:37 hrs.
 * @version 0.1
 */
@Repository("cuentaRepository")
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	/**
	 * Encuentra una o mas cuentas por el id de la entidad asociada a ella(s)
	 * 
	 * @param idEntidad
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
//	@Query("SELECT cta FROM Cuenta cta INNER JOIN cta.entidades ent WHERE ent.id = :idEntidad AND cta.estatus = true and ent.estatus = true")
	public List<Cuenta> findByEntidades_Id(/*@Param("idEntidad")*/ final Long idEntidad)
			throws EmptyResultDataAccessException;

	/**
	 * Regresa una Cuenta por numero
	 * 
	 * @param numero
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
//	@Query("SELECT cta FROM Cuenta cta WHERE cta.numeroCuenta = :numero AND cta.estatus = true")
	public Cuenta findByNumeroCuenta(/*@Param("numero")*/ final String numero) throws EmptyResultDataAccessException;

	/**
	 * Actualiza el estatus de una cuenta a false (inactivo)
	 * 
	 * @param estatus
	 * @param id
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @throws EmptyResultDataAccessException
	 */
	@Modifying
	@Query("UPDATE Cuenta cta SET cta.estatus = :estatus, cta.lastModifiedBy = :lastModifiedBy, cta.lastModifiedDate = :lastModifiedDate WHERE cta.id = :id")
	public void updateEstatusById(@Param("estatus") final Boolean estatus, @Param("id") final Long id,
			@Param("lastModifiedBy") final String lastModifiedBy,
			@Param("lastModifiedDate") final Date lastModifiedDate) throws EmptyResultDataAccessException;

	/**
	 * Regesa todas las cuentas
	 */
	@Query("SELECT cta FROM Cuenta cta ORDER BY cta.createdDate DESC")
	public List<Cuenta> findAll();

}
