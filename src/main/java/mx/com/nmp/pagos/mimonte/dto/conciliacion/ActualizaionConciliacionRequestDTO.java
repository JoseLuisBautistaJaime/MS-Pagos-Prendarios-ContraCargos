/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

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
	private MovTransitoRequestDTO movimientosTransito;
	private ComisionesRequestDTO comisiones;

	public ActualizaionConciliacionRequestDTO() {
		super();
	}

	public ActualizaionConciliacionRequestDTO(Long folio, MovTransitoRequestDTO movimientosTransito,
			ComisionesRequestDTO comisiones) {
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

	public MovTransitoRequestDTO getMovimientosTransito() {
		return movimientosTransito;
	}

	public void setMovimientosTransito(MovTransitoRequestDTO movimientosTransito) {
		this.movimientosTransito = movimientosTransito;
	}

	public ComisionesRequestDTO getComisiones() {
		return comisiones;
	}

	public void setComisiones(ComisionesRequestDTO comisiones) {
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
