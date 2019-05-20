/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Set;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReporteEstadoCuentaDTO Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con la
 * ReporteEstadoCuentaDTO
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 14/05/2019 13:28 hrs.
 * @version 0.1
 */
public abstract class ReporteEstadoCuentaBuilder {
	
	
	
	public ReporteEstadoCuentaBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un objeto de tipo ReporteEstadoCuentaDTO a partir de un entitie de tipo Reporte.
	 * @param reporte
	 * @return reporteEstadoCuentaDTO
	 */
	public static ReporteEstadoCuentaDTO buildReporteEstadoCuentaDTOFromReporte(Reporte reporte) {
		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = null;
		if(reporte != null) {
			reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO();
			reporteEstadoCuentaDTO.setFechaDesde(reporte.getFechaDesde());
			reporteEstadoCuentaDTO.setFechaHasta(reporte.getFechaHasta());
			reporteEstadoCuentaDTO.setDisponible(reporte.getDisponible());
		}
		return reporteEstadoCuentaDTO;
	}
	
	/**
	 * Construye un entitie de tipo Reporte a partir de un objeto de tipo ReporteEstadoCuentaDTO.
	 * @param reporteEstadoCuentaDTO
	 * @return reporte
	 */
	public static Reporte buildReporteFromReporteEstadoCuentaDTO(ReporteEstadoCuentaDTO reporteEstadoCuentaDTO) {
		Reporte reporte = null;
		if(reporteEstadoCuentaDTO != null) {
			reporte = new Reporte();
			reporte.setFechaDesde(reporteEstadoCuentaDTO.getFechaDesde());
			reporte.setFechaHasta(reporteEstadoCuentaDTO.getFechaHasta());
			reporte.setDisponible(reporteEstadoCuentaDTO.getDisponible());
		}
		return reporte;
	}
	
	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static ReporteEstadoCuentaDTO buildReporteEstadoCuentaDTOSetFromReporteSet(Set<Reporte> reporteSet){
		ReporteEstadoCuentaDTO reporteEstadoCuentaDTOSet = null;
		if(reporteSet != null && !reporteSet.isEmpty()) {
			reporteEstadoCuentaDTOSet = new ReporteEstadoCuentaDTO();
			for(Reporte reporte : reporteSet) {
				if(reporte.getTipo().equals(TipoReporteEnum.ESTADO_CUENTA.name())) {
					reporteEstadoCuentaDTOSet.setFechaDesde(reporte.getFechaDesde());
					reporteEstadoCuentaDTOSet.setFechaHasta(reporte.getFechaHasta());
					reporteEstadoCuentaDTOSet.setDisponible(reporte.getDisponible());
					break;
				}
			}
		}
		return reporteEstadoCuentaDTOSet;
	}

}
