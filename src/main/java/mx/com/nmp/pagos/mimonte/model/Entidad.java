package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.com.nmp.pagos.mimonte.dto.ContactoDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;

/**
 * Nombre: Entidad Descripcion: Clase que encapsula la informacion de una
 * entidad de catalogo de tipo Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
//@Entity
//@Table(name = "tc_entidad")
public class Entidad extends AbstractCatalogoAdm implements Comparable<Entidad> {

//	@Column(name = "nombre", nullable = false)
	private String nombre;
	
//	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	private Set<CuentaDTO> cuentas;	
	private Set<ContactoDTO> contactos;

	public Entidad() {
		super();
	}

	public Entidad(String nombre, Set<CuentaDTO> cuentas, Set<ContactoDTO> contactos, String descripcion) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
		this.descripcion = descripcion;
	}

	public Entidad(Long id, Boolean status, Date creationDate, Date modificationDate, String createdBy,
			String modifiedBy, String nombre, Set<CuentaDTO> cuentas, Set<ContactoDTO> contactos, String descripcion) {
		super(id, status, creationDate, modificationDate, createdBy, modifiedBy);
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<CuentaDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<ContactoDTO> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoDTO> contactos) {
		this.contactos = contactos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int compareTo(Entidad o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "Entidad [nombre=" + nombre + "]";
	}

}
