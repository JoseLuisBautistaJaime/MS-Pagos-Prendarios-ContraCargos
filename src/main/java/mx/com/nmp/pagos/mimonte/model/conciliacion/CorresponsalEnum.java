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

	OPEN_PAY(1L, "OPEN_PAY", "Proveedor transaccional"), OXXO(2L, "OXXO", "Proveedor Oxxo");

	private Long id;
	private String nombre;
	private String descripcion;

	private CorresponsalEnum(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static CorresponsalEnum getById(Long id) {
		for(CorresponsalEnum corresponsalEnum : CorresponsalEnum.values() ){
			if(corresponsalEnum.getId().equals(id)) {
				return corresponsalEnum;
			}
		}
		return null;
	}
	
}
