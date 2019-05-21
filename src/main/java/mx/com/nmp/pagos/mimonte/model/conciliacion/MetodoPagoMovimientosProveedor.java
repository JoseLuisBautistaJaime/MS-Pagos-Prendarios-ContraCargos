/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @name MetodoPagoMovimientosProveedor
 * @description Clase que encapsula la informacion relacionada con un objeto
 *              metodo de pago embebido en un movimiento de proveedor
 *              transaccional
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/05/2019 12203 hrs.
 * @version 0.1
 */
@Embeddable
public class MetodoPagoMovimientosProveedor {

	@Column(name = "payment_method_type")
	private String type;

	@Column(name = "payment_method_url")
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MetodoPagoMovimientosProveedor))
			return false;

		final MetodoPagoMovimientosProveedor other = (MetodoPagoMovimientosProveedor) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "MetodoPagoMovimientosProveedor [type=" + type + ", url=" + url + "]";
	}

}
