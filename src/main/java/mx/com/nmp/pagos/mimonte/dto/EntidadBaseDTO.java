package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

/**
 * Nombre: EntidadBaseDTO Descripcion: Clase que encapsula la unformacion de un
 * catalogo de entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class EntidadBaseDTO implements Comparable<EntidadBaseDTO> {

	private Long id;
	private String descripcion;
	private String shortDescription;
	private String nombre;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	private Boolean estatus;
	private Set<CuentaReqDTO> cuentas;
	private Set<ContactoReqDTO> contactos;

	public EntidadBaseDTO() {
		super();
	}

	public EntidadBaseDTO(Long id, String descripcion, String shortDescription, String nombre, Date createdDate,
			Date lastModifiedDate, String createdBy, String lastModifiedBy, Boolean estatus, Set<CuentaReqDTO> cuentas,
			Set<ContactoReqDTO> contactos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.shortDescription = shortDescription;
		this.nombre = nombre;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.estatus = estatus;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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
		return "EntidadBaseDTO [id=" + id + ", descripcion=" + descripcion + ", nombre=" + nombre + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", estatus=" + estatus + ", cuentas=" + cuentas
				+ ", contactos=" + contactos + "]";
	}

	@Override
	public int compareTo(EntidadBaseDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
