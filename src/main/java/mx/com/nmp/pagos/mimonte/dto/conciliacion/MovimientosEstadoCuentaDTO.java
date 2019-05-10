/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientosEstadoCuentaDTO
 * @description Clase que encapsula la informacion relacionada con Movimientos
 *              de estados de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 09:25 hrs.
 * @version 0.1
 */
public class MovimientosEstadoCuentaDTO implements Comparable<MovimientosEstadoCuentaDTO> {

	private Long total;
	private List<MovimientoEstadoCuentaDTO> movimientos;

	public MovimientosEstadoCuentaDTO() {
		super();
	}

	public MovimientosEstadoCuentaDTO(Long total, List<MovimientoEstadoCuentaDTO> movimientos) {
		super();
		this.total = total;
		this.movimientos = movimientos;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<MovimientoEstadoCuentaDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoEstadoCuentaDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "MovimientosEstadoCuentaDTO [total=" + total + ", movimientos=" + movimientos + "]";
	}

	@Override
	public int compareTo(MovimientosEstadoCuentaDTO arg0) {
		return arg0.total.compareTo(this.total);
	}
}
