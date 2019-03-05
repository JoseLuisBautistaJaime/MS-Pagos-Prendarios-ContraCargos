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
	private Set<CuentaDTO> cuentas;
	private Set<ContactoDTO> contactos;

	public EntidadDTO() {
		super();
	}

	public EntidadDTO(String nombre, Set<CuentaDTO> cuentas, Set<ContactoDTO> contactos) {
		super();
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public EntidadDTO(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion, Long usuarioCreador,
			String nombre, Set<CuentaDTO> cuentas, Set<ContactoDTO> contactos) {
		super(id, estatus, fechaCreacion, fechaModificacion, usuarioCreador);
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

	@Override
	public int compareTo(EntidadDTO arg0) {
		return arg0.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "EntidadDTO [nombre=" + nombre + "]";
	}

}
