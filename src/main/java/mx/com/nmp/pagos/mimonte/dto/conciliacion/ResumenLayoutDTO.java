/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name ResumenLayoutDTO
 * @description Clase que encapsula la informacion del resumen de un layout
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 30/03/2019 00:00:00 hrs.
 * @version 0.1
 */
public class ResumenLayoutDTO {

	private BigDecimal totalCargos;
	private BigDecimal totalAbonos;
	private List<ResumenLayoutTipoDTO> detalle;


	public ResumenLayoutDTO() {
		super();
		this.totalCargos = new BigDecimal(0);
		this.totalAbonos = new BigDecimal(0);
	}

	public void add(BigDecimal monto) {
		if (monto != null) {
			if (monto.compareTo(new BigDecimal(0)) < 0) {
				this.totalCargos = this.totalCargos.add(monto);
			}
			else {
				this.totalAbonos = this.totalAbonos.add(monto);
			}
		}
	}

	public BigDecimal getTotalCargos() {
		return totalCargos;
	}

	public void setTotalCargos(BigDecimal totalCargos) {
		this.totalCargos = totalCargos;
	}

	public BigDecimal getTotalAbonos() {
		return totalAbonos;
	}

	public void setTotalAbonos(BigDecimal totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public List<ResumenLayoutTipoDTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<ResumenLayoutTipoDTO> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "ResumenLayoutDTO [totalCargos=" + totalCargos + ", totalAbonos=" + totalAbonos + ", detalle=" + detalle
				+ "]";
	}

}