package mx.com.nmp.pagos.mimonte.dao.conciliacion;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CalendarioEjecucionProceso;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 19-Nov-2021 6:33:59 PM
 */
@Repository("calendarioEjecucionProcesoRepository")
public interface CalendarioEjecucionProcesoRepository extends PagingAndSortingRepository<CalendarioEjecucionProceso, Long> {


	/**
	 * Busqueda de las calendarizaciones de las ejecuciones de los procesos de automatización a partir de sus propiedades.
	 *
	 * @param idCalendario
	 * @param idProceso
	 * @param isActivo
	 * @param reintentos
	 * @param proveedor
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO(c.id, e.id, e.descripcionCorta, e.descripcion, c.configuracion, c.reintentos, c.rangoDiasCobertura, c.activo, c.createdBy, c.createdDate, c.lastModifiedDate, c.lastModifiedBy, p.nombre ) FROM CalendarioEjecucionProceso c " +
			"INNER JOIN CatalogoProceso e ON e.id = c.proceso.id " +
			"INNER JOIN Proveedor p ON p.id = c.proveedor.id " +
			"WHERE  ( :idCalendario IS NULL OR c.id = :idCalendario) " +
			"AND ( :idProceso IS NULL OR c.proceso.id = :idProceso) " +
			"AND ( :reintentos IS NULL OR c.reintentos= :reintentos) " +
			"AND ( :isActivo IS NULL OR c.activo = :isActivo ) " +
			"AND ( :proveedor IS NULL OR c.proveedor.nombre = :proveedor)")
	public List<CalendarioEjecucionProcesoDTO> findByPropiedades(@Param("idCalendario") final Long idCalendario, @Param("idProceso") final Long idProceso, @Param("isActivo") final Boolean isActivo, @Param("reintentos") final Integer reintentos, @Param("proveedor") final CorresponsalEnum proveedor);



}