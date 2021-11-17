/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.Objects;

/**
 * @name ActualizarEstatusEjecucionRequestDTO
 * @description Clase que encapsula la información del request de la
 *              actualizacion del estatus de una ejecución del proceso de conciliación
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 11/11/2021 13:48 hrs.
 * @version 0.1
 */
public class ActualizarEstatusEjecucionRequestDTO {

	private Long idEjecucionConciliacion;
	private Integer idEstatusEjecucion;
	private String descripcionEstatus;
	private Date fechaInicio;
	private Date fechaFin;

	public ActualizarEstatusEjecucionRequestDTO() {
		super();
	}

	public ActualizarEstatusEjecucionRequestDTO(Long idEjecucionConciliacion, Integer idEstatusEjecucion, String descripcionEstatus, Date fechaInicio, Date fechaFin) {
		this.idEjecucionConciliacion = idEjecucionConciliacion;
		this.idEstatusEjecucion = idEstatusEjecucion;
		this.descripcionEstatus = descripcionEstatus;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getIdEjecucionConciliacion() {
		return idEjecucionConciliacion;
	}

	public void setIdEjecucionConciliacion(Long idEjecucionConciliacion) {
		this.idEjecucionConciliacion = idEjecucionConciliacion;
	}

	public Integer getIdEstatusEjecucion() {
		return idEstatusEjecucion;
	}

	public void setIdEstatusEjecucion(Integer idEstatusEjecucion) {
		this.idEstatusEjecucion = idEstatusEjecucion;
	}

	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}

	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEjecucionConciliacion, idEstatusEjecucion, descripcionEstatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ActualizarEstatusEjecucionRequestDTO))
			return false;

		final ActualizarEstatusEjecucionRequestDTO other = (ActualizarEstatusEjecucionRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "ActualizarEstatusEjecucionRequestDTO [idEjecucionConciliacion=" + idEjecucionConciliacion + ", idEstatusEjecucion=" + idEstatusEjecucion
				+ ", descripcionEstatus="+ descripcionEstatus + ", fechaInicio="+ fechaInicio+ ", fechaFin="+ fechaFin+ "]";
	}

}
