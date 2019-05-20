/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;

/**
 * @name EstatusConciliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el Estatus de la conciliación
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 07/05/2019 16:56 hrs.
 * @version 0.1
 */
@Repository("estatusConciliacionRepository")
public interface EstatusConciliacionRepository extends JpaRepository<EstatusConciliacion, Integer>{
	
	@Query("FROM EstatusConciliacion ec WHERE ec.nombre = :nombre")
	public EstatusConciliacion findByNombre(@Param("nombre") final String nombre);
	
	@Query("FROM EstatusConciliacion ec WHERE ec.id = :idEstatus")
	public EstatusConciliacion findByIdEstatus(@Param("idEstatus") final Integer idEstatus);

}
