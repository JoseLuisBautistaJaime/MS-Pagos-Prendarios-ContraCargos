package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

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
	private Set<CuentaEntDTO> cuentas;
	private Set<ContactoEntDTO> contactos;
	private Set<CodigoEstadoCuenta> codigoEstadoCuentaSet;

	public EntidadDTO() {
		super();
	}

	public EntidadDTO(String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos,
			Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
	}

	public EntidadDTO(String nombre, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos, Long id, Boolean estatus,
			Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription, Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
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

	public Set<CodigoEstadoCuenta> getCodigoEstadoCuentaSet() {
		return codigoEstadoCuentaSet;
	}

	public void setCodigoEstadoCuentaSet(Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
	}

	@Override
	public int compareTo(EntidadDTO arg0) {
		return arg0.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "EntidadDTO [nombre=" + nombre + ", cuentas=" + cuentas + ", contactos=" + contactos
				+ ", codigoEstadoCuentaSet=" + codigoEstadoCuentaSet + "]";
	}

}
