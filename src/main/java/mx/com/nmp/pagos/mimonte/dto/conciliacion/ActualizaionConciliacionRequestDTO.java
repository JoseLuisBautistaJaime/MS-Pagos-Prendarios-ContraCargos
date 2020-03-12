/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name ActualizaionConciliacionRequestDTO
 * @description Clase que encapsula el request de la información para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:05 hrs.
 * @version 0.1
 */
public class ActualizaionConciliacionRequestDTO implements Comparable<ActualizaionConciliacionRequestDTO> {

	private Long folio;
	private List<MovTransitoRequestDTO> movimientosTransito;
	private List<ComisionesRequestDTO> comisiones;

	public ActualizaionConciliacionRequestDTO() {
		super();
	}

	public ActualizaionConciliacionRequestDTO(Long folio, List<MovTransitoRequestDTO> movimientosTransito,
			List<ComisionesRequestDTO> comisiones) {
		super();
		this.folio = folio;
		this.movimientosTransito = movimientosTransito;
		this.comisiones = comisiones;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public List<MovTransitoRequestDTO> getMovimientosTransito() {
		return movimientosTransito;
	}

	public void setMovimientosTransito(List<MovTransitoRequestDTO> movimientosTransito) {
		this.movimientosTransito = movimientosTransito;
	}

	public List<ComisionesRequestDTO> getComisiones() {
		return comisiones;
	}

	public void setComisiones(List<ComisionesRequestDTO> comisiones) {
		this.comisiones = comisiones;
	}

	@Override
	public String toString() {
		return "ActualizaionConciliacionRequestDTO [folio=" + folio + ", movimientosTransito=" + movimientosTransito
				+ ", comisiones=" + comisiones + "]";
	}

	@Override
	public int compareTo(ActualizaionConciliacionRequestDTO o) {
		return 0;
	}

}
