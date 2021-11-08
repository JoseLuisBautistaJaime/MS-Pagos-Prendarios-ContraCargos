package mx.com.nmp.pagos.mimonte.dao.conciliacion;


import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 29-Oct-2021 6:33:59 PM
 */
@Repository("ejecucionConciliacionRepository")
public interface EjecucionConciliacionRepository extends PagingAndSortingRepository<EjecucionConciliacion, Long> {

	/**
	 * Busqueda de las ejecuciones de conciliacióon a partir de sus propiedades.
	 *
	 * @param idEstatus
	 * @param idEntidad
	 * @param idCuenta
	 * @param idConciliacion
	 * @param fechaPeriodoInicio
	 * @param fechaPeriodoFin
	 * @param fechaEjecucionDesde
	 * @param fechaEjecucionHasta
	 * @param proveedor
	 * @return
	 */
	@Query("FROM EjecucionConciliacion c WHERE  ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad) AND( :idCuenta IS NULL OR c.cuenta.id = :idCuenta) AND ( :idConciliacion IS NULL OR c.conciliacion.id = :idConciliacion)  AND ( :fechaPeriodoInicio IS NULL OR c.fechaPeriodoInicio= :fechaPeriodoInicio) AND ( :fechaPeriodoFin IS NULL OR c.fechaPeriodoFin = :fechaPeriodoFin ) AND ( ( :fechaEjecucionDesde  IS NULL AND :fechaEjecucionHasta IS NULL ) OR ( :fechaEjecucionHasta IS NULL AND c.fechaEjecucion >= :fechaEjecucionDesde) OR ( :fechaEjecucionDesde IS NULL AND c.fechaEjecucion <= :fechaEjecucionHasta ) OR (c.fechaEjecucion BETWEEN :fechaEjecucionDesde AND :fechaEjecucionHasta) ) AND ( :proveedor IS NULL OR c.proveedor.nombre = :proveedor)")
	public List<EjecucionConciliacion> findByPropiedades(@Param("idEstatus") final Integer idEstatus, @Param("idEntidad") final Long idEntidad, @Param("idCuenta") final Long idCuenta, @Param("idConciliacion") final Long idConciliacion, @Param("fechaPeriodoInicio") final Date fechaPeriodoInicio, @Param("fechaPeriodoFin") final Date fechaPeriodoFin, @Param("fechaEjecucionDesde") final Date fechaEjecucionDesde, @Param("fechaEjecucionHasta") final Date fechaEjecucionHasta, @Param("proveedor") final CorresponsalEnum proveedor);


	/**
	 * Se actualiza el estatus de la ejecución de conciliación por el id de la misma.
	 *
	 * @param idEjecucionConciliacion
	 * @param estatusEjecucionConciliacion
	 * @param usuario
	 * @param fecha
	 * @param descripcion
	 */
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE to_ejecucion_conciliacion SET id_estatus_ejecucion = :estatusEjecucionConciliacion, estatus_descripcion = :descripcion, last_modified_by = :usuario, last_modified_date = :fecha WHERE id = :idEjecucionConciliacion")
	public void actualizaEstatusEjecucionConciliacion(@Param("idEjecucionConciliacion") final Long idEjecucionConciliacion,
                                                         @Param("estatusEjecucionConciliacion") EstatusEjecucionConciliacion estatusEjecucionConciliacion, @Param("usuario") final String usuario, @Param("fecha") Date fecha, @Param("descripcion") final String descripcion);

	 /**
	  * Regresa el estatus de una ejecución de conciliación por el id de la misma
	  * @param idEjecucionConciliacion
	  * @return
	 */
	@Query("SELECT pre.estatus FROM EjecucionConciliacion pre WHERE pre.id = :idEjecucionConciliacion")
	public EstatusEjecucionConciliacion findEstatusById(@Param("idEjecucionConciliacion") final Long idEjecucionConciliacion);


}