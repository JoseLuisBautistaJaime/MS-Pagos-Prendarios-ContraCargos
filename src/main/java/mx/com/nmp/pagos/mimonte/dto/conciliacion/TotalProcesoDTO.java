/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.sql.Timestamp;

/**
 * @name TotalProcesoDTO
 * @description Clase que encapsula la informacion del total de conciliaciones
 *              y va dentro del response del endpoint de resumen de movimientos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/01/2020 20:55 hrs.
 * @version 0.1
 */
public class TotalProcesoDTO extends TotalResumenDTO {

	public TotalProcesoDTO() {
		super();
		Tipos.P_TOTAL.setDescripcion("p_total");
		Tipos.P_FECHA_INICIO.setDescripcion("p_fechaInicio");
		Tipos.P_FECHA_FIN.setDescripcion("p_fechaFin");
	}

	public TotalProcesoDTO(Long total, Timestamp fechaInicio, Timestamp fechaFin) {
		super(total, fechaInicio, fechaFin);
	}

}
