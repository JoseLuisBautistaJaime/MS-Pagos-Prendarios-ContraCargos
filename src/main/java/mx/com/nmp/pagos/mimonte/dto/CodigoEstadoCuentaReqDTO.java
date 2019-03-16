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

	private String codigo;
	private Long id;
	private Boolean estatus;
	private EntidadReqDTO entidad;
	private CategoriaReqDTO categoria;

	public CodigoEstadoCuentaReqDTO() {
		super();
	}

	public CodigoEstadoCuentaReqDTO(String codigo, Long id, Boolean estatus, EntidadReqDTO entidad,
			CategoriaReqDTO categoria) {
		super();
		this.codigo = codigo;
		this.id = id;
		this.estatus = estatus;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
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
		return "CodigoEstadoCuentaReqDTO [codigo=" + codigo + ", id=" + id + ", status=" + estatus + ", entidad="
				+ entidad + ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
