/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.EstatusPago;

/**
 * @name MovimientoPago
 * 
 * @description encapsula informacion de un estado de un movimiento de pago
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:50 PM
 */
@Entity
@Table(name = "to_movimiento_pago")
public class MovimientoPago implements Comparable<MovimientoPago> {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "estatus", nullable = false)
	private EstatusPago estatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusPago getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusPago estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "MovimientoPago [id=" + id + ", estatus=" + estatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, estatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoPago))
			return false;

		final MovimientoPago other = (MovimientoPago) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(MovimientoPago arg0) {
		return arg0.id == this.id ? 0 : -1;
	}

}