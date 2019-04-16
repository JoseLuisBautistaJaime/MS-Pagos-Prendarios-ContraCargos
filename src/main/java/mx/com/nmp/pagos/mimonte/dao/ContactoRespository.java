/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
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
	 * @return List<Contactos>
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
	 * @return List<Contactos>
	 */
	@Query("FROM Contactos c WHERE (:idTipoContacto IS NULL OR c.tipoContacto.id = :idTipoContacto) AND "
			+ "(:nombre IS NULL OR c.nombre = :nombre) AND "
			+ "(:email IS NULL OR c.email = :email)")
	public List<Contactos> findByIdTipoContactoOrNombreOrEmail(@Param("idTipoContacto") final Long idTipoContacto,
			@Param("nombre") final String nombre, @Param("email") final String email);
	
	@Query("SELECT c FROM Contactos c WHERE c.email = :email")
	public Contactos findByEmail(@Param("email") final String email);
	
	
	public Optional<Contactos> findById(final Long idContacto);
		
	/**
	 * Regresa un set de contactos por id de entidad
	 * @param idEntidad
	 * @return
	 */
	public Set<Contactos> findByEntidades_Id(final Long idEntidad);
	
}
