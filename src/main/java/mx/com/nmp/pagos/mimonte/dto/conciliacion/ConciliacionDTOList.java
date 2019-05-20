/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.List;

/**
 * @name ConciliacionDTOList
 * @description Clase que encapsula el request de ConciliacionDTOList para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 05/04/2019 14:31 hrs.
 * @version 0.1
 */
public class ConciliacionDTOList extends AbstractConciliacionDTO implements Comparable<ConciliacionDTOList> {
	
	private Integer Folio;
	private EstatusConciliacionDTO estatus;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private ReporteProcesosNocturnosDTO reporteProcesosNocturnos;
	private ReporteProveedorTransaccionalDTO reporteProveedorTransaccional;
	private ReporteEstadoCuentaDTO reporteEstadoCuenta;
	private GlobalDTO global;
	private List<DevolucionConDTO> devoluciones;
	private List<MovTransitoDTO> movimientosTransito;
	private List<ComisionesDTO> comisiones;

	public ConciliacionDTOList() {
		super();
	}

	public ConciliacionDTOList(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public ConciliacionDTOList(Integer folio, EstatusConciliacionDTO estatus, EntidadDTO entidad, CuentaDTO cuenta,
			ReporteProcesosNocturnosDTO reporteProcesosNocturnos,
			ReporteProveedorTransaccionalDTO reporteProveedorTransaccional, ReporteEstadoCuentaDTO reporteEstadoCuenta,
			GlobalDTO global, List<DevolucionConDTO> devoluciones, List<MovTransitoDTO> movimientosTransito,
			List<ComisionesDTO> comisiones, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super();
		Folio = folio;
		this.estatus = estatus;
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.reporteProcesosNocturnos = reporteProcesosNocturnos;
		this.reporteProveedorTransaccional = reporteProveedorTransaccional;
		this.reporteEstadoCuenta = reporteEstadoCuenta;
		this.global = global;
		this.devoluciones = devoluciones;
		this.movimientosTransito = movimientosTransito;
		this.comisiones = comisiones;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 679398017169972235L;

	public Integer getFolio() {
		return Folio;
	}

	public void setFolio(Integer folio) {
		Folio = folio;
	}

	public EstatusConciliacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusConciliacionDTO estatus) {
		this.estatus = estatus;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public CuentaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDTO cuenta) {
		this.cuenta = cuenta;
	}

	public ReporteProcesosNocturnosDTO getReporteProcesosNocturnos() {
		return reporteProcesosNocturnos;
	}

	public void setReporteProcesosNocturnos(ReporteProcesosNocturnosDTO reporteProcesosNocturnos) {
		this.reporteProcesosNocturnos = reporteProcesosNocturnos;
	}

	public ReporteProveedorTransaccionalDTO getReporteProveedorTransaccional() {
		return reporteProveedorTransaccional;
	}

	public void setReporteProveedorTransaccional(ReporteProveedorTransaccionalDTO reporteProveedorTransaccional) {
		this.reporteProveedorTransaccional = reporteProveedorTransaccional;
	}

	public ReporteEstadoCuentaDTO getReporteEstadoCuenta() {
		return reporteEstadoCuenta;
	}

	public void setReporteEstadoCuenta(ReporteEstadoCuentaDTO reporteEstadoCuenta) {
		this.reporteEstadoCuenta = reporteEstadoCuenta;
	}

	public GlobalDTO getGlobal() {
		return global;
	}

	public void setGlobal(GlobalDTO global) {
		this.global = global;
	}

	public List<DevolucionConDTO> getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(List<DevolucionConDTO> devoluciones) {
		this.devoluciones = devoluciones;
	}

	public List<MovTransitoDTO> getMovimientosTransito() {
		return movimientosTransito;
	}

	public void setMovimientosTransito(List<MovTransitoDTO> movimientosTransito) {
		this.movimientosTransito = movimientosTransito;
	}

	public List<ComisionesDTO> getComisiones() {
		return comisiones;
	}

	public void setComisiones(List<ComisionesDTO> comisiones) {
		this.comisiones = comisiones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ConciliacionDTOList [Folio=" + Folio + ", estatus=" + estatus + ", entidad=" + entidad + ", cuenta="
				+ cuenta + ", reporteProcesosNocturnos=" + reporteProcesosNocturnos + ", reporteProveedorTransaccional="
				+ reporteProveedorTransaccional + ", reporteEstadoCuenta=" + reporteEstadoCuenta + ", global=" + global
				+ ", devoluciones=" + devoluciones + ", movimientosTransito=" + movimientosTransito + ", comisiones="
				+ comisiones + "]";
	}

	@Override
	public int compareTo(ConciliacionDTOList arg0) {
		return 0;
	}

}
