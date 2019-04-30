/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientoTransaccionalRequestDTO
 * @description Clase que encapsula la informacion relacionada con un respuetsa
 *              a el alta de MovimientosProveedor
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 16:27 hrs.
 * @version 0.1
 */
public class MovimientoTransaccionalListRequestDTO implements Comparable<MovimientoTransaccionalListRequestDTO> {

	private Integer folio;
	private List<MovimientoTransaccionalRequestDTO> movimientos;

	public MovimientoTransaccionalListRequestDTO() {
		super();
	}

	public MovimientoTransaccionalListRequestDTO(Integer folio, List<MovimientoTransaccionalRequestDTO> movimientos) {
		super();
		this.folio = folio;
		this.movimientos = movimientos;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public List<MovimientoTransaccionalRequestDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoTransaccionalRequestDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "MovimientoTransaccionalListRequestDTO [folio=" + folio + ", movimientos=" + movimientos + "]";
	}

	@Override
	public int compareTo(MovimientoTransaccionalListRequestDTO o) {
		return 0;
	}

}
