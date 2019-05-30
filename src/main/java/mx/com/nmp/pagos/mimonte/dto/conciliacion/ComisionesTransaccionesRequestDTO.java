/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name ComisionesTransaccionesRequestDTO
 * @description Clase que encapsula la información de las comisiones de una
 *              transacción.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 12/04/2019 20:54 hrs.
 * @version 0.1
 */
public class ComisionesTransaccionesRequestDTO implements Comparable<ComisionesTransaccionesRequestDTO> {

	private Date fechaDesde;
	private Date fechaHasta;
	private BigDecimal comision;

	public ComisionesTransaccionesRequestDTO() {
		super();
	}

	public ComisionesTransaccionesRequestDTO(Date fechaDesde, Date fechaHasta, BigDecimal comision) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.comision = comision;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaDesde, fechaHasta, comision);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ComisionesTransaccionesRequestDTO))
			return false;

		final ComisionesTransaccionesRequestDTO other = (ComisionesTransaccionesRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(ComisionesTransaccionesRequestDTO o) {
		return 0;
	}

	@Override
	public String toString() {
		return "ComisionesTransaccionesRequestDTO [fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta
				+ ", comision=" + comision + "]";
	}

}
