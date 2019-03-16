package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: Contacto Descripcion: Clase que encapsula la informacion
 * perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 14:27 hrs.
 * @version 0.1
 */
public class ContactoRequestDTO {

	private String nombre;

	private String email;

	private String descripcion;

	private Long tipoContacto;

	public ContactoRequestDTO() {
		super();
	}

	public ContactoRequestDTO(String nombre, String email, String descripcion, Long tipoContacto) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(Long tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	@Override
	public String toString() {
		return "ContactoRequestDTO [nombre=" + nombre + ", email=" + email + ", descripcion=" + descripcion
				+ ", tipoContacto=" + tipoContacto + "]";
	}

//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}

}
