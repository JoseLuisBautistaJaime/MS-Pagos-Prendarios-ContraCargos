/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.sql.Timestamp;

/**
 * @name TotalResumenDTO
 * @description Clase abstracta que encapsula la informacion del total de
 *              conciliaciones y cualquiera de sus sub-clases va dentro del
 *              response del endpoint de resumen de movimientos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/01/2020 20:55 hrs.
 * @version 0.1
 */
public abstract class TotalResumenDTO {

	protected TotalResumenDTO() {
		super();
	}

	protected TotalResumenDTO(Long total, Timestamp fechaInicio, Timestamp fechaFin) {
		super();
		this.total = total;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	private Long total;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "TotalResumenDTO [total=" + total + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

	public enum Tipos {

		P_TOTAL("total"), P_FECHA_INICIO("fechaInicio"), P_FECHA_FIN("fechaFin");

		String descripcion;

		private Tipos(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		protected void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

	}

}
