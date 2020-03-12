/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * 
 * @name ContactoReqSaveDTO
 * @description Clase que encapsula la informacion de un objeto Contacto para
 *              alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @date 16/04/2019 13:05 hrs.
 * @version 0.1
 */
public class ContactoReqSaveDTO implements Comparable<ContactoReqSaveDTO> {

	private Long id;

	public ContactoReqSaveDTO() {
		super();
	}

	public ContactoReqSaveDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContactoReqDTO [id=" + id + "]";
	}

	@Override
	public int compareTo(ContactoReqSaveDTO o) {
		return o.id.compareTo(this.id);
	}

}
