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
	
	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 679398017169972235L;
	
	private Long Folio;
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
	private List<DevolucionConDTO> devoluciones;
	private List<MovTransitoDTO> movimientosTransito;
	private List<ComisionesDTO> comisiones;
	private ComisionesTransDTO comisionesTransacciones;

	public ConciliacionDTOList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConciliacionDTOList(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
		// TODO Auto-generated constructor stub
	}

	public ConciliacionDTOList(Long folio, EstatusConciliacionDTO estatus, SubEstatusConciliacionDTO subEstatus,
			String subEstatusDescripcion, Long idPolizaTesoreria, Long idAsientoContable, EntidadDTO entidad,
			CuentaDTO cuenta, ReporteProcesosNocturnosDTO reporteProcesosNocturnos,
			ReporteProveedorTransaccionalDTO reporteProveedorTransaccional, ReporteEstadoCuentaDTO reporteEstadoCuenta,
			GlobalDTO global, List<DevolucionConDTO> devoluciones, List<MovTransitoDTO> movimientosTransito,
			List<ComisionesDTO> comisiones, ComisionesTransDTO comisionesTransacciones, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super();
		Folio = folio;
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
		this.comisionesTransacciones = comisionesTransacciones;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	public Long getFolio() {
		return Folio;
	}

	public void setFolio(Long folio) {
		Folio = folio;
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

	public ComisionesTransDTO getComisionesTransacciones() {
		return comisionesTransacciones;
	}

	public void setComisionesTransacciones(ComisionesTransDTO comisionesTransacciones) {
		this.comisionesTransacciones = comisionesTransacciones;
	}

	@Override
	public String toString() {
		return "ConciliacionDTOList [Folio=" + Folio + ", estatus=" + estatus + ", subEstatus=" + subEstatus
				+ ", subEstatusDescripcion=" + subEstatusDescripcion + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + ", entidad=" + entidad + ", cuenta=" + cuenta
				+ ", reporteProcesosNocturnos=" + reporteProcesosNocturnos + ", reporteProveedorTransaccional="
				+ reporteProveedorTransaccional + ", reporteEstadoCuenta=" + reporteEstadoCuenta + ", global=" + global
				+ ", devoluciones=" + devoluciones + ", movimientosTransito=" + movimientosTransito + ", comisiones="
				+ comisiones + ", comisionesTransacciones=" + comisionesTransacciones + "]";
	}

	@Override
	public int compareTo(ConciliacionDTOList arg0) {
		return 0;
	}

}
