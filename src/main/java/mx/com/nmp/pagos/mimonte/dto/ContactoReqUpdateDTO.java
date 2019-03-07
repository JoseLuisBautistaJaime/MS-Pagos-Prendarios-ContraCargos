package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: Contacto Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 06/03/2019 21:04 hrs.
 * @version 0.1
 */
public class ContactoReqUpdateDTO {
	
	private Integer id;
	
	private Boolean estatus;
	
	private String nombre;
	
	private String email;
	
	private String descripcion;
	
	private TipoContactoReqDTO tipoContactoReqDTO;
	
	private String lastModifiedBy;

	public ContactoReqUpdateDTO() {
		super();
	}

	public ContactoReqUpdateDTO(Integer id, Boolean estatus, String nombre, String email, String descripcion,
			TipoContactoReqDTO tipoContactoReqDTO, String lastModifiedBy) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.nombre = nombre;
		this.email = email;
		this.descripcion = descripcion;
		this.tipoContactoReqDTO = tipoContactoReqDTO;
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoContactoReqDTO getTipoContactoReqDTO() {
		return tipoContactoReqDTO;
	}

	public void setTipoContactoReqDTO(TipoContactoReqDTO tipoContactoReqDTO) {
		this.tipoContactoReqDTO = tipoContactoReqDTO;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "ContactoReqUpdateDTO [id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", email=" + email + ", descripcion=" + descripcion
				+ ", tipoContactoReqDTO=" + tipoContactoReqDTO + " , lastModifiedBy=" + lastModifiedBy + "]";
	}

}
