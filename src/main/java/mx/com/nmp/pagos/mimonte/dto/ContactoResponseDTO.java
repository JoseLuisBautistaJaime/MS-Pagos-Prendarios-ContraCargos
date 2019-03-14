package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: ContactoResponseDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a los contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 05/03/2019 13:27 hrs.
 * @version 0.1
 */
public class ContactoResponseDTO extends AbstractCatalogoDTO {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	private String email;

	private TipoContactoDTO tipoContacto;

	public ContactoResponseDTO() {
		super();

	}

	public ContactoResponseDTO(String nombre, String email, TipoContactoDTO tipoContacto) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.tipoContacto = tipoContacto;
	}

	public ContactoResponseDTO(String nombre, String email, TipoContactoDTO tipoContacto, Long id, Boolean estatus,
			Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.nombre = nombre;
		this.email = email;

		this.tipoContacto = tipoContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoContactoDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	@Override
	public String toString() {
		return "ContactoResponseDTO [nombre=" + nombre + ", email=" + email + ", " + "tipoContacto=" + tipoContacto
				+ ", id=" + id + ", estatus=" + estatus + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
