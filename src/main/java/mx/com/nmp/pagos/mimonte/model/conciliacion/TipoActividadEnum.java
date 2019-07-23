/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name TipoActividadEnum
 * @description Enum que encapsula la inforamcion de tipos de registros de
 *              actividades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 11:48 hrs.
 * @version 0.1
 */
public enum TipoActividadEnum {

	ACTIVIDAD(1, "Actividad", "Registro de una actividad relacionada con Conciliacion"),
	OPERACION(2, "Operacion", "Registro de una operacion relacionada con Conciliacion"),
	CRUD(3, "Crud", "Registro de operaciones crud sobre catalogos"), ERROR(4, "Error", "Registro de errores"),
	OTRO(5, "Otro", "Otro tipo");

	private Integer id;
	private String nombre;
	private String descripcion;

	TipoActividadEnum(Integer id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
