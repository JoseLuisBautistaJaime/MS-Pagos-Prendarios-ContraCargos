/*
 * Proyecto:       NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.Updatable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Registro de la calendarizacion de los procesos de automatización.
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 19-Nov-2021 5:57:43 PM
 */
@Entity
@Table(name = "to_calendario_ejecucion_proceso")
public class CalendarioEjecucionProceso extends Updatable implements Serializable  {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -3886314835601436753L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_proceso")
	private CatalogoProceso proceso;

	@Column(name = "configuracion")
	private String configuracion;

	@Column(name = "reintentos", nullable = false)
	private Integer reintentos;

	@Column(name = "rango_dias_cobertura_min", nullable = false)
	private Integer rangoDiasCoberturaMin;

	@Column(name = "rango_dias_cobertura_max", nullable = false)
	private Integer rangoDiasCoberturaMax;

	@ManyToOne
	@JoinColumn(name = "proveedor")
	private Proveedor proveedor;

	@NotNull
	@Column(name = "activo", nullable = false)
	private Boolean activo;

	public CalendarioEjecucionProceso() {
		super();
	}

	public CalendarioEjecucionProceso(Long id) {
		super();
		this.id = id;
	}

	public CalendarioEjecucionProceso(Long id, CatalogoProceso proceso, String configuracion, Integer reintentos, Integer rangoDiasCoberturaMin,Integer rangoDiasCoberturaMax, Proveedor proveedor, @NotNull Boolean activo) {
		super();
		this.id = id;
		this.proceso = proceso;
		this.configuracion = configuracion;
		this.reintentos = reintentos;
		this.rangoDiasCoberturaMin=rangoDiasCoberturaMin;
		this.rangoDiasCoberturaMax=rangoDiasCoberturaMax;
		this.proveedor = proveedor;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CatalogoProceso getProceso() {
		return proceso;
	}

	public void setProceso(CatalogoProceso proceso) {
		this.proceso = proceso;
	}

	public String getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(String configuracion) {
		this.configuracion = configuracion;
	}

	public Integer getReintentos() {
		return reintentos;
	}

	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((proceso == null) ? 0 : proceso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((configuracion == null) ? 0 : configuracion.hashCode());
		result = prime * result + ((proveedor == null) ? 0 : proveedor.hashCode());
		result = prime * result + ((reintentos == null) ? 0 : reintentos.hashCode());
		result = prime * result + ((rangoDiasCoberturaMin == null) ? 0 : rangoDiasCoberturaMin.hashCode());
		result = prime * result + ((rangoDiasCoberturaMax == null) ? 0 : rangoDiasCoberturaMax.hashCode());
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof CalendarioEjecucionProceso))
			return false;

		final CalendarioEjecucionProceso other = (CalendarioEjecucionProceso) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "CalendarioEjecucionProceso [id=" + id + ", proceso=" + proceso + ", configuracion=" + configuracion + ", reintentos=" + reintentos
				+ ", rangoDiasCoberturaMin=" + rangoDiasCoberturaMin + ", rangoDiasCoberturaMax=" + rangoDiasCoberturaMax + ", activo=" + activo + ", proveedor=" + proveedor + "]";
	}

}