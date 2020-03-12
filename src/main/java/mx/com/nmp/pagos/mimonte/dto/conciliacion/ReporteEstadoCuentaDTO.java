/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ReporteEstadoCuentaDTO
 * @description Clase que encapsula la informacion de reporte para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 20:58 hrs.
 * @version 0.1
 */
public class ReporteEstadoCuentaDTO extends AbstractReporteDTO implements Comparable<ReporteEstadoCuentaDTO>{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -3359303439426767888L;

	public ReporteEstadoCuentaDTO() {
		super();
	}

	public ReporteEstadoCuentaDTO(Date fechaDesde, Date fechaHasta, Boolean disponible) {
		super(fechaDesde, fechaHasta, disponible);
	}

	@Override
	public String toString() {
		return "ReporteEstadoCuentaDTO []";
	}

	@Override
	public int compareTo(ReporteEstadoCuentaDTO o) {
		return 0;
	}

}
