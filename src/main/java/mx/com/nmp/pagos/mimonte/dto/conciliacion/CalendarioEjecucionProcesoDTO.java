/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 19-Nov-2021 09:13:55 AM
 */
public class CalendarioEjecucionProcesoDTO implements Comparable<CalendarioEjecucionProcesoDTO> {

	private Long id;
	private ProcesoDTO proceso;
	private String configuracionAutomatizacion;
	private Boolean activo;
	private Integer reintentos;
	private Integer rangoDiasCoberturaMin;
	private Integer rangoDiasCoberturaMax;
	private String createdBy;
	private Date createdDate;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private CorresponsalEnum corresponsal;

	public CalendarioEjecucionProcesoDTO() {
		super();
	}

	public CalendarioEjecucionProcesoDTO(Long id, ProcesoDTO proceso, String configuracionAutomatizacion, Integer reintentos, Integer rangoDiasCoberturaMin, Integer rangoDiasCoberturaMax, Boolean activo, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		this.id = id;
		this.proceso = proceso;
		this.configuracionAutomatizacion = configuracionAutomatizacion;
		this.activo = activo;
		this.reintentos = reintentos;
		this.rangoDiasCoberturaMin = rangoDiasCoberturaMin;
		this.rangoDiasCoberturaMax = rangoDiasCoberturaMax;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.corresponsal = corresponsal;
	}

	public CalendarioEjecucionProcesoDTO(Long id, Integer idProceso, String descripcionCortaProceso, String descripcionProceso, String configuracionAutomatizacion,
										 Integer reintentos, Integer rangoDiasCoberturaMin, Integer rangoDiasCoberturaMax, Boolean activo, String createdBy, Date createdDate, Date lastModifiedDate, String lastModifiedBy, CorresponsalEnum corresponsal) {
		super();
		this.id = id;
		this.proceso = new ProcesoDTO(idProceso, descripcionCortaProceso, descripcionProceso);
		this.configuracionAutomatizacion = configuracionAutomatizacion;
		this.reintentos = reintentos;
		this.rangoDiasCoberturaMin = rangoDiasCoberturaMin;
		this.rangoDiasCoberturaMax = rangoDiasCoberturaMax;
		this.activo = activo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.corresponsal = corresponsal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcesoDTO getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "CalendarioEjecucionProcesoDTO [id=" + id + ", proceso=" + proceso +", configuracionAutomatizacion=" + configuracionAutomatizacion + ", reintentos=" + reintentos + ", rangoDiasCoberturaMin=" + rangoDiasCoberturaMin + ", rangoDiasCoberturaMax=" + rangoDiasCoberturaMax
				+ ", activo=" + activo + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", corresponsal=" + corresponsal + "]";
	}

	@Override
	public int compareTo(CalendarioEjecucionProcesoDTO o) {
		return 0;
	}


}