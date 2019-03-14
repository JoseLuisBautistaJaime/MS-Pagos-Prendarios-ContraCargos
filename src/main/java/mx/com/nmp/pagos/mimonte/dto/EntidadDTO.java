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

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 8080221303907003366L;

	private String nombre;
	private Set<CuentaReqDTO> cuentas;
	private Set<ContactoReqDTO> contactos;

	public EntidadDTO() {
		super();
	}

	public EntidadDTO(String nombre, Set<CuentaReqDTO> cuentas, Set<ContactoReqDTO> contactos) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public EntidadDTO(String nombre, Set<CuentaReqDTO> cuentas, Set<ContactoReqDTO> contactos, Long id, Boolean estatus,
			Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
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

	public Set<CuentaReqDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaReqDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<ContactoReqDTO> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoReqDTO> contactos) {
		this.contactos = contactos;
	}

	@Override
	public int compareTo(EntidadDTO arg0) {
		return arg0.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "EntidadDTO [nombre=" + nombre + ", cuentas=" + cuentas + ", contactos=" + contactos + "]";
	}

}
