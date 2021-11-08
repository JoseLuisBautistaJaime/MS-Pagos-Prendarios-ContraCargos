/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Registro del trazado de la ejecución de la conciliación.
 *
 * @author Quarksoft
 * @version 1.0
 * @created 28-Oct-2021 5:57:43 PM
 */
@Entity
@Table(name = "to_trazado_ejecucion_conciliacion")
public class TrazadoEjecucionConciliacion implements Serializable  {

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
	private EstatusEjecucionConciliacion estatus;

	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_ejecucion_conciliacion", nullable = false)
	private EjecucionConciliacion ejecucionConciliacion;

	@Column(name = "estatus_descripcion")
	private String estatusDescripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", nullable = false)
	private Date fechaFin;


	public TrazadoEjecucionConciliacion() {
		super();
	}

    public TrazadoEjecucionConciliacion(Long id, EstatusEjecucionConciliacion estatus, EjecucionConciliacion ejecucionConciliacion, String estatusDescripcion, Date fechaInicio, Date fechaFin) {
        super();
        this.id = id;
	    this.estatus = estatus;
        this.ejecucionConciliacion = ejecucionConciliacion;
        this.estatusDescripcion = estatusDescripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((ejecucionConciliacion == null) ? 0 : ejecucionConciliacion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((estatusDescripcion == null) ? 0 : estatusDescripcion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof TrazadoEjecucionConciliacion))
			return false;

		final TrazadoEjecucionConciliacion other = (TrazadoEjecucionConciliacion) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "Conciliacion [id=" + id + ", ejecucionConciliacion=" + ejecucionConciliacion + ", estatus=" + estatus
				+ ", estatusDescripcion=" + estatusDescripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

}