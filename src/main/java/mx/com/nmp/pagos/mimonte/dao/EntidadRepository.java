package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * Nombre: EntidadRepository Descripcion: Interface de capa DAO que sirve para
 * realizar operaciones de base de datos relacionadas con el catalogo Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
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
	 */
	public Entidad findByNombreAndEstatus(final String nombre, final Boolean estatus);

}
