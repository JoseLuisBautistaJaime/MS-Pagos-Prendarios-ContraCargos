/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

import java.util.Date;
import java.util.Objects;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 05-Nov-2021 09:13:55 AM
 */
public class EjecucionPreconciliacionDTO implements Comparable<EjecucionPreconciliacionDTO> {

	private Long id;
	private EstatusEjecucionPreconciliacionDTO estatus;
	private String estatusDescripcion;
	private Date fechaEjecucion;
	private Date fechaPeriodoInicio;
	private Date fechaPeriodoFin;
	private String createdBy;
	private Date createdDate;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private CorresponsalEnum corresponsal;

	public EjecucionPreconciliacionDTO() {
		super();
	}

	public EjecucionPreconciliacionDTO(Long id, EstatusEjecucionPreconciliacionDTO estatus, String estatusDescripcion, Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.estatusDescripcion = estatusDescripcion;
		this.fechaEjecucion = fechaEjecucion;
		this.fechaPeriodoInicio = fechaPeriodoInicio;
		this.fechaPeriodoFin = fechaPeriodoFin;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.corresponsal = corresponsal;
	}

	public EjecucionPreconciliacionDTO(Long id, Integer idEstatus, String descripcionCortaEstatus, String descripcionEstatus, Integer orderNumberEstatus,
											   Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.estatus = new EstatusEjecucionPreconciliacionDTO(idEstatus, descripcionCortaEstatus, descripcionEstatus, orderNumberEstatus);
		this.estatusDescripcion = descripcionEstatus;
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

	public EstatusEjecucionPreconciliacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusEjecucionPreconciliacionDTO estatus) {
		this.estatus = estatus;
	}

	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}

	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
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
		return "EjecucionPreconciliacionDTO [id=" + id + ", estatus=" + estatus +", estatusDescripcion=" + estatusDescripcion + ", fechaEjecucion=" + fechaEjecucion
				+ ", fechaPeriodoInicio=" + fechaPeriodoInicio + ", fechaPeriodoFin=" + fechaPeriodoFin + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", corresponsal=" + corresponsal + "]";
	}

	@Override
	public int compareTo(EjecucionPreconciliacionDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, estatus, estatusDescripcion, fechaEjecucion, fechaPeriodoInicio, fechaPeriodoFin, createdBy, createdDate, lastModifiedDate, lastModifiedBy, corresponsal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EjecucionPreconciliacionDTO)) {
			return false;
		}
		final EjecucionPreconciliacionDTO other = (EjecucionPreconciliacionDTO) obj;
		return (this.hashCode() == other.hashCode());

	}


}