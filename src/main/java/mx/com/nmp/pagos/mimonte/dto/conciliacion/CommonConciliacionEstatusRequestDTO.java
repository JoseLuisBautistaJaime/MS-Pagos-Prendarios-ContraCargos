package mx.com.nmp.pagos.mimonte.dto.conciliacion;

public class CommonConciliacionEstatusRequestDTO {

	private Integer folio;
	private Boolean estatus;

	public CommonConciliacionEstatusRequestDTO() {
		super();
	}

	public CommonConciliacionEstatusRequestDTO(Integer folio, Boolean estatus) {
		super();
		this.folio = folio;
		this.estatus = estatus;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "CommonConciliacionEstatusRequestDTO [folio=" + folio + ", estatus=" + estatus + "]";
	}

}
