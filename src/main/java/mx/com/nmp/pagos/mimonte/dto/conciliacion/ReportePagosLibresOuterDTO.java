package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.List;

public class ReportePagosLibresOuterDTO {

	private List<ReportePagosLibresDTO> movimientos;
	private Integer totalMovimientos;
	private BigDecimal montoTotal;

	public List<ReportePagosLibresDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<ReportePagosLibresDTO> movimientos) {
		this.movimientos = movimientos;
	}

	public Integer getTotalMovimientos() {
		return totalMovimientos;
	}

	public void setTotalMovimientos(Integer totalMovimientos) {
		this.totalMovimientos = totalMovimientos;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

}
