/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class MovimientoPago extends MovimientoConciliacion implements Comparable<MovimientoPago> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "estatus", nullable = false)
	private EstatusPago estatus;

	@Column(name = "monto")
	private BigDecimal monto;


	public MovimientoPago() {
		super();
	}

	public MovimientoPago(EstatusPago estatus) {
		super();
		this.estatus = estatus;
	}

	public MovimientoPago(EstatusPago estatus, BigDecimal monto) {
		super();
		this.estatus = estatus;
		this.monto = monto;
	}


	public EstatusPago getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusPago estatus) {
		this.estatus = estatus;
	}
	
	
	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Transient
	public MovimientoConciliacionEnum getTipoMovimiento() {
		return MovimientoConciliacionEnum.PAGOS;
	}



	@Override
	public int hashCode() {
		return Objects.hash(estatus, monto);
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
		return 0;
	}

	@Override
	public String toString() {
		return "MovimientoPago [estatus=" + estatus + ", monto=" + monto + "]";
	}

}