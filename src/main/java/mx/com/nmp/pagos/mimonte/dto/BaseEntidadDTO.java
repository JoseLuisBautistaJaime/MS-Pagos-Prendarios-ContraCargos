package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: BaseEntidadDTO Descripcion: Clase que encapsula la informacion de un
 * objeto de tipo BaseEntidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
public class BaseEntidadDTO implements Comparable<BaseEntidadDTO> {

	private Long id;
	private String nombre;

	public BaseEntidadDTO() {
		super();
	}

	public BaseEntidadDTO(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public int compareTo(BaseEntidadDTO o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "BaseEntidadDTO [id=" + id + ", nombre=" + nombre + "]";
	}

}
