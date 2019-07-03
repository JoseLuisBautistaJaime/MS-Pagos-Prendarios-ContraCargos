/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Nombre: Contactos Descripcion: Entidad que representa al contacto dentro del
 * sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:04 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_contactos")
public class Contactos extends AbstractCatalogoAdm implements Serializable, Comparable<Contactos> {

	private static final long serialVersionUID = -2473378930460136183L;

	public Contactos() {
		super();
	}

	public Contactos(String nombre, String email, TipoContacto tipoContacto, Set<Entidad> entidades) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.tipoContacto = tipoContacto;
		this.entidades = entidades;
	}

	public Contactos(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
	}

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "email")
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_contacto")
	private TipoContacto tipoContacto;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "contactos")
	private Set<Entidad> entidades;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public Set<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(Set<Entidad> entidades) {
		this.entidades = entidades;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, email, tipoContacto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contactos other = (Contactos) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipoContacto == null) {
			if (other.tipoContacto != null)
				return false;
		} else if (!tipoContacto.equals(other.tipoContacto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contactos [nombre=" + nombre + ", email=" + email + ", tipoContacto=" + tipoContacto + "]";
	}

	@Override
	public int compareTo(Contactos o) {
		return o.nombre.compareTo(this.nombre);
	}

}
