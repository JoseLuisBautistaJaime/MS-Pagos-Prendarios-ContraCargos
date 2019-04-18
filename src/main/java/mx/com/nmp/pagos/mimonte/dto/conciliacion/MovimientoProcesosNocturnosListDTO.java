/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
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

	private Integer total;
	private List<MovimientoProcesosNocturnosDTO> movimientos;

	public MovimientoProcesosNocturnosListDTO() {
		super();
	}

	public MovimientoProcesosNocturnosListDTO(Integer total, List<MovimientoProcesosNocturnosDTO> movimientos) {
		super();
		this.total = total;
		this.movimientos = movimientos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<MovimientoProcesosNocturnosDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoProcesosNocturnosDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "MovimientoProcesosNocturnosListDTO [total=" + total + ", movimientos=" + movimientos + "]";
	}

}
