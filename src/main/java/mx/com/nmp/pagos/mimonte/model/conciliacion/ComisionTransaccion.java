/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ComisionTransaccion extends Updatable implements java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "id_conciliacion", nullable = true)
	private Integer idConciliacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desde", nullable = false)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_hasta", nullable = false)
	private Date fechaHasta;

	@Column(name = "comision", nullable = false)
	private BigDecimal comision;

	@OneToMany(mappedBy = "comisionTransaccion", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Set<ComisionTransaccionProyeccion> comisionTransaccionProyeccionSet;

	@OneToMany(mappedBy = "comisionTransaccion", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Set<ComisionTransaccionReal> comisionTransaccionRealSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Integer idConciliacion) {
		this.idConciliacion = idConciliacion;
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

	public Set<ComisionTransaccionProyeccion> getComisionTransaccionProyeccionSet() {
		return comisionTransaccionProyeccionSet;
	}

	public void setComisionTransaccionProyeccionSet(
			Set<ComisionTransaccionProyeccion> comisionTransaccionProyeccionSet) {
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
		return Objects.hash(id, idConciliacion, fechaDesde, fechaHasta, comision);
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
		return "ComisionTransaccion [id=" + id + ", idConciliacion=" + idConciliacion + ", fechaDesde=" + fechaDesde
				+ ", fechaHasta=" + fechaHasta + ", comision=" + comision + "]";
	}

}