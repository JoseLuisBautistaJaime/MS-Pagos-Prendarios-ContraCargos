package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:47 PM
 */
@Entity
@Table(name = "to_movimiento_conciliacion")
@Inheritance(strategy = InheritanceType.JOINED)
public class MovimientoConciliacion extends Updatable implements Serializable {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -4506917958983577918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "id_conciliacion")
	private Integer idConciliacion;

	@Column(name = "nuevo")
	private Boolean nuevo;

	@ManyToOne
	@JoinColumn(name = "id_movimiento_midas")
	private MovimientoMidas movimientoMidas;

	public MovimientoConciliacion() {
		super();
	}

	public MovimientoConciliacion(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public MovimientoConciliacion(Long id, Integer idConciliacion, Boolean nuevo) {
		super();
		this.id = id;
		this.idConciliacion = idConciliacion;
		this.nuevo = nuevo;
	}

	public MovimientoConciliacion(Long id, Integer idConciliacion, Boolean nuevo, MovimientoMidas movimientoMidas) {
		super();
		this.id = id;
		this.idConciliacion = idConciliacion;
		this.nuevo = nuevo;
		this.movimientoMidas = movimientoMidas;
	}

	public MovimientoMidas getMovimientoMidas() {
		return movimientoMidas;
	}

	public void setMovimientoMidas(MovimientoMidas movimientoMidas) {
		this.movimientoMidas = movimientoMidas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Integer idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idConciliacion == null) ? 0 : idConciliacion.hashCode());
		result = prime * result + ((nuevo == null) ? 0 : nuevo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoConciliacion other = (MovimientoConciliacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idConciliacion == null) {
			if (other.idConciliacion != null)
				return false;
		} else if (!idConciliacion.equals(other.idConciliacion))
			return false;
		if (nuevo == null) {
			if (other.nuevo != null)
				return false;
		} else if (!nuevo.equals(other.nuevo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimientoConciliacion [id=" + id + ", idConciliacion=" + idConciliacion + ", nuevo=" + nuevo + "]";
	}
}