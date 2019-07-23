/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @name Reporte
 * @description Encapsula la informacion perteneciente a un reporte
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:54 PM
 */
@Entity
@Table(name = "to_reporte")
public class Reporte extends Updatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_conciliacion")
	private Conciliacion conciliacion;

	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoReporteEnum tipo;

	@Column(name = "disponible", nullable = false)
	private boolean disponible;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desde", nullable = false)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_hasta", nullable = false)
	private Date fechaHasta;


	public Reporte() {
		super();
	}

	public Reporte(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public Reporte(Integer id, Conciliacion conciliacion,
			@Size(max = 45, message = "Debe ingresar maximo 45 caracteres") TipoReporteEnum tipo, Boolean disponible,
			Date fechaDesde, Date fechaHasta) {
		super();
		this.id = id;
		this.conciliacion = conciliacion;
		this.tipo = tipo;
		this.disponible = disponible;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conciliacion getConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Conciliacion conciliacion) {
		this.conciliacion = conciliacion;
	}

	public TipoReporteEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoReporteEnum tipo) {
		this.tipo = tipo;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	@Transient
	public boolean isMergeUpdated() {
		Date lastUpdated = this.lastModifiedDate != null ? this.lastModifiedDate : this.createdDate;
		Date lastMergeDate = null;
		if (this.tipo != null && this.conciliacion != null && this.conciliacion.getMerge() != null) {
			switch (this.tipo) {
				case MIDAS:
					lastMergeDate = this.conciliacion.getMerge().getMidasLastUpdated();
					break;
				case ESTADO_CUENTA:
					lastMergeDate = this.conciliacion.getMerge().getEstadoCuentaLastUpdated();
					break;
				case PROVEEDOR:
					lastMergeDate = this.conciliacion.getMerge().getProveedorLastUpdated();
					break;
			}
		}
		return lastUpdated != null && lastMergeDate != null ? lastMergeDate.compareTo(lastUpdated) >= 0 : false;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, (conciliacion != null ? conciliacion.getId() : 0), tipo, disponible, fechaDesde, fechaHasta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Reporte))
			return false;

		final Reporte other = (Reporte) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "Reporte [id=" + id + ", conciliacion=" + (conciliacion != null ? conciliacion.getId() : 0) + ", tipo=" + tipo + ", disponible=" + disponible
				+ ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + "]";
	}

}