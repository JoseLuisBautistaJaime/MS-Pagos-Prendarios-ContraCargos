/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Actividad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @name ActividadPaginRepository
 * @description Interfaz que contiene los metodos relacionados con las
 *              actividades y que permiten realizar operaciones a nivel base de
 *              datos con paginacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/07/2019 10:13 hrs.
 * @version 0.1
 */
@Repository("actividadPaginRepository")
public interface ActividadPaginRepository extends PagingAndSortingRepository<Actividad, Long> {

	/**
	 * Regresa una lista de objetos de tipo ConsultaActividadDTO con los ultimos 10
	 * registros
	 * 
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO(act.folio, act.fecha, act.descripcion) FROM Actividad act INNER JOIN Conciliacion c ON c.id = act.folio WHERE ( :idCorresponsal IS NULL OR c.proveedor.id = :idCorresponsal ) ORDER BY act.fecha DESC")
	public List<ConsultaActividadDTO> nGetTopXActividades(Pageable pageable, @Param("idCorresponsal") final CorresponsalEnum idCorresponsal);

}
