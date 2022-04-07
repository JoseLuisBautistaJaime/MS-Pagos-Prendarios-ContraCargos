/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 03-Nov-2021 11:33:55 AM
 */
public class EjecucionConciliacionDTO implements Comparable<EjecucionConciliacionDTO> {

	private Long id;
	private EstatusEjecucionConciliacionDTO estatus;
	private String estatusDescripcion;
	private ConciliacionEjecucionDTO conciliacion;
	private Date fechaEjecucion;
	private Date fechaPeriodoInicio;
	private Date fechaPeriodoFin;
	private String createdBy;
	private Date createdDate;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private CorresponsalEnum corresponsal;
	List<TrazadoEjecucionConciliacionDTO> listaTrazado;

	public EjecucionConciliacionDTO() {
		super();
	}

	public EjecucionConciliacionDTO(Long id, EstatusEjecucionConciliacionDTO estatus, String estatusDescripcion, ConciliacionEjecucionDTO conciliacion, Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.estatusDescripcion = estatusDescripcion;
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

	public EjecucionConciliacionDTO(Long id, Integer idEstatus, String descripcionCortaEstatus, String descripcionEstatus, Integer orderNumberEstatus,
											Long folioConciliacion, Long idConciliacion, Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.estatus = new EstatusEjecucionConciliacionDTO(idEstatus, descripcionCortaEstatus, descripcionEstatus, orderNumberEstatus);
		this.estatusDescripcion = descripcionEstatus;
		this.conciliacion = new ConciliacionEjecucionDTO(folioConciliacion, idConciliacion);
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

	public EstatusEjecucionConciliacionDTO getEstatus() {
		return estatus;
	}

	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}

	public ConciliacionEjecucionDTO getConciliacion() {
		return conciliacion;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public Date getFechaPeriodoInicio() {
		return fechaPeriodoInicio;
	}

	public Date getFechaPeriodoFin() {
		return fechaPeriodoFin;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public List<TrazadoEjecucionConciliacionDTO> getListaTrazado() {
		return listaTrazado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEstatus(EstatusEjecucionConciliacionDTO estatus) {
		this.estatus = estatus;
	}

	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
	}

	public void setConciliacion(ConciliacionEjecucionDTO conciliacion) {
		this.conciliacion = conciliacion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
		this.fechaPeriodoInicio = fechaPeriodoInicio;
	}

	public void setFechaPeriodoFin(Date fechaPeriodoFin) {
		this.fechaPeriodoFin = fechaPeriodoFin;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	public void setListaTrazado(List<TrazadoEjecucionConciliacionDTO> listaTrazado) {
		this.listaTrazado = listaTrazado;
	}

	@Override
	public String toString() {
		return "EjecucionConciliacionDTO [id=" + id + ", estatus=" + estatus +", estatusDescripcion=" + estatusDescripcion + ", conciliacion=" + conciliacion
				+ ", fechaEjecucion=" + fechaEjecucion + ", fechaPeriodoInicio=" + fechaPeriodoInicio + ", fechaPeriodoFin=" + fechaPeriodoFin
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", corresponsal=" + corresponsal + "]";
	}

	@Override
	public int compareTo(EjecucionConciliacionDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, estatus, estatusDescripcion, conciliacion, fechaEjecucion, fechaPeriodoInicio, fechaPeriodoFin, createdBy, createdDate, lastModifiedDate, lastModifiedBy, corresponsal, listaTrazado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EjecucionConciliacionDTO)) {
			return false;
		}
		final EjecucionConciliacionDTO other = (EjecucionConciliacionDTO) obj;
		return (this.hashCode() == other.hashCode());
	}

}