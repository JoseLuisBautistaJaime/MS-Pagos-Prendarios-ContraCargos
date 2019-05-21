/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name MetodoPagoMovimientosProveedorDTO
 * @description Clase que encapsula la informacion relacionada con un objeto
 *              metodo de pago embebido en un movimiento de proveedor
 *              transaccional
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/05/2019 13:41 hrs.
 * @version 0.1
 */
public class MetodoPagoMovimientosProveedorDTO {

	private String type;
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
	public String toString() {
		return "MetodoPagoMovimientosProveedorDTO [type=" + type + ", url=" + url + "]";
	}

}
