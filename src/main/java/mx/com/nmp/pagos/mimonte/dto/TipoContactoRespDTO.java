package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: ContactoRespDTO Descripcion: Clase que encapsula la informacion perteneciente a los tipo de contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/03/2019 16:27 hrs.
 * @version 0.1
 */
public class TipoContactoRespDTO {
	
	private Integer id;
	
	private String descripcion;

	public TipoContactoRespDTO() {
		super();
	}

	public TipoContactoRespDTO(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoContactoRespDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
