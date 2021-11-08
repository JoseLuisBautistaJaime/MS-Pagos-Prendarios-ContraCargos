/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 03-Nov-2021 11:33:55 AM
 */
public class ConsultaEjecucionConciliacionDTO implements Comparable<ConsultaEjecucionConciliacionDTO> {

	private Long id;
	private EstatusEjecucionConciliacionDTO estatus;
	private String estatusDescripcion;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private ConciliacionDTO conciliacion;
	private Date fechaEjecucion;
	private Date fechaPeriodoInicio;
	private Date fechaPeriodoFin;
	private String createdBy;
	private Date createdDate;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private CorresponsalEnum corresponsal;

	public ConsultaEjecucionConciliacionDTO() {
		super();
	}

	public ConsultaEjecucionConciliacionDTO(Long id, EstatusEjecucionConciliacionDTO estatus, String estatusDescripcion, EntidadDTO entidad, CuentaDTO cuenta, ConciliacionDTO conciliacion, Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.estatusDescripcion = estatusDescripcion;
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.conciliacion = conciliacion;
		this.fechaEjecucion = fechaEjecucion;
		this.fechaPeriodoInicio = fechaPeriodoInicio;
		this.fechaPeriodoFin = fechaPeriodoFin;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.corresponsal = corresponsal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusEjecucionConciliacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusEjecucionConciliacionDTO estatus) {
		this.estatus = estatus;
	}

	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}

	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
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

	public ConciliacionDTO getConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(ConciliacionDTO conciliacion) {
		this.conciliacion = conciliacion;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public Date getFechaPeriodoInicio() {
		return fechaPeriodoInicio;
	}

	public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
		this.fechaPeriodoInicio = fechaPeriodoInicio;
	}

	public Date getFechaPeriodoFin() {
		return fechaPeriodoFin;
	}

	public void setFechaPeriodoFin(Date fechaPeriodoFin) {
		this.fechaPeriodoFin = fechaPeriodoFin;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ConsultaEjecucionConciliacionDTO [id=" + id + ", estatus=" + estatus +", estatusDescripcion=" + estatusDescripcion + ", conciliacion=" + conciliacion
				+ ", fechaEjecucion=" + fechaEjecucion + ", fechaPeriodoInicio=" + fechaPeriodoInicio + ", fechaPeriodoFin=" + fechaPeriodoFin
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", entidad=" + entidad + ", cuenta=" + cuenta+ ", corresponsal=" + corresponsal + "]";
	}

	@Override
	public int compareTo(ConsultaEjecucionConciliacionDTO o) {
		return 0;
	}


}