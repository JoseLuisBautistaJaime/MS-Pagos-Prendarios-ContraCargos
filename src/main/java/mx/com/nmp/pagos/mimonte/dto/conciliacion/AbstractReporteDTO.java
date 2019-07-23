/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;
import java.util.Date;

/**
 * @name AbstractConciliacionDTO
 * @descriptipon Clase abstracta que que encapsula la informacion basica de un reporte para conciliacion.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 20:47 hrs.
 * @version 0.1
 */
public abstract class AbstractReporteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3239035423663826850L;
	
	protected Date fechaDesde;
	protected Date fechaHasta;
	protected Boolean disponible;
	
	public AbstractReporteDTO() {
		super();
	}
	public AbstractReporteDTO(Date fechaDesde, Date fechaHasta, Boolean disponible) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.disponible = disponible;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	@Override
	public String toString() {
		return "AbstractReporteDTO [fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", disponible="
				+ disponible + "]";
	}

}
