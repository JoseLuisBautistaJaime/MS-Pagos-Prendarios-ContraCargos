/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 30-Nov-2021 11:33:55 AM
 */
public class FiltroCalendarioEjecucionProcesoDTO {


	private Long idCalendario;
	private Integer idProceso;
	private Boolean isActivo;
	private Integer reintentos;
	private String corresponsal;

	public FiltroCalendarioEjecucionProcesoDTO() {
		this.idCalendario = null;
		this.idProceso = null;
		this.isActivo = null;
		this.reintentos = null;
		this.corresponsal = null;
	}

	public FiltroCalendarioEjecucionProcesoDTO(Long idCalendario, Integer idProceso, Boolean isActivo, Integer reintentos, String corresponsal) {
		this.idCalendario = idCalendario;
		this.idProceso = idProceso;
		this.isActivo = isActivo;
		this.reintentos = reintentos;
		this.corresponsal = corresponsal;
	}

	public Long getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(Long idCalendario) {
		this.idCalendario = idCalendario;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Boolean getActivo() {
		return isActivo;
	}

	public void setActivo(Boolean activo) {
		isActivo = activo;
	}

	public Integer getReintentos() {
		return reintentos;
	}

	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}

	public String getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(String corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "FiltroCalendarioEjecucionProcesoDTO [idCalendario=" + idCalendario + ", idProceso=" + idProceso
				+ ", isActivo=" + isActivo + ", reintentos=" + reintentos + ", corresponsal=" + corresponsal + "]";
	}

}