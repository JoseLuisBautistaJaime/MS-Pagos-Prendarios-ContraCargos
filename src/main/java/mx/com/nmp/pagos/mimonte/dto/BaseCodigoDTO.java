package mx.com.nmp.pagos.mimonte.dto;

public class BaseCodigoDTO implements Comparable<BaseCodigoDTO> {

	private Long id;
	private Boolean estatus;
	private String leyenda;

	public BaseCodigoDTO() {
		super();
	}

	public BaseCodigoDTO(Long id, Boolean estatus, String leyenda) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.leyenda = leyenda;
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

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	@Override
	public int compareTo(BaseCodigoDTO o) {
		return o.getId().compareTo(this.id);
	}

	@Override
	public String toString() {
		return "BaseCodigoDTO [id=" + id + ", estatus=" + estatus + ", leyenda=" + leyenda + "]";
	}

}
