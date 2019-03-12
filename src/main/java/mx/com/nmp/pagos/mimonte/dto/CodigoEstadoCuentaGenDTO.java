package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: CodigoEstadoCuentaGenDTO Descripcion: Clase que encapsula la
 * informacion de un catalogo de codigo de estado de cuenta generico (que hereda
 * de AsbtractCatalogoDTO)
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 06/03/2019 12:48 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaGenDTO extends AbstractCatalogoDTO {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 4252462570216032264L;

	private String leyenda;
	private EntidadDTO entidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaGenDTO() {
		super();
	}

	public CodigoEstadoCuentaGenDTO(String leyenda, EntidadDTO entidad, CategoriaDTO categoria) {
		super();
		this.leyenda = leyenda;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuentaGenDTO(String leyenda, EntidadDTO entidad, CategoriaDTO categoria, Long id,
			Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy,
			String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.leyenda = leyenda;
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

	@Override
	public String toString() {
		return "CodigoEstadoCuentaGenDTO [leyenda=" + leyenda + ", entidad=" + entidad + ", categoria=" + categoria
				+ "]";
	}

}
