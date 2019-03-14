package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * Nombre: EntidadBaseDTO Descripcion: Clase que encapsula la unformacion de un
 * catalogo de entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class EntidadBaseDTO {

	private Long id;
	private String descripcion;
	private String nombre;
	private Set<CuentaReqDTO> cuentas;
	private Set<ContactoReqDTO> contactos;

	public EntidadBaseDTO() {
		super();
	}

	public EntidadBaseDTO(Long id, String descripcion, String nombre, Set<CuentaReqDTO> cuentas,
			Set<ContactoReqDTO> contactos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String toString() {
		return "EntidadBaseDTO [id=" + id + ", descripcion=" + descripcion + ", nombre=" + nombre + ", cuentas="
				+ cuentas + ", contactos=" + contactos + "]";
	}

}
