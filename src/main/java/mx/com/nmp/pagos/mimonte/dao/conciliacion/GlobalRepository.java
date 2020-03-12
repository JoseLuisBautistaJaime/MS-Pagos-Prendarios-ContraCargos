/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;

/**
 * @name GlobalRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con global
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("globalRepository")
public interface GlobalRepository extends JpaRepository<Global, Long> {

	/**
	 * Búsqueda de global a partir del id de la conciliación
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("FROM Global g WHERE g.conciliacion.id = :idConciliacion")
	public Global findByIdConciliacion(@Param("idConciliacion") final Long idConciliacion);

}
