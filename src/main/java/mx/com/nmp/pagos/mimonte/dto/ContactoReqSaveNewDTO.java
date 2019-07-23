/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name ContactoReqSaveNewDTO
 * @description Clase que encapsula la informacion perteneciente a un contacto
 *              para alta desde servicio de entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:24 hrs.
 * @version 0.1
 */
public class ContactoReqSaveNewDTO implements Comparable<ContactoReqSaveNewDTO> {

	private String nombre;
	private String email;
//	private Boolean estatus;

	public ContactoReqSaveNewDTO() {
		super();
	}

	public ContactoReqSaveNewDTO(String nombre, String email/*, Boolean estatus*/) {
		super();
		this.nombre = nombre;
		this.email = email;
//		this.estatus = estatus;
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

//	public Boolean getEstatus() {
//		return estatus;
//	}

//	public void setEstatus(Boolean estatus) {
//		this.estatus = estatus;
//	}

	@Override
	public int compareTo(ContactoReqSaveNewDTO o) {
		return o.nombre.compareTo(this.nombre);
	}
}
