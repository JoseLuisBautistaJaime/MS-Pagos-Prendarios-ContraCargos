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

	GENERACION_CONCILIACION(1, "Generacion de Conciliacion",
			"Registro de una actividad relacionada con la generacion de una Conciliacion", TipoActividadEnum.ACTIVIDAD),

	ACTUALIZACION_ESTATUS_CONCILIACION(2, "Actualizacion de estatus de Conciliacion",
			"Registro de una operacion relacionada con la actualizacion de sub estatus y tal vez estatus de una Conciliacion",
			TipoActividadEnum.ACTIVIDAD),

	ACTUALIZAR_CONCILIACION(3, "Actualizacion de Conciliacion",
			"Registra un evento relacionado con la actualizacion de una Conciliacion", TipoActividadEnum.ACTIVIDAD),

	SOLICITAR_PAGO(4, "Solicitud de Pagos", "Registra un evento relacionado con la solicitud de pagos",
			TipoActividadEnum.ACTIVIDAD),

	ENVIO_DE_PLANTILLA(5, "Envio de Plantilla", "Registra un evento relacionado con el envio de plantillas.",
			TipoActividadEnum.ACTIVIDAD),

	CONSULTA_CONCILIACION(6, "Consulta de Conciliacion",
			"Registra un evento relacionado con la consulta de Conciliacion.", TipoActividadEnum.ACTIVIDAD),

	MOVIMIENTOS(7, "Generacion de movimientos",
			"Se genera algun tipo de movimiento (midas, proveedor transaccional o estado de cuenta.",
			TipoActividadEnum.ACTIVIDAD),

	ACTUALIZACION_ID_PEOPLE_SOFT(8, "Actualizacion de id de PeopleSoft",
			"Se genera una actualizacion del id de peoplesoft que es la actualizacion de id de asiento contable e id de poliaza contable.",
			TipoActividadEnum.ACTIVIDAD),
	
	COMISIONES(9, "Operaciones sobre comisiones",
			"Se realiza una operacion sobre las comisiones.",
			TipoActividadEnum.ACTIVIDAD);

	private Integer id;
	private String nombre;
	private String descripcion;
	private TipoActividadEnum tipoActividad;

	SubTipoActividadEnum(Integer id, String nombre, String descripcion, TipoActividadEnum tipoActividad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoActividad = tipoActividad;
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

	public TipoActividadEnum getTipoActividad() {
		return tipoActividad;
	}

}
