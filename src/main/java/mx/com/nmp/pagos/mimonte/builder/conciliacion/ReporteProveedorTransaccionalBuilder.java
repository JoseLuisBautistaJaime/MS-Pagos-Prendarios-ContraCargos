/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Set;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProveedorTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReporteProveedorTransaccionalBuilder Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con la
 * ReporteProveedorTransaccionalBuilder
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 14/05/2019 13:28 hrs.
 * @version 0.1
 */
public abstract class ReporteProveedorTransaccionalBuilder {
	
	
	
	public ReporteProveedorTransaccionalBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un objeto de tipo ReporteProveedorTransaccionalDTO a partir de un entitie de tipo Reporte.
	 * @param reporte
	 * @return reporteProveedorTransaccionalDTO
	 */
	public static ReporteProveedorTransaccionalDTO buildReporteProveedorTransaccionalDTOFromReporte(Reporte reporte) {
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = null;
		if(reporte != null) {
			reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO();
			reporteProveedorTransaccionalDTO.setFechaDesde(reporte.getFechaDesde());
			reporteProveedorTransaccionalDTO.setFechaHasta(reporte.getFechaHasta());
			reporteProveedorTransaccionalDTO.setDisponible(reporte.getDisponible());
		}
		return reporteProveedorTransaccionalDTO;
	}
	
	/**
	 * Construye un set de objetos de tipo ReporteProveedorTransaccionalDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return
	 */
	public static ReporteProveedorTransaccionalDTO buildReporteProveedorTransaccionalDTOSetFromReporteSet(Set<Reporte> reporteSet){
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTOSet = null;
		if(reporteSet != null && !reporteSet.isEmpty()) {
			reporteProveedorTransaccionalDTOSet = new ReporteProveedorTransaccionalDTO();
			for(Reporte reporte : reporteSet) {
				if(reporte.getTipo().equals(TipoReporteEnum.PROVEEDOR.name())) {
					reporteProveedorTransaccionalDTOSet.setFechaHasta(reporte.getFechaHasta());
					reporteProveedorTransaccionalDTOSet.setFechaDesde(reporte.getFechaDesde());
					reporteProveedorTransaccionalDTOSet.setDisponible(reporte.getDisponible());
					break;
				}
			}
		}
		return reporteProveedorTransaccionalDTOSet;
	}
	
	/**
	 * Construye un entitie de tipo Reporte a partir de un objeto de tipo ReporteProveedorTransaccionalDTO.
	 * @param reporteProveedorTransaccionalDTO
	 * @return
	 */
	public static Reporte buildReporteFromReporteProveedorTransaccionalDTO(ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO) {
		Reporte reporte = null;
		if(reporteProveedorTransaccionalDTO != null) {
			reporte = new Reporte();
			reporte.setFechaDesde(reporteProveedorTransaccionalDTO.getFechaDesde());
			reporte.setFechaHasta(reporteProveedorTransaccionalDTO.getFechaHasta());
			reporte.setDisponible(reporteProveedorTransaccionalDTO.getDisponible());
		}
		return reporte;
	}

}
