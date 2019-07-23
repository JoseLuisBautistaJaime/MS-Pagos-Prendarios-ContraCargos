/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: Contacto Descripcion: Clase que encapsula la informacion
 * perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 14:27 hrs.
 * @version 0.1
 */
public class ContactoRequestDTO {

	
	private String descripcion;
	private String email;
	private String nombre;
	private TipoContactoReqDTO tipoContacto;
	
	public ContactoRequestDTO() {
		super();
	}

	public ContactoRequestDTO(String descripcion, String email, String nombre, TipoContactoReqDTO tipoContacto) {
		super();
		this.descripcion = descripcion;
		this.email = email;
		this.nombre = nombre;
		this.tipoContacto = tipoContacto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoContactoReqDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoReqDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	

	@Override
	public String toString() {
		return "ContactoRequestDTO [descripcion=" + descripcion + ", email=" + email + ", nombre=" + nombre
				+ ", tipoContacto=" + tipoContacto + "]";
	}
}
