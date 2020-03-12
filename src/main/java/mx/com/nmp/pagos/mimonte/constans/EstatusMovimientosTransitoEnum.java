/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name EstatusMovimientosTransitoEnum
 * @description Enum que mapea los posibles estatusde movimientos en transito y
 *              simula el mapeo de la tabla tk_estatus_movimientos_en_transito
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @creationDate 13/11/2019 12:29 hrs.
 */
public enum EstatusMovimientosTransitoEnum {

	NO_IDENTIFICADA_EN_MIDAS(1, "No identificada en MIDAS"), SOLICITADA(2, "Solicitada"),
	MARCADA_DEVOLUCION(3, "Marcada devolucion"), NO_IDENTIFICADA_EN_OPENPAY(4, "No identificada en Open Pay");

	private int id;
	private String nombre;

	private EstatusMovimientosTransitoEnum(int id, String nombre) {
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
