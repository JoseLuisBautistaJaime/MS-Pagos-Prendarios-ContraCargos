package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name EstatusBonificacion
 * @description Clase que mapea el catalogo de estatus bonificaciones
 * @date: 31/01/2018 14:49 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_bonificacion")
public class EstatusBonificacion extends AbstractCatalogo implements Serializable {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 4424811334427701915L;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="estatus")
	private Boolean estatus;

	public EstatusBonificacion() {
		super();
	}

	public EstatusBonificacion(Integer id, String descripcionCorta, String descripcion) {
		super(id, descripcionCorta, descripcion);
	}

	public EstatusBonificacion(Integer id) {
		super(id);
	}

	public EstatusBonificacion(String nombre, Boolean estatus) {
		super();
		this.nombre = nombre;
		this.estatus = estatus;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, estatus);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstatusBonificacion other = (EstatusBonificacion) obj;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstatusDevolucion [nombre=" + nombre + ", estatus=" + estatus + "]";
	}

}
