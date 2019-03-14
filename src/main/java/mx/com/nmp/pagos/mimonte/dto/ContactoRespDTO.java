package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: ContactoRespDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a los contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/03/2019 16:06 hrs.
 * @version 0.1
 */
public class ContactoRespDTO extends AbstractCatDTO {

	private String nombre;

	private String email;

	private String descripcion;

	private TipoContactoRespDTO tipoContactoRespDTO;

	public ContactoRespDTO() {
		super();
	}

	public ContactoRespDTO(String nombre, String email, String descripcion, TipoContactoRespDTO tipoContactoRespDTO,
			Long id, Boolean estatus) {
		super(id, estatus);
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

	public TipoContactoRespDTO getTipoContactoRespDTO() {
		return tipoContactoRespDTO;
	}

	public void setTipoContactoResDTO(TipoContactoRespDTO tipoContactoRespDTO) {
		this.tipoContactoRespDTO = tipoContactoRespDTO;
	}

	@Override
	public String toString() {
		return "ContactoRespDTO [nombre=" + nombre + ", email=" + email + ", descripcion=" + descripcion
				+ ", TipoContactoRespDTO=" + tipoContactoRespDTO + ", id=" + id + ", estatus=" + estatus + "]";
	}

}
