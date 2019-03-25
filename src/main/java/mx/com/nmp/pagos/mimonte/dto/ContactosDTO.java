/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: ContactosDTO Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 06/03/2019 22:00 hrs.
 * @version 0.1
 */
public class ContactosDTO {
	
	private ContactoRespDTO contactos;

	public ContactosDTO() {
		super();
	}

	public ContactosDTO(ContactoRespDTO contactos) {
		super();
		this.contactos = contactos;
	}

	public ContactoRespDTO getContactos() {
		return contactos;
	}

	public void setContactos(ContactoRespDTO contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "ContactosDTO [contactos=" + contactos + "]";
	}

}
