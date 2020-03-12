/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;

/**
 * @name ComisionesTransRealDTO
 * @description Clase que encapsula la información de las comisiones.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 12/04/2019 23:12 hrs.
 * @version 0.1
 */
public class ComisionesTransRealDTO implements Comparable<ComisionesTransRealDTO> {

	private BigDecimal comision;
	private BigDecimal ivaComision;
	private BigDecimal totalComision;

	public ComisionesTransRealDTO() {
		super();
	}

	public ComisionesTransRealDTO(BigDecimal comision, BigDecimal ivaComision, BigDecimal totalComision) {
		super();
		this.comision = comision;
		this.ivaComision = ivaComision;
		this.totalComision = totalComision;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getIvaComision() {
		return ivaComision;
	}

	public void setIvaComision(BigDecimal ivaComision) {
		this.ivaComision = ivaComision;
	}

	public BigDecimal getTotalComision() {
		return totalComision;
	}

	public void setTotalComision(BigDecimal totalComision) {
		this.totalComision = totalComision;
	}

	@Override
	public String toString() {
		return "ComisionesTransRealDTO [comision=" + comision + ", ivaComision=" + ivaComision + ", totalComision="
				+ totalComision + "]";
	}

	@Override
	public int compareTo(ComisionesTransRealDTO o) {
		return 0;
	}

}
