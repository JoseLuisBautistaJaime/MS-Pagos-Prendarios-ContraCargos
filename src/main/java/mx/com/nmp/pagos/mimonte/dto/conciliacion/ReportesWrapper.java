/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReportesWrapper
 * Descripcion: Clase que encapsula los reportes de las diferentes fuentes
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ReportesWrapper {

	private Long idConciliacion;
	private Long idEntidad;

	private Reporte reporteMidas;
	private Reporte reporteProveedor;
	private Reporte reporteEstadoCuenta;
	
	private CorresponsalEnum corresponsal;
	
	private List<Long> idsConciliacionesActualizar;
	


	public ReportesWrapper(Long idConciliacion, Long idEntidad, CorresponsalEnum corresponsal) {
		this.idConciliacion = idConciliacion;
		this.idEntidad = idEntidad;
		this.corresponsal = corresponsal;
	}


	public boolean contains(TipoReporteEnum tipoReporte) {
		boolean contains = false;
		switch (tipoReporte) {
			case MIDAS:
				contains = this.reporteMidas != null && this.reporteMidas.getId() > 0;
				break;
			case PROVEEDOR:
				contains = this.reporteProveedor != null && this.reporteProveedor.getId() > 0;
				break;
			case ESTADO_CUENTA:
				contains = this.reporteEstadoCuenta != null && this.reporteEstadoCuenta.getId() > 0;
				break;
		}
		return contains;
	}

	public Integer getIdReporte(TipoReporteEnum tipoReporte) {
		Integer idReporte = null;
		switch (tipoReporte) {
			case MIDAS:
				idReporte = this.reporteMidas != null ? this.reporteMidas.getId() : 0;
				break;
			case PROVEEDOR:
				idReporte = this.reporteProveedor != null ? this.reporteProveedor.getId() : 0;
				break;
			case ESTADO_CUENTA:
				idReporte = this.reporteEstadoCuenta != null ? this.reporteEstadoCuenta.getId() : 0;
				break;
		}
		return idReporte;
	}

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public Reporte getReporteMidas() {
		return reporteMidas;
	}

	public Reporte getReporteProveedor() {
		return reporteProveedor;
	}

	public Reporte getReporteEstadoCuenta() {
		return reporteEstadoCuenta;
	}

	public void setReporteMidas(Reporte reporteMidas) {
		this.reporteMidas = reporteMidas;
	}

	public void setReporteProveedor(Reporte reporteProveedor) {
		this.reporteProveedor = reporteProveedor;
	}

	public void setReporteEstadoCuenta(Reporte reporteEstadoCuenta) {
		this.reporteEstadoCuenta = reporteEstadoCuenta;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public List<Long> getIdsConciliacionesActualizar() {
		return idsConciliacionesActualizar;
	}

	public void setIdsConciliacionesActualizar(List<Long> idsConciliacionesActualizar) {
		this.idsConciliacionesActualizar = idsConciliacionesActualizar;
	}

}
