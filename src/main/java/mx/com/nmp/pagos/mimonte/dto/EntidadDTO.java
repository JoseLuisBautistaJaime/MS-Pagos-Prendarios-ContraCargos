package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

/**
 * Nombre: EntidadDTO Descripcion: Clase que encapsula la unformacion de un
 * catalogo de entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:06 hrs.
 * @version 0.1
 */
public class EntidadDTO extends AbstractCatalogoDTO implements Comparable<EntidadDTO> {

	private String nombre;
	private String descripcion;
	private Set<CuentaEntDTO> cuentas;
	private Set<ContactoEntDTO> contactos;

	public EntidadDTO() {
		super();
	}

	public EntidadDTO(String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos, String descripcion) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
		this.descripcion = descripcion;
	}

	public EntidadDTO(Long id, Boolean status, Date creationDate, Date modificationDate, String createdBy,
			String modifiedBy, String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos, String descripcion) {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public int compareTo(EntidadDTO arg0) {
		return arg0.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "EntidadDTO [nombre=" + nombre + ", descripcion=" + descripcion + ", cuentas=" + cuentas + ", contactos="
				+ contactos + "]";
	}

}
