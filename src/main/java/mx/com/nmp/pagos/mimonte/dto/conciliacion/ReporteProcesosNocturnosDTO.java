/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ReporteProcesosNocturnosDTO
 * @description Clase que encapsula la informacion del estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:22 hrs.
 * @version 0.1
 */
public class ReporteProcesosNocturnosDTO extends AbstractReporteDTO implements Comparable<ReporteProcesosNocturnosDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5421151814420008102L;

	public ReporteProcesosNocturnosDTO() {
		super();
	}

	public ReporteProcesosNocturnosDTO(Date fechaDesde, Date fechaHasta, Boolean disponible) {
		super(fechaDesde, fechaHasta, disponible);
	}

	@Override
	public String toString() {
		return "ReporteProcesosNocturnosDTO []";
	}

	@Override
	public int compareTo(ReporteProcesosNocturnosDTO o) {
		return 0;
	}

}
