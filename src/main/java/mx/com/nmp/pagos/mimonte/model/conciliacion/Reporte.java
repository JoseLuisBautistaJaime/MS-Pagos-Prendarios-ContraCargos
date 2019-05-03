/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

//	@OneToMany(mappedBy = "reporte", targetEntity = MovimientoMidas.class)
//	private Set<MovimientoMidas> movimientosMidas;
//
//	@OneToMany(mappedBy = "reporte", targetEntity = MovimientoProveedor.class)
//	private Set<MovimientoProveedor> movimientosProveedor;

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

//	public Set<MovimientoMidas> getMovimientosMidas() {
//		return movimientosMidas;
//	}

//	public void setMovimientosMidas(Set<MovimientoMidas> movimientosMidas) {
//		this.movimientosMidas = movimientosMidas;
//	}

//	public Set<MovimientoProveedor> getMovimientosProveedor() {
//		return movimientosProveedor;
//	}

//	public void setMovimientosProveedor(Set<MovimientoProveedor> movimientosProveedor) {
//		this.movimientosProveedor = movimientosProveedor;
//	}

	public void setId(Long id) {
		this.id = id;
	}

//	@Override
//	public String toString() {
//		return "Reporte [id=" + id + ", conciliacion=" + conciliacion + ", tipo=" + tipo + ", disponible=" + disponible
//				+ ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", movimientosMidas=" + movimientosMidas
//				+ ", movimientosProveedor=" + movimientosProveedor + "]";
//	}

	@Override
	public String toString() {
		return "Reporte [id=" + id + ", conciliacion=" + conciliacion + ", tipo=" + tipo + ", disponible=" + disponible
				+ ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + "]";
	}

	@Override
	public int compareTo(Reporte o) {
		return o.id.compareTo(this.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conciliacion == null) ? 0 : conciliacion.hashCode());
		result = prime * result + ((disponible == null) ? 0 : disponible.hashCode());
		result = prime * result + ((fechaDesde == null) ? 0 : fechaDesde.hashCode());
		result = prime * result + ((fechaHasta == null) ? 0 : fechaHasta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((movimientosMidas == null) ? 0 : movimientosMidas.hashCode());
//		result = prime * result + ((movimientosProveedor == null) ? 0 : movimientosProveedor.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reporte other = (Reporte) obj;
		if (conciliacion == null) {
			if (other.conciliacion != null)
				return false;
		} else if (!conciliacion.equals(other.conciliacion))
			return false;
		if (disponible == null) {
			if (other.disponible != null)
				return false;
		} else if (!disponible.equals(other.disponible))
			return false;
		if (fechaDesde == null) {
			if (other.fechaDesde != null)
				return false;
		} else if (!fechaDesde.equals(other.fechaDesde))
			return false;
		if (fechaHasta == null) {
			if (other.fechaHasta != null)
				return false;
		} else if (!fechaHasta.equals(other.fechaHasta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (movimientosMidas == null) {
//			if (other.movimientosMidas != null)
//				return false;
//		} else if (!movimientosMidas.equals(other.movimientosMidas))
//			return false;
//		if (movimientosProveedor == null) {
//			if (other.movimientosProveedor != null)
//				return false;
//		} else if (!movimientosProveedor.equals(other.movimientosProveedor))
//			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}