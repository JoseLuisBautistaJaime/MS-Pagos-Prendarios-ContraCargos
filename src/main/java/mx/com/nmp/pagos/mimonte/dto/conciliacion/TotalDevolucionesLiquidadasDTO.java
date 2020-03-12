/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.sql.Timestamp;

/**
 * @name TotalDevolucionesLiquidadasDTO
 * @description Clase que encapsula la informacion del total de devoluciones
 *              liquidadas de conciliaciones y va dentro del response del
 *              endpoint de resumen de movimientos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/01/2020 20:55 hrs.
 * @version 0.1
 */
public class TotalDevolucionesLiquidadasDTO extends TotalResumenDTO {

	public TotalDevolucionesLiquidadasDTO() {
		super();
		Tipos.P_TOTAL.setDescripcion("d_total");
		Tipos.P_FECHA_INICIO.setDescripcion("d_fechaInicio");
		Tipos.P_FECHA_FIN.setDescripcion("d_fechaFin");
	}

	public TotalDevolucionesLiquidadasDTO(Long total, Timestamp fechaInicio, Timestamp fechaFin) {
		super(total, fechaInicio, fechaFin);
	}

}
