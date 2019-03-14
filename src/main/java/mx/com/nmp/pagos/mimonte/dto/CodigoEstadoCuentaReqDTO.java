package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CodigoEstadoCuentaReqDTO Descripcion: Clase que encapsula la
 * unformacion de un catalogo de codigo de estado de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaReqDTO implements Comparable<CodigoEstadoCuentaReqDTO> {

	private String leyenda;
	private Long id;
	private Boolean status;
	private EntidadReqDTO entidad;
	private CategoriaReqDTO categoria;

	public CodigoEstadoCuentaReqDTO() {
		super();
	}

	public CodigoEstadoCuentaReqDTO(String leyenda, Long id, Boolean status, EntidadReqDTO entidad,
			CategoriaReqDTO categoria) {
		super();
		this.leyenda = leyenda;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public EntidadReqDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadReqDTO entidad) {
		this.entidad = entidad;
	}

	public CategoriaReqDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaReqDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaReqDTO [leyenda=" + leyenda + ", id=" + id + ", status=" + status + ", entidadReqDTO="
				+ entidad + ", categoriaReqDTO=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
