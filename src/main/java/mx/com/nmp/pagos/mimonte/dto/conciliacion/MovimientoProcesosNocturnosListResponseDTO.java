/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientoProcesosNocturnosDTO
 * @description Clase que encapsula la informacion relacionada con una peticion
 *              de alta de movimientos nocturnos
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 16:36 hrs.
 * @version 0.1
 */
public class MovimientoProcesosNocturnosListResponseDTO
		implements Comparable<MovimientoProcesosNocturnosListResponseDTO> {

	private Integer folio;
	private List<MovimientoProcesosNocturnosResponseDTO> movimientos;

	public MovimientoProcesosNocturnosListResponseDTO() {
		super();
	}

	public MovimientoProcesosNocturnosListResponseDTO(Integer folio,
			List<MovimientoProcesosNocturnosResponseDTO> movimientos) {
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

	public List<MovimientoProcesosNocturnosResponseDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoProcesosNocturnosResponseDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "MovimientoProcesosNocturnosListResponseDTO [folio=" + folio + ", movimientos=" + movimientos + "]";
	}

	@Override
	public int compareTo(MovimientoProcesosNocturnosListResponseDTO o) {
		return 0;
	}

}
