/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Actividad;

/**
 * @name ActividadRepository
 * @description Interfaz que contiene los metodos relacionados con las
 *              actividades y que permiten realizar operaciones a nivel base de
 *              datos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 13:01 hrs.
 * @version 0.1
 */
@Repository("actividadRepository")
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

	/**
	 * Regresa una lista de objetos de tipo ConsultaActividadDTO por folio de
	 * conciliacion y un rango de fechas inicio y fin
	 * 
	 * @param folio
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO(act.folio, act.fecha, act.descripcion) FROM Actividad act WHERE act.folio = :folio AND act.fecha BETWEEN :fechaDesde AND :fechaHasta  ORDER BY act.fecha DESC")
	public List<ConsultaActividadDTO> findByFolioFechaDesdeAndFechaHasta(@Param("folio") final Long folio,
			@Param("fechaDesde") final Date fechaDesde, @Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa una lista de objetos de tipo ConsultaActividadDTO por folio y fecha
	 * de inicio
	 * 
	 * @param folio
	 * @param fechaDesde
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO(act.folio, act.fecha, act.descripcion) FROM Actividad act WHERE act.folio = :folio AND act.fecha >= :fechaDesde  ORDER BY act.fecha DESC")
	public List<ConsultaActividadDTO> findByFolioAndFechaDesde(@Param("folio") final Long folio,
			@Param("fechaDesde") final Date fechaDesde);

	/**
	 * Regresa una lista de objetos de tipo ConsultaActividadDTO a partir de un
	 * folio y una fecha de fin
	 * 
	 * @param folio
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO(act.folio, act.fecha, act.descripcion) FROM Actividad act WHERE act.folio = :folio AND act.fecha <= :fechaHasta  ORDER BY act.fecha DESC")
	public List<ConsultaActividadDTO> findByFolioAndFechaHasta(@Param("folio") final Long folio,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa una lista de objetos de tipo ConsultaActividadDTO a partir de un
	 * folio de conciliacion
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO(act.folio, act.fecha, act.descripcion) FROM Actividad act WHERE act.folio = :folio ORDER BY act.fecha DESC")
	public List<ConsultaActividadDTO> findByFolio(@Param("folio") final Long folio);

}
