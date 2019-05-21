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

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;

/**
 * @name ReportePagosRepository
 * @description Interface relacionada con el reporte de movimientos nocturnos
 *              (midas)
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/05/2019 14:35 hrs.
 * @version 0.1
 */
@Repository("reportePagosRepository")
public interface ReportePagosRepository extends JpaRepository<MovimientoMidas, Long> {

	/**
	 * Regresa una lista de objetos de tipo ReportePagosLibresDTO
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param producto
	 * @param operacion
	 * @param sucursales
	 * @param partida
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO(mm.fecha, mm.consumidor, mm.folio, mm.tipoContratoAbr, mm.operacionAbr, mm.sucursal, mm.monto) FROM MovimientoMidas mm WHERE mm.tipoContratoAbr = :producto AND mm.folio= :partida  AND mm.operacionAbr = :operacion AND mm.sucursal IN :sucursales AND mm.fecha BETWEEN :fechaDesde AND :fechaHasta")
	public List<ReportePagosLibresDTO> getReportePagosLibres(@Param("fechaDesde") Date fechaDesde,
			@Param("fechaHasta") Date fechaHasta, @Param("producto") final String producto,
			@Param("operacion") final String operacion, @Param("sucursales") List<Integer> sucursales,
			@Param("partida") final Long partida);

}
