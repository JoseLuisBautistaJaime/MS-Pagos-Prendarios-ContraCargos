package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: ContactoResponseDTO Descripcion: Clase que encapsula la informacion perteneciente a los contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 05/03/2019 13:27 hrs.
 * @version 0.1
 */
public class ContactoResponseDTO extends AbstractCatalogoDTO{
	
	private String nombre;
	
	private String email;
	
	private String descripcion;
	
	private TipoContactoDTO tipoContacto;

	public ContactoResponseDTO() {
		super();
		
	}
	
	public ContactoResponseDTO(String nombre, String email, String descripcion,Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, TipoContactoDTO tipoContacto) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoContactoDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	@Override
	public String toString() {
		return "ContactoResponseDTO [nombre=" + nombre + ", email=" + email + ", descripcion=" + descripcion
				+ ", tipoContacto=" + tipoContacto + ", id=" + id + ", estatus=" + estatus + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
