/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;

/**
 * @name EstatusPago
 * 
 * @description encapsula informacion de un estatus de pago
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @created 21/11/2018 16:41 AM
 */
@Table(name = "tk_estatus_pago")
@Entity
public class EstatusPagos extends AbstractCatalogo {

	public EstatusPagos() {
		super();
	}

	public EstatusPagos(Integer id) {
		super(id);
	}

	public EstatusPagos(Integer id, String descripcionCorta, String descripcion) {
		super(id, descripcionCorta, descripcion);
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "estatusPago")
	private List<Pago> pagos;

	@OneToMany(mappedBy = "estatus")
	private Set<MovimientoPago> movimientoPagoSet;

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Set<MovimientoPago> getMovimientoPagoSet() {
		return movimientoPagoSet;
	}

	public void setMovimientoPagoSet(Set<MovimientoPago> movimientoPagoSet) {
		this.movimientoPagoSet = movimientoPagoSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descripcionCorta, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EstatusPagos))
			return false;

		final EstatusPagos other = (EstatusPagos) obj;
		return (this.hashCode() == other.hashCode());

	}

}
