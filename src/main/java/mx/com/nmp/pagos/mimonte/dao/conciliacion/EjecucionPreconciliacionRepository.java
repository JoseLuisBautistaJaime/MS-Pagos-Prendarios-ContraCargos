package mx.com.nmp.pagos.mimonte.dao.conciliacion;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 29-Oct-2021 6:33:59 PM
 */
@Repository("ejecucionPreconciliacionRepository")
public interface EjecucionPreconciliacionRepository extends PagingAndSortingRepository<EjecucionPreconciliacion, Long> {


	/**
	 * Busqueda de las ejecuciones de preconciliacíon a partir de sus propiedades.
	 *
	 * @param idEstatus
	 * @param estatusDescripcion
	 * @param fechaPeriodoInicio
	 * @param fechaPeriodoFin
	 * @param fechaEjecucionDesde
	 * @param fechaEjecucionHasta
	 * @param proveedor
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionDTO(c.id, e.id, e.descripcionCorta, e.descripcion, e.orderNumber, c.fechaEjecucion, c.fechaPeriodoInicio, c.fechaPeriodoFin, c.createdBy, c.createdDate, c.lastModifiedDate, c.lastModifiedBy, p.nombre ) FROM EjecucionPreconciliacion c " +
			"INNER JOIN EstatusEjecucionPreconciliacion e ON e.id = c.estatus.id " +
			"INNER JOIN Proveedor p ON p.id = c.proveedor.id " +
			"WHERE  ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) " +
			"AND ( :estatusDescripcion IS NULL OR c.estatusDescripcion = :estatusDescripcion ) " +
			"AND ( :fechaPeriodoInicio IS NULL OR c.fechaPeriodoInicio= :fechaPeriodoInicio) " +
			"AND ( :fechaPeriodoFin IS NULL OR c.fechaPeriodoFin = :fechaPeriodoFin ) " +
			"AND ( ( :fechaEjecucionDesde  IS NULL AND :fechaEjecucionHasta IS NULL ) OR ( :fechaEjecucionHasta IS NULL AND c.fechaEjecucion >= :fechaEjecucionDesde) OR ( :fechaEjecucionDesde IS NULL AND c.fechaEjecucion <= :fechaEjecucionHasta ) OR (c.fechaEjecucion BETWEEN :fechaEjecucionDesde AND :fechaEjecucionHasta) ) " +
			"AND ( :proveedor IS NULL OR c.proveedor.nombre = :proveedor)")
	public List<ConsultaEjecucionPreconciliacionDTO> findByPropiedades(@Param("idEstatus") final Integer idEstatus, @Param("estatusDescripcion") final String estatusDescripcion, @Param("fechaPeriodoInicio") final Date fechaPeriodoInicio, @Param("fechaPeriodoFin") final Date fechaPeriodoFin, @Param("fechaEjecucionDesde") final Date fechaEjecucionDesde, @Param("fechaEjecucionHasta") final Date fechaEjecucionHasta, @Param("proveedor") final CorresponsalEnum proveedor);


	/**
	 * Se actualiza el estatus de la ejecución de preconciliación por el id de la misma.
	 *
	 * @param idEjecucionPreconciliacion
	 * @param estatusEjecucionPreconciliacion
	 * @param usuario
	 * @param fecha
	 * @param descripcion
	 */
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE to_ejecucion_preconciliacion SET id_estatus_ejecucion = :estatusEjecucionPreconciliacion, estatus_descripcion = :descripcion, last_modified_by = :usuario, last_modified_date = :fecha WHERE id = :idEjecucionPreconciliacion")
	public void actualizaEstatusEjecucionPreconciliacion(@Param("idEjecucionPreconciliacion") final Long idEjecucionPreconciliacion,
                                                @Param("estatusEjecucionPreconciliacion") EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion, @Param("usuario") final String usuario, @Param("fecha") Date fecha, @Param("descripcion") final String descripcion);

	 /**
	  * Regresa el estatus de una ejecución de preconciliación por el id de la misma
	  * @param idEjecucionPreconciliacion
	  * @return
	 */
	@Query("SELECT pre.estatus FROM EjecucionPreconciliacion pre WHERE pre.id = :idEjecucionPreconciliacion")
	public EstatusEjecucionPreconciliacion findEstatusById(@Param("idEjecucionPreconciliacion") final Long idEjecucionPreconciliacion);


}