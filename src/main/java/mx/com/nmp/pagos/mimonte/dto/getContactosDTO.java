package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: getContactosDTO Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 15/03/2019 15:30 hrs.
 * @version 0.1
 */
public class getContactosDTO {
	
	private String nombre;
	
	private String email;

	public getContactosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public getContactosDTO(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
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

	@Override
	public String toString() {
		return "getContactosDTO [nombre=" + nombre + ", email=" + email + "]";
	}

}
