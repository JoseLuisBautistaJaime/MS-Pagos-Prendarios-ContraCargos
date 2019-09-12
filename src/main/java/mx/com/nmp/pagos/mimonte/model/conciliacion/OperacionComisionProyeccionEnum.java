/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name OperacionComisionProyeccionEnum
 * @description Enum que almacena las operaciones posibles para las proyecciones
 *              de las comisiones.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/05/2019 15:53 hrs.
 * @version 0.1
 */
public enum OperacionComisionProyeccionEnum {

	PAGOS(1, "Pagos"), DEVOLUCIONES(2, "Devoluciones");

	private int id;
	private String descripcion;

	private OperacionComisionProyeccionEnum(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Regresa un valor del Enum por id
	 * 
	 * @param id
	 * @return
	 */
	public static OperacionComisionProyeccionEnum getById(int id) {
		for (OperacionComisionProyeccionEnum e : values()) {
			if (e.id == id)
				return e;
		}
		return null;
	}
}
