/*
 * Proyecto:       NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.Updatable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Registro de la ejecución de la pre-conciliación.
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 28-Oct-2021 5:57:43 PM
 */
@Entity
@Table(name = "to_ejecucion_preconciliacion")
public class EjecucionPreconciliacion extends Updatable implements Serializable  {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -3886314835601436753L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_estatus_ejecucion")
	private EstatusEjecucionPreconciliacion estatus;

	@Column(name = "estatus_descripcion")
	private String estatusDescripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ejecucion", nullable = false)
	private Date fechaEjecucion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_periodo_inicio", nullable = false)
	private Date fechaPeriodoInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_periodo_fin", nullable = false)
	private Date fechaPeriodoFin;

	@ManyToOne
	@JoinColumn(name = "proveedor")
	private Proveedor proveedor;

	public EjecucionPreconciliacion() {
		super();
	}

	public EjecucionPreconciliacion(Long id, Long folio, EstatusEjecucionPreconciliacion estatus, String estatusDescripcion, Date fechaEjecucion, Date fechaPeriodoInicio, Date fechaPeriodoFin, Proveedor proveedor) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.estatusDescripcion = estatusDescripcion;
		this.fechaEjecucion = fechaEjecucion;
		this.fechaPeriodoInicio = fechaPeriodoInicio;
		this.fechaPeriodoFin = fechaPeriodoFin;
		this.proveedor = proveedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusEjecucionPreconciliacion getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusEjecucionPreconciliacion estatus) {
		this.estatus = estatus;
	}

	public String getEstatusDescripcion() {
		return estatusDescripcion;
	}

	public void setEstatusDescripcion(String estatusDescripcion) {
		this.estatusDescripcion = estatusDescripcion;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public Date getFechaPeriodoInicio() {
		return fechaPeriodoInicio;
	}

	public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
		this.fechaPeriodoInicio = fechaPeriodoInicio;
	}

	public Date getFechaPeriodoFin() {
		return fechaPeriodoFin;
	}

	public void setFechaPeriodoFin(Date fechaPeriodoFin) {
		this.fechaPeriodoFin = fechaPeriodoFin;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fechaEjecucion == null) ? 0 : fechaEjecucion.hashCode());
		result = prime * result + ((fechaPeriodoInicio == null) ? 0 : fechaPeriodoInicio.hashCode());
		result = prime * result + ((fechaPeriodoFin == null) ? 0 : fechaPeriodoFin.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((estatusDescripcion == null) ? 0 : estatusDescripcion.hashCode());
		result = prime * result + ((proveedor == null) ? 0 : proveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EjecucionPreconciliacion))
			return false;

		final EjecucionPreconciliacion other = (EjecucionPreconciliacion) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "Conciliacion [id=" + id + ", estatus=" + estatus + ", estatusDescripcion=" + estatusDescripcion + ", fechaEjecucion=" + fechaEjecucion
				+ ", fechaPeriodoInicio=" + fechaPeriodoInicio + ", fechaPeriodoFin=" + fechaPeriodoFin + ", proveedor=" + proveedor + "]";
	}

}