/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

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

	private Long folio;
	private EstatusConciliacionDTO estatus;
	private SubEstatusConciliacionDTO subEstatus;
	private String subEstatusDescripcion;
	private Long idPolizaTesoreria;
	private Long idAsientoContable;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private ReporteProcesosNocturnosDTO reporteProcesosNocturnos;
	private ReporteProveedorTransaccionalDTO reporteProveedorTransaccional;
	private ReporteEstadoCuentaDTO reporteEstadoCuenta;
	private GlobalDTO global;
	private DevolucionConDTO devoluciones;
	private MovTransitoDTO movimientosTransito;
	private ComisionesDTO comisiones;
	private ComisionesTransProyeccionDTO comisionesTransacciones;
	private CorresponsalEnum corresponsal;

	public ConciliacionDTO() {
		super();
	}

	public ConciliacionDTO(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public ConciliacionDTO(Long folio, EstatusConciliacionDTO estatus, SubEstatusConciliacionDTO subEstatus,
			String subEstatusDescripcion, Long idPolizaTesoreria, Long idAsientoContable, EntidadDTO entidad,
			CuentaDTO cuenta, ReporteProcesosNocturnosDTO reporteProcesosNocturnos,
			ReporteProveedorTransaccionalDTO reporteProveedorTransaccional, ReporteEstadoCuentaDTO reporteEstadoCuenta,
			GlobalDTO global, DevolucionConDTO devoluciones, MovTransitoDTO movimientosTransito,
			ComisionesDTO comisiones) {
		super();
		this.folio = folio;
		this.estatus = estatus;
		this.subEstatus = subEstatus;
		this.subEstatusDescripcion = subEstatusDescripcion;
		this.idPolizaTesoreria = idPolizaTesoreria;
		this.idAsientoContable = idAsientoContable;
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

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}
	
	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public EstatusConciliacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusConciliacionDTO estatus) {
		this.estatus = estatus;
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

	public Long getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(Long idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public Long getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(Long idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
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

	public ComisionesTransProyeccionDTO getComisionesTransacciones() {
		return comisionesTransacciones;
	}

	public void setComisionesTransacciones(ComisionesTransProyeccionDTO comisionesTransacciones) {
		this.comisionesTransacciones = comisionesTransacciones;
	}

}