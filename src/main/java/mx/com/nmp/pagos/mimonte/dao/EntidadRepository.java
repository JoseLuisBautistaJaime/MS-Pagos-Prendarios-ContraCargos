/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * Nombre: EntidadRepository Descripcion: Interface de capa DAO que sirve para
 * realizar operaciones de base de datos relacionadas con el catalogo Entidad
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
	 */
	public Entidad findByNombreAndEstatus(final String nombre, final Boolean estatus);

}
