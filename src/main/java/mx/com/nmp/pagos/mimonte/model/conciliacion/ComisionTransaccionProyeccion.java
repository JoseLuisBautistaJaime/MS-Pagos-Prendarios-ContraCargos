/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @name ComisionTransaccionProyeccion
 * @description Clase de entidad persistente que encapsula entidades de tipo
 *              ComisionTransaccionProyecion
 *
 * @author Quarksoft
 * @creationDate 28-May-2019 8:17:07 PM
 * @version 0.1
 */
@Entity
@Table(name = "to_comision_transaccion_proyeccion")
public class ComisionTransaccionProyeccion implements java.io.Serializable, Comparable<ComisionTransaccionProyeccion> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "comision_transaccion", nullable = false)
	private ComisionTransaccion comisionTransaccion;

	@Column(name = "operacion", nullable = false)
	private Integer operacion;

	@Column(name = "transacciones", nullable = false)
	private Long transacciones;

	@Column(name = "comision", nullable = true)
	private BigDecimal comsion;

	@Column(name = "iva", nullable = true)
	private BigDecimal ivaComision;

	@Column(name = "total", nullable = true)
	private BigDecimal total;

	public ComisionTransaccionProyeccion() {
		super();
	}

	public ComisionTransaccionProyeccion(Long id, ComisionTransaccion comisionTransaccion, Integer operacion,
			Long transacciones, BigDecimal comsion, BigDecimal ivaComision, BigDecimal total) {
		super();
		this.id = id;
		this.comisionTransaccion = comisionTransaccion;
		this.operacion = operacion;
		this.transacciones = transacciones;
		this.comsion = comsion;
		this.ivaComision = ivaComision;
		this.total = total;
	}

	public ComisionTransaccionProyeccion(Long id, Integer operacion, Long transacciones, BigDecimal comsion,
			BigDecimal ivaComision, BigDecimal total) {
		super();
		this.id = id;
		this.operacion = operacion;
		this.transacciones = transacciones;
		this.comsion = comsion;
		this.ivaComision = ivaComision;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ComisionTransaccion getComisionTransaccion() {
		return comisionTransaccion;
	}

	public void setComisionTransaccion(ComisionTransaccion comisionTransaccion) {
		this.comisionTransaccion = comisionTransaccion;
	}

	public Integer getOperacion() {
		return operacion;
	}

	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getComsion() {
		return comsion;
	}

	public void setComsion(BigDecimal comsion) {
		this.comsion = comsion;
	}

	public BigDecimal getIvaComision() {
		return ivaComision;
	}

	public void setIvaComision(BigDecimal ivaComision) {
		this.ivaComision = ivaComision;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Long getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Long transacciones) {
		this.transacciones = transacciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, comisionTransaccion, operacion, transacciones, comsion, ivaComision, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ComisionTransaccionProyeccion))
			return false;

		final ComisionTransaccionProyeccion other = (ComisionTransaccionProyeccion) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(ComisionTransaccionProyeccion o) {
		return o.id.compareTo(this.id);
	}

	@Override
	public String toString() {
		return "ComisionTransaccionProyeccion [id=" + id + ", comisionTransaccion=" + comisionTransaccion
				+ ", operacion=" + operacion + ", transacciones=" + transacciones + ", comsion=" + comsion
				+ ", ivaComision=" + ivaComision + ", total=" + total + "]";
	}

}