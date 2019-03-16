package mx.com.nmp.pagos.mimonte.dto;

public class BaseCodigoDTO implements Comparable<BaseCodigoDTO> {

	private Long id;
	private Boolean estatus;
	private String codigo;

	public BaseCodigoDTO() {
		super();
	}

	public BaseCodigoDTO(Long id, Boolean estatus, String codigo) {
		super();
		this.id = id;
		this.estatus = estatus;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int compareTo(BaseCodigoDTO o) {
		return o.getId().compareTo(this.id);
	}

	@Override
	public String toString() {
		return "BaseCodigoDTO [id=" + id + ", estatus=" + estatus + ", codigo=" + codigo + "]";
	}

}
