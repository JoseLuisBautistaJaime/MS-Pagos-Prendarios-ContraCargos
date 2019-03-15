package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

/**
 * Nombre: EntidadResponseDTO Descripcion: Clase que encapsula la unformacion de
 * un catalogo de entidad y es exclusiva para un tipo de response
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:06 hrs.
 * @version 0.1
 */
public class EntidadResponseDTO implements Comparable<EntidadResponseDTO> {

	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean estatus;
	private Date fechaCreacion;
	private String creadoPor;
	private Set<CuentaEntDTO> cuentas;
	private Set<ContactoEntDTO> contactos;

	public EntidadResponseDTO() {
		super();
	}

	public EntidadResponseDTO(Long id, String nombre, String descripcion, Boolean estatus, Date fechaCreacion,
			String creadoPor, Set<CuentaEntDTO> cuentas, Set<ContactoEntDTO> contactos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
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
	public int compareTo(EntidadResponseDTO arg0) {
		return arg0.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "EntidadResponseDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estatus="
				+ estatus + ", cuentas=" + cuentas + ", contactos=" + contactos + "]";
	}

}
