/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ConciliacionEjecucionDTO
 * @description Clase que encapsula la información de la conciliación asociada a la
 *              ejecución del proceso.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 10/11/2021 16:22 hrs.
 * @version 0.1
 */


public class ConciliacionEjecucionDTO implements Comparable<ConciliacionEjecucionDTO> {

	private Long folioConciliacion; // Folio especifico por corresponsal to_conciliacion.folio
	private Long folio; // Identificador unico de la conciliacion to_conciliacion.id

	public ConciliacionEjecucionDTO() {
		super();
	}

	public ConciliacionEjecucionDTO(Long folioConciliacion, Long folio) {
		super();
		this.folioConciliacion = folioConciliacion;
		this.folio = folio;
	}

	public Long getFolioConciliacion() {
		return folioConciliacion;
	}

	public void setFolioConciliacion(Long folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "ConciliacionEjecucionDTO [folio=" + folio + ", folioConciliacion=" + folioConciliacion +"]";
	}

	@Override
	public int compareTo(ConciliacionEjecucionDTO o) {
		return 0;
	}
}
