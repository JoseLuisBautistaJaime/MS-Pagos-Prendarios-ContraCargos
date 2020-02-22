/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name TipoMovimientoEnum
 *
 * @description Enum que contiene los tipos de comisiones
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @creationDate 27/05/2019 15:03 hrs.
 */
public enum TipoMovimientoComisionEnum {

	COMISION(1, "COMISION"), IVA_COMISION(2, "IVA_COMISION"), OPENPAY(3, "OPENPAY"), GENERAL(4, "GENERAL");

	private int id;
	private String descripcion;

	private TipoMovimientoComisionEnum(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
