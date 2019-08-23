/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name TipoMovimientoActualizacionTransito
 * @description Enum que encapsula la inforamcion de tipos de movimientos a los
 *              que se puede actualizar un movimiento en transito
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/08/2019 17:11 hrs.
 * @version 0.1
 */
public enum TipoMovimientoActualizacionTransito {

	PAGO(1, "PAGO"), DEVOLUCION(2, "DEVOLUCION");

	private int id;
	private String nombre;

	private TipoMovimientoActualizacionTransito(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
