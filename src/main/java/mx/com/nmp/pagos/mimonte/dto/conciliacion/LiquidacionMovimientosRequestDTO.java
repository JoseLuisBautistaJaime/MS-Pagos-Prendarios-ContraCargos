/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name LiquidacionMovimientosRequestDTO
 * @description Clase que encapsula el request de
 *              LiquidacionMovimientosRequestDTO para la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:05 hrs.
 * @version 0.1
 */
public class LiquidacionMovimientosRequestDTO implements Comparable<LiquidacionMovimientosRequestDTO> {

	private Long folio;
	private List<MovimientosDTO> movimientos;

	public LiquidacionMovimientosRequestDTO() {
		super();
	}

	public LiquidacionMovimientosRequestDTO(Long folio, List<MovimientosDTO> movimientos) {
		super();
		this.folio = folio;
		this.movimientos = movimientos;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public List<MovimientosDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientosDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "LiquidacionMovimientosRequestDTO [folio=" + folio + ", movimientos=" + movimientos + "]";
	}

	@Override
	public int compareTo(LiquidacionMovimientosRequestDTO o) {
		return 0;
	}

}
