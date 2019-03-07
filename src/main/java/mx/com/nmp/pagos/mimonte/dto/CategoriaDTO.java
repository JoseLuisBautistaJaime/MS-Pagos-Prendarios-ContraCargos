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

	private Long id;
	private String descripcion;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int compareTo(CategoriaDTO o) {
		return o.getId().compareTo(this.id);
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
