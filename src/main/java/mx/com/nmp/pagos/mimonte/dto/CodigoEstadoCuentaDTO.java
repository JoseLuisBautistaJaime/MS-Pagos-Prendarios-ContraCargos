package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

/**
 * @name CodigoEstadoCuentaDTO
 * @description Clase que encapsula la informacion de un catalogo de codigo de
 *              estado de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaDTO extends AbstractCatalogoDTO implements Comparable<CodigoEstadoCuentaDTO> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -32478678397212959L;

	private String codigo;
	private Set<EntidadDTO> entidades;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaDTO() {
		super();
	}

	public CodigoEstadoCuentaDTO(String codigo, Set<EntidadDTO> entidades, CategoriaDTO categoria) {
		super();
		this.codigo = codigo;
		this.entidades = entidades;
		this.categoria = categoria;
	}

	public CodigoEstadoCuentaDTO(String codigo, Set<EntidadDTO> entidades, CategoriaDTO categoria, Long id,
			Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy,
			String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.codigo = codigo;
		this.entidades = entidades;
		this.categoria = categoria;
	}

	public Set<EntidadDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(Set<EntidadDTO> entidades) {
		this.entidades = entidades;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaDTO [codigo=" + codigo + ", entidades=" + entidades + ", categoria=" + categoria
				+ "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaDTO o) {
		return o.codigo.compareTo(this.codigo);
	}

}
