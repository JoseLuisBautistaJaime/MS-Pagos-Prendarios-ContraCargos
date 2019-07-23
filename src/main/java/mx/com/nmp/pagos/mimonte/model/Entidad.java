/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;

/**
 * @name Entidad
 * @description Clase que encapsula la informacion de una entidad de catalogo de
 *              tipo Entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_entidad")
@NamedQueries(value = {
		@NamedQuery(name = "Entidad.findByNombreAndEstatus", query = "SELECT ent FROM Entidad ent WHERE (:nombre IS NULL OR ent.nombre = :nombre) AND (:estatus IS NULL OR ent.estatus = :estatus)"),
		@NamedQuery(name = "Entidad.setEstatusById", query = "UPDATE Entidad ent set ent.estatus = :estatus, ent.lastModifiedBy = :lastModifiedBy, ent.lastModifiedDate = :lastModifiedDate WHERE ent.id = :id") })
public class Entidad extends AbstractCatalogoAdm implements Comparable<Entidad>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JoinTable(name = "tr_entidad_contactos", joinColumns = {
			@JoinColumn(name = "id_entidad", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_contacto", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Contactos> contactos;

	@OneToMany(mappedBy = "entidad")
	private Set<CodigoEstadoCuenta> codigoEstadoCuentaSet;

	@OneToMany(mappedBy = "entidad", fetch = FetchType.LAZY)
	private Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet;

	@OneToMany(mappedBy = "entidad", fetch = FetchType.LAZY)
	private Set<Conciliacion> conciliacionSet;

	public Entidad() {
		super();
	}

	public Entidad(Long id) {
		super();
		this.id = id;
	}

	public Entidad(String nombre, Set<Contactos> contactos) {
		super();
		this.nombre = nombre;
		this.contactos = contactos;
	}

	public Entidad(String nombre, Set<Contactos> contactos, Long id, Boolean estatus, Date createdDate,
			Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.nombre = nombre;
		this.contactos = contactos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Contactos> getContactos() {
		return contactos;
	}

	public void setContactos(Set<Contactos> contactos) {
		this.contactos = contactos;
	}

	public Set<CodigoEstadoCuenta> getCodigoEstadoCuentaSet() {
		return codigoEstadoCuentaSet;
	}

	public void setCodigoEstadoCuentaSet(Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
	}

	public Set<EntidadCuentaAfiliacion> getEntidadCuentaAfiliacionSet() {
		return entidadCuentaAfiliacionSet;
	}

	public void setEntidadCuentaAfiliacionSet(Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet) {
		this.entidadCuentaAfiliacionSet = entidadCuentaAfiliacionSet;
	}

	public Set<Conciliacion> getConciliacionSet() {
		return conciliacionSet;
	}

	public void setConciliacionSet(Set<Conciliacion> conciliacionSet) {
		this.conciliacionSet = conciliacionSet;
	}

	@Override
	public String toString() {
		return "Entidad [nombre=" + nombre + ", contactos=" + (contactos != null ? contactos.size() : 0) +
				", codigoEstadoCuentaSet=" + (codigoEstadoCuentaSet != null ? codigoEstadoCuentaSet.size() : 0) +
				", entidadCuentaAfiliacionSet=" + (entidadCuentaAfiliacionSet != null ? entidadCuentaAfiliacionSet.size() : 0) +
				", conciliacionSet=" + (conciliacionSet != null ? conciliacionSet.size() : 0) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Entidad))
			return false;

		final Entidad other = (Entidad) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(Entidad o) {
		return o.getNombre().compareTo(this.nombre);
	}

}
