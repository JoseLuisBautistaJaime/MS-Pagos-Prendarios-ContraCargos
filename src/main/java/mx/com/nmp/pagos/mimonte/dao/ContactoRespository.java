/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Contactos;

/**
 * Nombre: ContactoRespository Descripcion: Repositorio que realiza las
 * consultas sobre la entidad {@link Contacto}
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 15:33 hrs.
 * @version 0.1
 */
@Repository
public interface ContactoRespository extends JpaRepository<Contactos, Long> {

	/**
	 * Consulta que obtiene los registros en base de datos a partir del parametro
	 * idTipoContacto.
	 * 
	 * @param idTipoContacto
	 * @return
	 */
	@Query("FROM Contactos c WHERE c.tipoContacto.id = :idTipoContacto")
	public List<Contactos> findByIdTipoContacto(@Param("idTipoContacto") final Long idTipoContacto);

	/**
	 * Conaulta que obtiene los registros de base de datos a partir de los
	 * parametros idTipoContacto, nombre y email.
	 * 
	 * @param idTipoContacto
	 * @param nombre
	 * @param email
	 * @return
	 */
	@Query("FROM Contactos c WHERE (:idTipoContacto IS NULL OR c.tipoContacto.id = :idTipoContacto) AND "
			+ "(:nombre IS NULL OR c.nombre = :nombre) AND " + "(:email IS NULL OR c.email = :email)")
	public List<Contactos> findByIdTipoContactoOrNombreOrEmail(@Param("idTipoContacto") final Long idTipoContacto,
			@Param("nombre") final String nombre, @Param("email") final String email);

	/**
	 * Regresa un contacto por email
	 * 
	 * @param email
	 * @return
	 */
	@Query("SELECT c FROM Contactos c WHERE c.email = :email")
	public Contactos findByEmail(@Param("email") final String email);

	/**
	 * Regresa un contacto por id
	 */
	public Optional<Contactos> findById(final Long idContacto);

	/**
	 * Regresa un set de contactos por id de entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public Set<Contactos> findByEntidades_Id(final Long idEntidad);

	/**
	 * Elimina los contactos de tipo Entidad que no estan asociados a ninguna
	 * entidad
	 * 
	 * @param idTipoContacto
	 */
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tc_contactos WHERE id NOT IN(SELECT DISTINCT cto.id FROM (SELECT * FROM tc_contactos) cto INNER JOIN tr_entidad_contactos eco ON cto.id = eco.id_contacto) AND id_tipo_contacto = :idTipoContacto")
	public void deleteWithNoAccountAssociation(@Param("idTipoContacto") final Long idTipoContacto);

	/**
	 * Regresa un valor 1 cuando el email no existe, de lo contrario regresa un 0
	 * 
	 * @param email
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(cto.id) FROM tc_contactos cto WHERE cto.email = :email) = 0) THEN 1 ELSE 0 END")
	public Object validateDuplicateEmail(@Param("email") final String email);

	/**
	 * Regesa un valor de 1 cuando un email de contacto no existe (a menos que sea
	 * el mismo contacto), de lo contrario regresa un valor 0
	 * 
	 * @param email
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(cto.id) FROM tc_contactos cto WHERE cto.email = :email AND cto.id <> :id) = 0) THEN 1 ELSE 0 END")
	public Object validateDuplicateEmailUpdt(@Param("email") final String email, @Param("id") final Long id);

	/**
	 * Regresa el dato createdBy de un contacto por su id
	 * 
	 * @param idCto
	 * @return
	 */
	@Query("SELECT cto.createdBy FROM Contactos cto WHERE cto.id = :idCto")
	public Object getCreatedByById(@Param("idCto") final Long idCto);

}
