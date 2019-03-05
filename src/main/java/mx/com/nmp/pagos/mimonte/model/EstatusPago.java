package mx.com.nmp.pagos.mimonte.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: EstatusPago Descripcion: Entidad que representa un estatus de pago en
 * el sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 21/11/2018 16:41 AM
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_transaccion")
public class EstatusPago extends AbstractCatalogo {

	public EstatusPago() {
		super();
	}

	public EstatusPago(Integer id, String descripcionCorta, String descripcion) {
		super(id, descripcionCorta, descripcion);
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "estatusPago")
	private List<Pago> pagos;

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

}
