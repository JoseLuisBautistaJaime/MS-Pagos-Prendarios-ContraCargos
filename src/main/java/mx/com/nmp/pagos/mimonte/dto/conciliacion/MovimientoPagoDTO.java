/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Objects;

import mx.com.nmp.pagos.mimonte.model.EstatusPago;

/**
 * @name EstadoCuenta
 * 
 * @description encapsula informacion de un estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:41 PM
 */
public class MovimientoPagoDTO implements Comparable<MovimientoPagoDTO> {

	private long id;
	private EstatusPago estatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		return "MovimientoPagoDTO [id=" + id + ", estatus=" + estatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, estatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoPagoDTO))
			return false;

		final MovimientoPagoDTO other = (MovimientoPagoDTO) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public int compareTo(MovimientoPagoDTO o) {
		return o.id == this.id ? 0 : -1;
	}

}
