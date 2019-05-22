/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProveedorTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReporteProveedorTransaccionalBuilder Descripcion: Clase de capa de
 * builder que se encarga de convertir difrentes tipos de objetos y entidades
 * relacionadas con la ReporteProveedorTransaccionalBuilder
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
	 * Construye un set de objetos de tipo ReporteProveedorTransaccionalDTO a partir
	 * de una lista de entities de tipo Reporte.
	 * 
	 * @param reporteSet
	 * @return reporteProveedorTransaccionalDTO
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static ReporteProveedorTransaccionalDTO buildReporteProveedorTransaccionalDTOFromReporteList(
			List<Reporte> reporteList) {
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = null;
		if (reporteList != null && !reporteList.isEmpty()) {
			reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO();
			for (Reporte reporte : reporteList) {
				if (reporte.getTipo().equals(TipoReporteEnum.PROVEEDOR.name())) {
					reporteProveedorTransaccionalDTO.setFechaHasta(reporte.getFechaHasta());
					reporteProveedorTransaccionalDTO.setFechaDesde(reporte.getFechaDesde());
					reporteProveedorTransaccionalDTO.setDisponible(reporte.getDisponible());
					break;
				}
			}
		}
		return reporteProveedorTransaccionalDTO;
	}

}
