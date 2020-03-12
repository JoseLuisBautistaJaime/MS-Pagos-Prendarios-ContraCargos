/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientoProcesosNocturnosListDTO
 * @description Clase que encapsula la informacion relacionada con un respuetsa
 *              a el alta de MovimientoProcesosNocturnosDTO en una lista
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/04/2019 10:34 hrs.
 * @version 0.1
 */
public class MovimientoProcesosNocturnosListDTO {

	private Long total;
	private List<MovimientoMidasDTO> movimientos;

	public MovimientoProcesosNocturnosListDTO() {
		super();
	}

	public MovimientoProcesosNocturnosListDTO(Long total, List<MovimientoMidasDTO> movimientos) {
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

	public List<MovimientoMidasDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoMidasDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "MovimientoProcesosNocturnosListDTO [total=" + total + ", movimientos=" + movimientos + "]";
	}

}
