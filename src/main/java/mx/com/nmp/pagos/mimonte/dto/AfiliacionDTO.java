package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: AfiliacionDTO
 * Descripcion: Clase que encapsula la informacion
 * perteneciente a un tipo de Afiliacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:39 hrs.
 * @version 0.1
 */
public class AfiliacionDTO {

	private Integer id;
	private String descripcion;

	public AfiliacionDTO() {
		super();
	}

	public AfiliacionDTO(Integer id, String descripcion) {
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
		return "AfiliacionDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
