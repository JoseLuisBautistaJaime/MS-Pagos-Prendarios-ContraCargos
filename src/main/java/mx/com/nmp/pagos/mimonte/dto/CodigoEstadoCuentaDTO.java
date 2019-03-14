package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: CodigoEstadoCuentaDTO Descripcion: Clase que encapsula la informacion
 * de un catalogo de codigo de estado de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaDTO extends AbstractCatalogoDTO implements Comparable<CodigoEstadoCuentaDTO> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -32478678397212959L;

	private String leyenda;
	private Boolean status;
	private EntidadDTO entidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaDTO() {
		super();
	}

	public CodigoEstadoCuentaDTO(String leyenda, Boolean status, EntidadDTO entidad, CategoriaDTO categoria) {
		super();
		this.leyenda = leyenda;
		this.status = status;
		this.categoria = categoria;
	}

	public CodigoEstadoCuentaDTO(String leyenda, Boolean status, EntidadDTO entidad, CategoriaDTO categoria, Long id,
			Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy,
			String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.leyenda = leyenda;
		this.status = status;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaDTO [leyenda=" + leyenda + ", status=" + status + ", entidades=" + entidad
				+ ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaDTO o) {
		return o.getLeyenda().compareTo(this.leyenda);
	}

}
