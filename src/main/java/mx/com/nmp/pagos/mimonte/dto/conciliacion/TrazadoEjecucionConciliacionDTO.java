/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacion;

import java.util.Date;
import java.util.Objects;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 09-Nov-2021 09:13:55 AM
 */
public class TrazadoEjecucionConciliacionDTO implements Comparable<TrazadoEjecucionConciliacionDTO> {

	private Long id;
	private EstatusEjecucionConciliacionDTO estatusEjecucion;
	private EjecucionConciliacionDTO ejecucionConciliacion;
	private String estatusDescripcion;
	private Date fechaInicio;
	private Date fechaFin;

	public TrazadoEjecucionConciliacionDTO() {
		super();
	}

	public TrazadoEjecucionConciliacionDTO(Long id, EstatusEjecucionConciliacionDTO estatusEjecucion, EjecucionConciliacionDTO ejecucionConciliacion, String estatusDescripcion, Date fechaInicio, Date fechaFin) {
		super();
		this.id = id;
		this.estatusEjecucion = estatusEjecucion;
		this.ejecucionConciliacion = ejecucionConciliacion;
		this.estatusDescripcion = estatusDescripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public TrazadoEjecucionConciliacionDTO(Long id, EstatusEjecucionConciliacion estatusEjecucion, String estatusDescripcion, Date fechaInicio, Date fechaFin) {
		super();
		this.id = id;
		this.estatusEjecucion = new EstatusEjecucionConciliacionDTO(estatusEjecucion.getId(), estatusEjecucion.getDescripcionCorta(), estatusEjecucion.getDescripcion(), estatusEjecucion.getOrderNumber());
		this.estatusDescripcion = estatusDescripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusEjecucionConciliacionDTO getEstatusEjecucion() {
		return estatusEjecucion;
	}

	public void setEstatusEjecucion(EstatusEjecucionConciliacionDTO estatusEjecucion) {
		this.estatusEjecucion = estatusEjecucion;
	}

	public EjecucionConciliacionDTO getEjecucionConciliacion() {
		return ejecucionConciliacion;
	}

	public void setEjecucionConciliacion(EjecucionConciliacionDTO ejecucionConciliacion) {
		this.ejecucionConciliacion = ejecucionConciliacion;
	}

	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}

	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "TrazadoEjecucionConciliacionDTO [id=" + id + ", estatusEjecucion=" + estatusEjecucion +", estatusDescripcion=" + estatusDescripcion + ", ejecucionConciliacion=" + ejecucionConciliacion
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

	@Override
	public int compareTo(TrazadoEjecucionConciliacionDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, estatusEjecucion, ejecucionConciliacion, estatusDescripcion, fechaInicio, fechaFin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TrazadoEjecucionConciliacionDTO)) {
			return false;
		}
		final TrazadoEjecucionConciliacionDTO other = (TrazadoEjecucionConciliacionDTO) obj;
		return (this.hashCode() == other.hashCode());

	}


}