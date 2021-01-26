/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @name MovimientoBonificacion
 * 
 * @description encapsula informacion de una referencia asociada a un movimiento bonificacion 
 * @author Quarksoft
 * @version 1.0
 * @created 11/11/2020
 */
@Entity
@Table(name = "to_movimiento_bonificacion_referencia")
public class MovimientoBonificacionReferencia implements Serializable {

	private static final long serialVersionUID = 8987562812338981275L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "sucursal")
	private Long sucursal;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "referencia")
	private String referencia;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_movimiento_bonificacion")
	private MovimientoBonificacion movimientoBonificacion;


	public MovimientoBonificacionReferencia(Long id, Long sucursal, BigDecimal monto, String referencia,
			MovimientoBonificacion movimientoBonificacion) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.monto = monto;
		this.referencia = referencia;
		this.movimientoBonificacion = movimientoBonificacion;
	}

	public MovimientoBonificacionReferencia() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public MovimientoBonificacion getMovimientoBonificacion() {
		return movimientoBonificacion;
	}

	public void setMovimientoBonificacion(MovimientoBonificacion movimientoBonificacion) {
		this.movimientoBonificacion = movimientoBonificacion;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, referencia, monto, sucursal, movimientoBonificacion != null ? movimientoBonificacion.getId() : 0);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Conciliacion))
			return false;

		final Conciliacion other = (Conciliacion) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "[id=" + id + ", sucursal=" + sucursal + ", monto=" + monto
				+ ", referencia=" + referencia + ", movimientoBonificacion=" + movimientoBonificacion + "]";
	}

}