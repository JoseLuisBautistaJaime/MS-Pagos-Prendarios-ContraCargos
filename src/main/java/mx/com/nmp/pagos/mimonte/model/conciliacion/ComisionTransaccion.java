/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @name ComisionTransaccion
 * @description Clase de entidad persistente que encapsula entidades de tipo
 *              ComisionTransaccion
 *
 * @author Quarksoft
 * @creationDate 28-May-2019 8:17:10 PM
 * @version 0.1
 */
@Entity
@Table(name = "to_comision_transaccion")
public class ComisionTransaccion extends Updatable implements java.io.Serializable, Comparable<ComisionTransaccion> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_conciliacion", nullable = true)
	private Conciliacion conciliacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desde", nullable = false)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_hasta", nullable = false)
	private Date fechaHasta;

	@Column(name = "comision", nullable = false)
	private BigDecimal comision;

	@OneToMany(mappedBy = "comisionTransaccion")
	private List<ComisionTransaccionProyeccion> comisionTransaccionProyeccionSet;

	@OneToMany(mappedBy = "comisionTransaccion")
	private Set<ComisionTransaccionReal> comisionTransaccionRealSet;

	public ComisionTransaccion() {
		super();
	}
	
	public ComisionTransaccion(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conciliacion getConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Conciliacion conciliacion) {
		this.conciliacion = conciliacion;
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

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public List<ComisionTransaccionProyeccion> getComisionTransaccionProyeccionSet() {
		return comisionTransaccionProyeccionSet;
	}

	public void setComisionTransaccionProyeccionSet(
			List<ComisionTransaccionProyeccion> comisionTransaccionProyeccionSet) {
		this.comisionTransaccionProyeccionSet = comisionTransaccionProyeccionSet;
	}

	public Set<ComisionTransaccionReal> getComisionTransaccionRealSet() {
		return comisionTransaccionRealSet;
	}

	public void setComisionTransaccionRealSet(Set<ComisionTransaccionReal> comisionTransaccionRealSet) {
		this.comisionTransaccionRealSet = comisionTransaccionRealSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, conciliacion != null ? conciliacion.getId() : 0, fechaDesde, fechaHasta, comision);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ComisionTransaccion))
			return false;

		final ComisionTransaccion other = (ComisionTransaccion) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "ComisionTransaccion [id=" + id + ", conciliacion=" + (conciliacion != null ? conciliacion.getId() : 0) + ", fechaDesde=" + fechaDesde
				+ ", fechaHasta=" + fechaHasta + ", comision=" + comision + "]";
	}

	@Override
	public int compareTo(ComisionTransaccion o) {
		return o.id.compareTo(this.id);
	}

}