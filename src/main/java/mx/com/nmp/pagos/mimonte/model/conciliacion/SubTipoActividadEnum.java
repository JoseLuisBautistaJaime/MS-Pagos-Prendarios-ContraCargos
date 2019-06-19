/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name TipoActividadEnum
 * @description Enum que encapsula la inforamcion de sub tipos de registros de
 *              actividades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 12:23 hrs.
 * @version 0.1
 */
public enum SubTipoActividadEnum {

	ACTIVIDAD_CONCILIACION(1, "Actividad Conciliacion", "Registro de una actividad relacionada con Conciliacion"),
	OPERACION_CONCILIACION(2, "Operacion Conciliacion", "Registro de una operacion relacionada con Conciliacion"),
	CRUD_CATALOGOS(3, "Crud Catalogos", "Registro de operaciones crud sobre catalogos"),
	ERROR_TRANSACCION(4, "Error Transacciones", "Registro de errores en transacciones"),
	OTRO(5, "Otro", "Otro SubTipo");

	private Integer id;
	private String nombre;
	private String descripcion;

	SubTipoActividadEnum(Integer id, String nombre, String descripcion) {
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
