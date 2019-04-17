/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: ContactoRespDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a los contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/03/2019 16:06 hrs.
 * @version 0.1
 */
public class ContactoRespDTO extends AbstractCatalogoDTO {
	
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 4019940557415143754L;

	private String nombre;
	private String email;
	private TipoContactoRespDTO tipoContacto;

	
	
	public ContactoRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactoRespDTO(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		// TODO Auto-generated constructor stub
	}

	public ContactoRespDTO(String nombre, String email, TipoContactoRespDTO tipoContacto) {
		super();
		this.nombre = nombre;
		this.email = email;
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

	public TipoContactoRespDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContactoResDTO(TipoContactoRespDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	@Override
	public String toString() {
		return "ContactoRespDTO [nombre=" + nombre + ", email=" + email + ", tipoContacto=" + tipoContacto + ", id="
				+ id + ", estatus=" + estatus + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy
				+ ", description=" + description + ", shortDescription=" + shortDescription + "]";
	}

	

}
