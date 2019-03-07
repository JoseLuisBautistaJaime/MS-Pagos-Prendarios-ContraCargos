package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: ContactoDTO Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 06/03/2019 22:07 hrs.
 * @version 0.1
 */
public class ContactoDTO {
	
	private ContactoRespDTO contacto;

	public ContactoDTO() {
		super();
	}

	public ContactoDTO(ContactoRespDTO contacto) {
		super();
		this.contacto = contacto;
	}

	public ContactoRespDTO getContacto() {
		return contacto;
	}

	public void setContacto(ContactoRespDTO contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "ContactoDTO [contacto=" + contacto + "]";

	}

}
