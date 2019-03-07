package mx.com.nmp.pagos.mimonte.model;

/**
 * Nombre: BaseEntidad Descripcion: Clase que encapsula la informacion de una
 * entidad de catalogo de tipo BaseEntidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
public class BaseEntidad implements Comparable<BaseEntidad> {

	private Long id;
	private String nombre;

	public BaseEntidad() {
		super();
	}

	public BaseEntidad(Long id, String nombre) {
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
	public int compareTo(BaseEntidad o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "BaseEntidad [id=" + id + ", nombre=" + nombre + "]";
	}

}
