/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @name ComisionTransaccionReal
 * @description Clase de entidad persistente que encapsula entidades de tipo
 *              ComisionTransaccionReal
 *
 * @author Quarksoft
 * @creationDate 28-May-2019 8:17:09 PM
 * @version 0.1
 */
@Entity
@Table(name = "to_comision_transaccion_real")
public class ComisionTransaccionReal implements java.io.Serializable, Comparable<ComisionTransaccionReal> {

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

	@Column(name = "comision", nullable = false)
	private BigDecimal comision;

	@Column(name = "iva_comision", nullable = true)
	private BigDecimal ivaComision;

	@Column(name = "total", nullable = true)
	private BigDecimal total;

	public ComisionTransaccionReal() {
		super();
	}

	public ComisionTransaccionReal(Long id, ComisionTransaccion comisionTransaccion, BigDecimal comision,
			BigDecimal ivaComision, BigDecimal total) {
		super();
		this.id = id;
		this.comisionTransaccion = comisionTransaccion;
		this.comision = comision;
		this.ivaComision = ivaComision;
		this.total = total;
	}

	public ComisionTransaccionReal(Long id, BigDecimal comision, BigDecimal ivaComision, BigDecimal total) {
		super();
		this.id = id;
		this.comision = comision;
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

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
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

	@Override
	public int compareTo(ComisionTransaccionReal o) {
		return o.id.compareTo(this.id);
	}

	@Override
	public String toString() {
		return "ComisionTransaccionReal [id=" + id + ", comisionTransaccion=" + comisionTransaccion + ", comision="
				+ comision + ", ivaComision=" + ivaComision + ", total=" + total + "]";
	}

}