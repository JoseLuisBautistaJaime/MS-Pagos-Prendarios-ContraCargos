/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProcesosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReporteProcesosNocturnosBuilder Descripcion: Clase de capa de builder que se
 * encarga de convertir difrentes tipos de objetos y entidades relacionadas con
 * ReporteProcesosNocturnosBuilder
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 06/05/2019 13:21 hrs.
 * @version 0.1
 */
public abstract class ReporteProcesosNocturnosBuilder {
	
	
	public ReporteProcesosNocturnosBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un set de objetos de tipo ReporteProcesosNocturnosDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return
	 */              
	public static ReporteProcesosNocturnosDTO buildReporteProcesosNocturnosDTOSetFromReporteSet(Set<Reporte> reporteSet){
		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = null;
		if(reporteSet != null && !reporteSet.isEmpty()) {
			reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO();
			for(Reporte reporte : reporteSet) {
				if(reporte.getTipo().equals(TipoReporteEnum.MIDAS.name())) {
					reporteProcesosNocturnosDTO.setFechaDesde(reporte.getFechaDesde());
					reporteProcesosNocturnosDTO.setFechaHasta(reporte.getFechaHasta());
					reporteProcesosNocturnosDTO.setDisponible(reporte.getDisponible());
					break;
				}
			}
		}
		return reporteProcesosNocturnosDTO;
	}
	
	/**
	 * Construye una objeto de tipo ReporteProcesosNocturnosDTO a partir de una entidad de tipo Reporte.
	 * @param reporte
	 * @return reporteProcesosNocturnosDTO
	 */
	public static ConciliacionDTOList buildReporteProcesosNocturnosDTOFromReporte(Reporte reporte) {
		ConciliacionDTOList reporteProcesosNocturnosDTO = null;
		if(reporte != null) {
			reporteProcesosNocturnosDTO = new ConciliacionDTOList();
			reporteProcesosNocturnosDTO.getReporteProcesosNocturnos().setFechaDesde(reporte.getFechaDesde());
			reporteProcesosNocturnosDTO.getReporteProcesosNocturnos().setFechaHasta(reporte.getFechaHasta());
			reporteProcesosNocturnosDTO.getReporteProcesosNocturnos().setDisponible(reporte.getDisponible());
		}
		return reporteProcesosNocturnosDTO;
	}
	
	/**
	 * Construye una entidad de tipo Reporte a partir de un objeto de tipo ReporteProcesosNocturnosDTO.
	 * @param reporteProcesosNocturnosDTO
	 * @return reporte
	 */
	public static Reporte buildReporteFromReporteProcesosNocturnosDTO(ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO) {
		Reporte reporte = null;
		if(reporteProcesosNocturnosDTO != null) {
			reporte = new Reporte();
			reporte.setFechaDesde(reporteProcesosNocturnosDTO.getFechaDesde());
			reporte.setFechaHasta(reporteProcesosNocturnosDTO.getFechaHasta());
			reporte.setDisponible(reporteProcesosNocturnosDTO.getDisponible());
		}
		return reporte;
	}
	
	public static Set<Reporte> buildReporteSetFromReporteProcesosNocturnosDTOSet(Set<ReporteProcesosNocturnosDTO> reporteProcesosNocturnosDTOList){
		Set<Reporte> reporteSet = null;
		if(reporteProcesosNocturnosDTOList != null && !reporteProcesosNocturnosDTOList.isEmpty()) {
			reporteSet = new TreeSet<>();
			for(ReporteProcesosNocturnosDTO rS: reporteProcesosNocturnosDTOList) {
				reporteSet.add(buildReporteFromReporteProcesosNocturnosDTO(rS));
			}
		}
		return reporteSet;
	}
	

	
//	/**
//	 * Construye un set de objetos de tipo ReporteProcesosNocturnosDTO a partir de un set de entities de tipo Reporte.
//	 * @param reporteSet
//	 * @return
//	 */
//	public static Set<ConciliacionDTOList> buildReporteProcesosNocturnosDTOSetFromReporteSet(Set<Reporte> reporteSet){
//		Set<ConciliacionDTOList> ReporteProcesosNocturnosDTOSet = null;
//		if(reporteSet != null && !reporteSet.isEmpty()) {
//			ReporteProcesosNocturnosDTOSet = new TreeSet<>();
//			for(Reporte reporte : reporteSet) {
//				ReporteProcesosNocturnosDTOSet.add(buildReporteProcesosNocturnosDTOFromReporte(reporte));
//			}
//		}
//		return null;
//		
//	}

}
