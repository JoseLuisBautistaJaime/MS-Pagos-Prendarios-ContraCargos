package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CodigoEstadoCuentaUpdtDTO Descripcion: Clase que encapsula la
 * informacion de un catalogo de codigo de estado de cuenta con el objeto
 * entidad resumido
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 07/03/2019 15:20 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaUpdtDTO implements Comparable<CodigoEstadoCuentaUpdtDTO> {

	private Long id;
	private String codigo;
	private Boolean status;
	private BaseEntidadDTO entidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaUpdtDTO() {
		super();
	}

	public CodigoEstadoCuentaUpdtDTO(Long id, String codigo, Boolean status, BaseEntidadDTO entidad,
			CategoriaDTO categoria) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.status = status;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public BaseEntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(BaseEntidadDTO entidad) {
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
		return "CodigoEstadoCuentaUpdtDTO [id=" + id + ", codigo=" + codigo + ", status=" + status + ", entidad="
				+ entidad + ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaUpdtDTO o) {
		return o.getId().compareTo(this.id);
	}

}
