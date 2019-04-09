/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class Entidad extends AbstractCatalogoAdm implements Comparable<Entidad> {

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_entidad_cuenta", joinColumns = {
			@JoinColumn(name = "id_entidad", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_cuenta", referencedColumnName = "id") })
	private Set<Cuenta> cuentas;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_entidad_contactos", joinColumns = {
			@JoinColumn(name = "id_entidad", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_contacto", referencedColumnName = "id") })
	private Set<Contactos> contactos;

	@OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
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

	public Set<CodigoEstadoCuenta> getCodigoEstadoCuentaSet() {
		return codigoEstadoCuentaSet;
	}

	public void setCodigoEstadoCuentaSet(Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
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
