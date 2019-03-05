package mx.com.nmp.pagos.mimonte.dto;

public class BaseCodigoDTO implements Comparable<BaseCodigoDTO> {

	private Long id;
	private Boolean estatus;

	public BaseCodigoDTO() {
		super();
	}

	public BaseCodigoDTO(Long id, Boolean estatus) {
		super();
		this.id = id;
		this.estatus = estatus;
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

	@Override
	public int compareTo(BaseCodigoDTO o) {
		return o.getId().compareTo(this.id);
	}

	@Override
	public String toString() {
		return "BaseCodigoDTO [id=" + id + ", estatus=" + estatus + "]";
	}

}
