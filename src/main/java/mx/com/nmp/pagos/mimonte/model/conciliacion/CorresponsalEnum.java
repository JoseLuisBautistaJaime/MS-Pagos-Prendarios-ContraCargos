/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * Enum de Proveedor o Corresponsal de quien se realiza la conciliacion.
 * 
 * @author Ismael Flores Aguilar
 * @version 1.0
 * @created 19-Oct-2020
 */
public enum CorresponsalEnum {

	OPENPAY("OPENPAY", "Proveedor transaccional"), OXXO("OXXO", "Proveedor Oxxo");

	private String nombre;
	private String descripcion;

	private CorresponsalEnum(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static CorresponsalEnum getByNombre(String nombre) {
		for (CorresponsalEnum corresponsalEnum : CorresponsalEnum.values()) {
			if (corresponsalEnum.getNombre().equals(nombre)) {
				return corresponsalEnum;
			}
		}
		return null;
	}

}
