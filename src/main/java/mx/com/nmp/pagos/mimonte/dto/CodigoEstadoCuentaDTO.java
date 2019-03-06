package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: CodigoEstadoCuentaDTO Descripcion: Clase que encapsula la informacion
 * de un catalogo de codigo de estado de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaDTO implements Comparable<CodigoEstadoCuentaDTO> {

	private Long id;
	private String leyenda;
	private Boolean status;
	private EntidadDTO entidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaDTO() {
		super();
	}

	public CodigoEstadoCuentaDTO(Long id, String leyenda, Boolean status, EntidadDTO entidad, CategoriaDTO categoria) {
		super();
		this.id = id;
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

	@Override
	public int compareTo(CodigoEstadoCuentaDTO o) {
		return o.getLeyenda().compareTo(this.leyenda);
	}

}
