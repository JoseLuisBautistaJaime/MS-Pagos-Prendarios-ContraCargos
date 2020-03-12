package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CategoriaReqDTO Descripcion: Clase que encapsula la unformacion de un
 * catalogo de categoria
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class CategoriaReqDTO implements Comparable<CategoriaReqDTO> {

	private Long id;

	public CategoriaReqDTO() {
		super();
	}

	public CategoriaReqDTO(Long id) {
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
		return "CategoriaReqDTO [id=" + id + "]";
	}

	@Override
	public int compareTo(CategoriaReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
