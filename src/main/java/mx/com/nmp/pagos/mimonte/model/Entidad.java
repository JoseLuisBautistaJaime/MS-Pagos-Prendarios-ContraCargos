package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Nombre: Entidad Descripcion: Clase que encapsula la informacion de una
 * entidad de catalogo de tipo Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_entidad")
public class Entidad extends AbstractCatalogoAdm implements Comparable<Entidad> {

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_entidad_cuenta", joinColumns = { @JoinColumn(name = "id_entidad") }, inverseJoinColumns = {
			@JoinColumn(name = "id_cuenta") })
	private Set<Cuenta> cuentas;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_entidad_contacto", joinColumns = { @JoinColumn(name = "id_entidad") }, inverseJoinColumns = {
			@JoinColumn(name = "id_contacto") })
	private Set<Contactos> contactos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entidades")
	private Set<CodigoEstadoCuenta> codigoEstadoCuentaSet;

	public Entidad() {
		super();
	}

	public Entidad(String nombre, Set<Cuenta> cuentas, Set<Contactos> contactos,
			Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public Entidad(String nombre, Set<Cuenta> cuentas, Set<Contactos> contactos,
			Set<CodigoEstadoCuenta> codigoEstadoCuentaSet, Long id, Boolean estatus, Date createdDate,
			Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<Contactos> getContactos() {
		return contactos;
	}

	public void setContactos(Set<Contactos> contactos) {
		this.contactos = contactos;
	}

	@Override
	public int compareTo(Entidad o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "Entidad [nombre=" + nombre + "cuentas=" + cuentas + ", contactos=" + contactos + "]";
	}

}
