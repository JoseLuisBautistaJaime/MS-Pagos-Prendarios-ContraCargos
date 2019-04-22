/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * 
 * @name ContactoReqDTONE
 * @description Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @date 21/04/2019 23:50 hrs.
 * @version 0.1
 */
public class ContactoReqDTONE implements Comparable<ContactoReqDTONE> {

	private Long id;
	private String nombre;
	private String email;

	public ContactoReqDTONE() {
		super();
	}

	public ContactoReqDTONE(Long id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
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

	@Override
	public String toString() {
		return "ContactoReqDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
	}

	@Override
	public int compareTo(ContactoReqDTONE o) {
		return o.id.compareTo(this.id);
	}

}
