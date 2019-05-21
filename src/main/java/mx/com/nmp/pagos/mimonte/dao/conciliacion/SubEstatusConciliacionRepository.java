/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * @name SubEstatusConciliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el sub estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("subEstatusConciliacionRepository")
public interface SubEstatusConciliacionRepository extends JpaRepository<SubEstatusConciliacion, Integer> {

	/**
	 * Búsqueda del sub estatus de la conciliación a partir de la descripcion.
	 * 
	 * @param descripcion
	 * @return
	 */
	@Query("FROM SubEstatusConciliacion sec WHERE sec.description = :descripcion")
	public SubEstatusConciliacion findByDescripcion(@Param("descripcion") final String descripcion);

}
