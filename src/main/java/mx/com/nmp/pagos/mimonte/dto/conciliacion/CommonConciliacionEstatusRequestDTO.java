package mx.com.nmp.pagos.mimonte.dto.conciliacion;

public class CommonConciliacionEstatusRequestDTO {

	private Integer folio;
	private Integer resultados;
	private Integer pagina;
	private Boolean estatus;

	public CommonConciliacionEstatusRequestDTO() {
		super();
	}

	public CommonConciliacionEstatusRequestDTO(Integer folio, Integer resultados, Integer pagina, Boolean estatus) {
		super();
		this.folio = folio;
		this.resultados = resultados;
		this.pagina = pagina;
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

	public Integer getResultados() {
		return resultados;
	}

	public void setResultados(Integer resultados) {
		this.resultados = resultados;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	@Override
	public String toString() {
		return "CommonConciliacionEstatusRequestDTO [folio=" + folio + ", resultados=" + resultados + ", pagina="
				+ pagina + ", estatus=" + estatus + "]";
	}

}
