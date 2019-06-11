/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Reporte extends Updatable implements Comparable<Reporte> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "id_conciliacion",  insertable = false, updatable = false)
	private Conciliacion conciliacion;

	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoReporteEnum tipo;

	@Column(name = "disponible", nullable = false)
	private Boolean disponible;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desde", nullable = false)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_hasta", nullable = false)
	private Date fechaHasta;
	
	@OneToMany(mappedBy = "reporte", targetEntity = MovimientoMidas.class)
	private Set<MovimientoMidas> movimientosMidas;

	@OneToMany(mappedBy = "reporte", targetEntity = MovimientoProveedor.class)
	private Set<MovimientoProveedor> movimientosProveedor;

	public Reporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reporte(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
		// TODO Auto-generated constructor stub
	}

	public Reporte(Integer id, Conciliacion conciliacion,
			@Size(max = 45, message = "Debe ingresar maximo 45 caracteres") TipoReporteEnum tipo, Boolean disponible,
			Date fechaDesde, Date fechaHasta, Set<MovimientoMidas> movimientosMidas,
			Set<MovimientoProveedor> movimientosProveedor) {
		super();
		this.id = id;
		this.conciliacion = conciliacion;
		this.tipo = tipo;
		this.disponible = disponible;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.movimientosMidas = movimientosMidas;
		this.movimientosProveedor = movimientosProveedor;
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

	public Set<MovimientoMidas> getMovimientosMidas() {
		return movimientosMidas;
	}

	public void setMovimientosMidas(Set<MovimientoMidas> movimientosMidas) {
		this.movimientosMidas = movimientosMidas;
	}

	public Set<MovimientoProveedor> getMovimientosProveedor() {
		return movimientosProveedor;
	}

	public void setMovimientosProveedor(Set<MovimientoProveedor> movimientosProveedor) {
		this.movimientosProveedor = movimientosProveedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, conciliacion, tipo, disponible, fechaDesde, fechaHasta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		if (movimientosMidas == null) {
			if (other.movimientosMidas != null)
				return false;
		} else if (!movimientosMidas.equals(other.movimientosMidas))
			return false;
		if (movimientosProveedor == null) {
			if (other.movimientosProveedor != null)
				return false;
		} else if (!movimientosProveedor.equals(other.movimientosProveedor))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reporte [id=" + id + ", conciliacion=" + conciliacion + ", tipo=" + tipo + ", disponible=" + disponible
				+ ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + "]";
	}
	
	@Override
	public int compareTo(Reporte arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}