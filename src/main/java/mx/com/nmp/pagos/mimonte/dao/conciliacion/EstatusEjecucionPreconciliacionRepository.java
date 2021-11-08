/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionPreconciliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @name EstatusEjecucionPreconciliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el estatus de la ejecución de la preconciliación
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 29/10/2021 16:56 hrs.
 * @version 0.1
 */
@Repository("estatusEjecucionPreconciliacionRepository")
public interface EstatusEjecucionPreconciliacionRepository extends JpaRepository<EstatusEjecucionPreconciliacion, Integer>{

	/**
	 * Búsqueda del estatus de la ejecución de la preconciliación a partir de la descripción.
	 * @param descripcion
	 * @return
	 */
	@Query("FROM EstatusEjecucionPreconciliacion ec WHERE ec.descripcion = :descripcion")
	public EstatusEjecucionPreconciliacion findByDescripcion(@Param("descripcion") final String descripcion);

}
