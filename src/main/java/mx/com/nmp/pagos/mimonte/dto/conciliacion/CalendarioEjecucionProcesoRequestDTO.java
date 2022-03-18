/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;


/**
 * @author Quarksoft
 * @version 1.0
 * @created 17-Marzo-2022 12:13:55 AM
 */
public class CalendarioEjecucionProcesoRequestDTO implements Comparable<CalendarioEjecucionProcesoRequestDTO> {

	private Long idCalendario;
	private Integer proceso;
	private String configuracionAutomatizacion;
	private Boolean activo;
	private Integer reintentos;
	private Integer rangoDiasCoberturaMin;
	private Integer rangoDiasCoberturaMax;
	private String corresponsal;

	public CalendarioEjecucionProcesoRequestDTO() {
		super();
	}

	public CalendarioEjecucionProcesoRequestDTO(Long idCalendario, Integer proceso, String configuracionAutomatizacion, Integer reintentos, Integer rangoDiasCoberturaMin, Integer rangoDiasCoberturaMax, Boolean activo, String corresponsal) {
		this.idCalendario = idCalendario;
		this.proceso = proceso;
		this.configuracionAutomatizacion = configuracionAutomatizacion;
		this.activo = activo;
		this.reintentos = reintentos;
		this.rangoDiasCoberturaMin = rangoDiasCoberturaMin;
		this.rangoDiasCoberturaMax = rangoDiasCoberturaMax;
		this.corresponsal = corresponsal;
	}

	public Long getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(Long idCalendario) {
		this.idCalendario = idCalendario;
	}

	public String getConfiguracionAutomatizacion() {
		return configuracionAutomatizacion;
	}

	public void setConfiguracionAutomatizacion(String configuracionAutomatizacion) {
		this.configuracionAutomatizacion = configuracionAutomatizacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getRangoDiasCoberturaMin() {
		return rangoDiasCoberturaMin;
	}

	public void setRangoDiasCoberturaMin(Integer rangoDiasCoberturaMin) {
		this.rangoDiasCoberturaMin = rangoDiasCoberturaMin;
	}

	public Integer getRangoDiasCoberturaMax() {
		return rangoDiasCoberturaMax;
	}

	public void setRangoDiasCoberturaMax(Integer rangoDiasCoberturaMax) {
		this.rangoDiasCoberturaMax = rangoDiasCoberturaMax;
	}

	public Integer getReintentos() {
		return reintentos;
	}

	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}

	public Integer getProceso() {
		return proceso;
	}

	public void setProceso(Integer proceso) {
		this.proceso = proceso;
	}

	public String getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(String corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "CalendarioEjecucionProcesoRequestDTO [idCalendario=" + idCalendario + ", proceso=" + proceso +", configuracionAutomatizacion=" + configuracionAutomatizacion + ", reintentos=" + reintentos + ", rangoDiasCoberturaMin=" + rangoDiasCoberturaMin + ", rangoDiasCoberturaMax=" + rangoDiasCoberturaMax
				+ ", activo=" + activo  + ", corresponsal=" + corresponsal + "]";
	}

	@Override
	public int compareTo(CalendarioEjecucionProcesoRequestDTO o) {
		return 0;
	}


}