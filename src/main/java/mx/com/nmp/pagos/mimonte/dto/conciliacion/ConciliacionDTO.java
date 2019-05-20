/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:34 PM
 */
public class ConciliacionDTO extends AbstractConciliacionDTO implements Comparable<ConciliacionDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 1838609195762330516L;

	private Integer folio;
	private EstatusConciliacionDTO estatus;
	private SubEstatusConciliacionDTO subEstatus;
	private String subEstatusDescripcion;
	private String idPolizaTesoreria;
	private String idAsientoContable;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private ReporteProcesosNocturnosDTO reporteProcesosNocturnos;
	private ReporteProveedorTransaccionalDTO reporteProveedorTransaccional;
	private ReporteEstadoCuentaDTO reporteEstadoCuenta;
	private GlobalDTO global;
	private DevolucionConDTO devoluciones;
	private MovTransitoDTO movimientosTransito;
	private ComisionesDTO comisiones;

	public ConciliacionDTO() {
		super();
	}

	public ConciliacionDTO(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public ConciliacionDTO(Integer folio, EstatusConciliacionDTO estatus, EntidadDTO entidad, CuentaDTO cuenta,
			ReporteProcesosNocturnosDTO reporteProcesosNocturnos,
			ReporteProveedorTransaccionalDTO reporteProveedorTransaccional, ReporteEstadoCuentaDTO reporteEstadoCuenta,
			GlobalDTO global, DevolucionConDTO devoluciones, MovTransitoDTO movimientosTransito, ComisionesDTO comisiones, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
		this.folio = folio;
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
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
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

	public DevolucionConDTO getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(DevolucionConDTO devoluciones) {
		this.devoluciones = devoluciones;
	}

	public MovTransitoDTO getMovimientosTransito() {
		return movimientosTransito;
	}

	public void setMovimientosTransito(MovTransitoDTO movimientosTransito) {
		this.movimientosTransito = movimientosTransito;
	}

	public ComisionesDTO getComisiones() {
		return comisiones;
	}

	public void setComisiones(ComisionesDTO comisiones) {
		this.comisiones = comisiones;
	}
	
	public SubEstatusConciliacionDTO getSubEstatus() {
		return subEstatus;
	}

	public void setSubEstatus(SubEstatusConciliacionDTO subEstatus) {
		this.subEstatus = subEstatus;
	}

	public String getSubEstatusDescripcion() {
		return subEstatusDescripcion;
	}

	public void setSubEstatusDescripcion(String subEstatusDescripcion) {
		this.subEstatusDescripcion = subEstatusDescripcion;
	}

	public String getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(String idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public String getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(String idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}

	@Override
	public String toString() {
		return "ConciliacionDTO [folio=" + folio + ", estatus=" + estatus + ", subEstatus=" + subEstatus
				+ ", subEstatusDescripcion=" + subEstatusDescripcion + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + ", entidad=" + entidad + ", cuenta=" + cuenta
				+ ", reporteProcesosNocturnos=" + reporteProcesosNocturnos + ", reporteProveedorTransaccional="
				+ reporteProveedorTransaccional + ", reporteEstadoCuenta=" + reporteEstadoCuenta + ", global=" + global
				+ ", devoluciones=" + devoluciones + ", movimientosTransito=" + movimientosTransito + ", comisiones="
				+ comisiones + "]";
	}

	@Override
	public int compareTo(ConciliacionDTO o) {
		return 0;
	}

}