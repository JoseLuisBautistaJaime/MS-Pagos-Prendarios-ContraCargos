/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.TipoContacto;

/**
 * @name TipoContactoRepository @description Interface de capa DAO que sirve
 *       para realizar operaciones de base de datos relacionadas con el catalogo
 *       TipoContacto
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 28/03/2019 12:35 hrs.
 * @version 0.1
 */
@Repository
public interface TipoContactoRepository extends JpaRepository<TipoContacto, Long> {

	/**
	 * Regresa un id de tipo de contacto por nombre
	 * 
	 * @param name
	 * @return
	 */
	public TipoContacto findByDescription(final String name);

}
