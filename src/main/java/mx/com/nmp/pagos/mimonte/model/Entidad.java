package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.com.nmp.pagos.mimonte.dto.ContactoEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;

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

	@Transient
	private Set<CuentaEntDTO> cuentas;

	@Transient
	private Set<ContactoEntDTO> contactos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entidades")
	private Set<CodigoEstadoCuenta> codigoEstadoCuentaSet;

	public Entidad() {
		super();
	}

	public Entidad(String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos,
			Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public Entidad(String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos,
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

	public Set<CuentaEntDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaEntDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<ContactoEntDTO> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoEntDTO> contactos) {
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
