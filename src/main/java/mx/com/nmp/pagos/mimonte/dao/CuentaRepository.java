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
	 * Regresa el usuario creador de una entidad por su id
	 * 
	 * @param idEntidad
	 * @return
	 */
	@Query("SELECT ent.createdBy FROM Entidad ent WHERE ent.id = :idEntidad")
	public String findCreatedByByEntidadId(@Param("idEntidad") Long idEntidad);

	/**
	 * Regresa la fecha de creacion de una entidad por su id
	 * 
	 * @param idEntidad
	 * @return
	 */
	@Query("SELECT ent.createdDate FROM Entidad ent WHERE ent.id = :idEntidad")
	public Date findCreatedDateByEntidadId(@Param("idEntidad") Long idEntidad);

	/**
	 * Encuentra una o mas cuentas por el id de la entidad asociada a ella(s)
	 * 
	 * @param idEntidad
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Cuenta> qGetByEntidadId(@Param("idEntidad") final Long idEntidad) throws EmptyResultDataAccessException;

	/**
	 * Regresa una Cuenta por numero
	 * 
	 * @param numero
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws                                javax.persistence.NonUniqueResultException
	 */
	public Cuenta findByNumeroCuenta(final String numero)
			throws EmptyResultDataAccessException, javax.persistence.NonUniqueResultException;

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
	public List<Cuenta> findAll();
	
	/**
	 * Regresa el numero de cuenta en base a el id de conciliacion asociado
	 * @param conciliacionId
	 * @return
	 */
	@Query("SELECT c.numeroCuenta FROM Cuenta c INNER JOIN Conciliacion con ON con.cuenta.id = c.id WHERE con.id = :conciliacionId")
	public Object findCuentaNumeroByConciliacionId(@Param("conciliacionId") Long conciliacionId);
	
}
