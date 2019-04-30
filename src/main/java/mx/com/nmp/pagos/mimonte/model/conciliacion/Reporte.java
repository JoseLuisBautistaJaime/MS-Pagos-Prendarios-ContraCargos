/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
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
public class Reporte extends Updatable implements Comparable<Reporte> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_conciliacion", nullable = false)
	private Conciliacion conciliacion;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "tipo", nullable = false)
	private String tipo;

	@Column(name = "disponible", nullable = false)
	private Boolean disponible;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desde", nullable = false)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_hasta", nullable = false)
	private Date fechaHasta;

	@OneToMany(mappedBy = "reporte", targetEntity = MovimientoMidas.class)
	private Set<MovimientoMidas> movimientos;

	public Reporte() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Conciliacion getConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Conciliacion conciliacion) {
		this.conciliacion = conciliacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
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

	public Set<MovimientoMidas> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<MovimientoMidas> movimientos) {
		this.movimientos = movimientos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Reporte [id=" + id + ", idConciliacion=" + conciliacion + ", tipo=" + tipo + ", disponible="
				+ disponible + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", movimientos="
				+ movimientos + "]";
	}

	@Override
	public int compareTo(Reporte o) {
		return o.id.compareTo(this.id);
	}

}