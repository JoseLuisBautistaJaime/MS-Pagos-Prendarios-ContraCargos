package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CategoriaDTO Descripcion: Clase que encapsula la informacion de un
 * catalogo categoria
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CategoriaDTO implements Comparable<CategoriaDTO> {

	private Integer id;
	private String nombre;
	private String descripcion;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int compareTo(CategoriaDTO o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
