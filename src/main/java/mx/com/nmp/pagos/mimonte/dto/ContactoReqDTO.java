/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name ContactoReqDTO
 * @description Clase DTO que mapea objetos relacionados con el catalogo de
 *              Contactos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 25/03/2019 16:53 hrs.
 * @version 0.1
 */
public class ContactoReqDTO implements Comparable<ContactoReqDTO> {

	private Long id;
	private String nombre;
	private String email;
	private Boolean estatus;

	public ContactoReqDTO() {
		super();
	}

	public ContactoReqDTO(Long id, String nombre, String email, Boolean estatus) {
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
		return "ContactoReqDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(ContactoReqDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
