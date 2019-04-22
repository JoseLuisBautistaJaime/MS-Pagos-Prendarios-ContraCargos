/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;

/**
 * @name ComisionesTransaccionesOperacionDTO
 * @description Clase que encapsula la información de las proyecciones.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 12/04/2019 23:02 hrs.
 * @version 0.1
 */
public class ComisionesTransaccionesOperacionDTO implements Comparable<ComisionesTransaccionesOperacionDTO> {

	private String operacion;
	private Long transacciones;
	private BigDecimal comision;
	private BigDecimal ivaComision;
	private BigDecimal totalComision;

	public ComisionesTransaccionesOperacionDTO() {
		super();
	}

	public ComisionesTransaccionesOperacionDTO(String operacion, Long transacciones, BigDecimal comision,
			BigDecimal ivaComision, BigDecimal totalComision) {
		super();
		this.operacion = operacion;
		this.transacciones = transacciones;
		this.comision = comision;
		this.ivaComision = ivaComision;
		this.totalComision = totalComision;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Long getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Long transacciones) {
		this.transacciones = transacciones;
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
		return "ComisionesTransaccionesOperacionDTO [operacion=" + operacion + ", transacciones=" + transacciones
				+ ", comision=" + comision + ", ivaComision=" + ivaComision + ", totalComision=" + totalComision + "]";
	}

	@Override
	public int compareTo(ComisionesTransaccionesOperacionDTO o) {
		return 0;
	}

}
