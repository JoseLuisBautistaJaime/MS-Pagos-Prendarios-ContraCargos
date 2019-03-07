package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CuentaEntDTO Descripcion: Clase que encapsula la informacion de una
 * contacto a enviar como respuesta dentro del objeto Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 07/03/2019 10:340 hrs.
 * @version 0.1
 */
public class ContactoEntDTO implements Comparable<ContactoEntDTO> {

	private Long id;
	private String nombre;
	private String email;
	private Boolean estatus;

	public ContactoEntDTO() {
		super();
	}

	public ContactoEntDTO(Long id, String nombre, String email, Boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.estatus = estatus;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "ContactoEntDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(ContactoEntDTO o) {
		return o.getNombre().compareTo(this.nombre);
	}

}
