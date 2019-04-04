/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ReporteProveedorTransaccionalDTO
 * @description Clase que encapsula la informacion de reporte para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 20:55 hrs.
 * @version 0.1
 */
public class ReporteProveedorTransaccionalDTO extends AbstractReporteDTO implements Comparable<ReporteProveedorTransaccionalDTO>{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 656077734409024692L;

	public ReporteProveedorTransaccionalDTO() {
		super();
	}

	public ReporteProveedorTransaccionalDTO(Date fechaDesde, Date fechaHasta, Boolean disponible) {
		super(fechaDesde, fechaHasta, disponible);
	}

	@Override
	public String toString() {
		return "ReporteProveedorTransaccionalDTO []";
	}

	@Override
	public int compareTo(ReporteProveedorTransaccionalDTO o) {
		return 0;
	}

}
