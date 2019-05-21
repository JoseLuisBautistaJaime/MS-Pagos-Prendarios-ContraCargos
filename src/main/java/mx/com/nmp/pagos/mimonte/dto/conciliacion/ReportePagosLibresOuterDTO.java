/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @name ReportePagosLibresOuterDTO
 * @description Clase que encapsula la informacion de una respuesta de reporte
 *              de pagos en linea y adiciona un total de movimientos y un monto
 *              total
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/05/2019 18:01 hrs.
 * @version 0.1
 */
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

	@Override
	public String toString() {
		return "ReportePagosLibresOuterDTO [movimientos=" + movimientos + ", totalMovimientos=" + totalMovimientos
				+ ", montoTotal=" + montoTotal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(movimientos, totalMovimientos, montoTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ReportePagosLibresOuterDTO))
			return false;

		final ReportePagosLibresOuterDTO other = (ReportePagosLibresOuterDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

}
