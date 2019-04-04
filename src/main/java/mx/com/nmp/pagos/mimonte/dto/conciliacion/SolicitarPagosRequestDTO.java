/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name SolicitarPagosRequestDTO
 * @description Clase que encapsula el request de SolicitarPagosRequestDTO para
 *              solicitar pagos de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 17:31 hrs.
 * @version 0.1
 */
public class SolicitarPagosRequestDTO implements Comparable<SolicitarPagosRequestDTO> {

	private Long folio;
	private Long idMovimientos;

	public SolicitarPagosRequestDTO() {
		super();
	}

	public SolicitarPagosRequestDTO(Long folio, Long idMovimientos) {
		super();
		this.folio = folio;
		this.idMovimientos = idMovimientos;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Long getIdMovimientos() {
		return idMovimientos;
	}

	public void setIdMovimientos(Long idMovimientos) {
		this.idMovimientos = idMovimientos;
	}

	@Override
	public String toString() {
		return "SolicitarPagosRequestDTO [folio=" + folio + ", idMovimientos=" + idMovimientos + "]";
	}

	@Override
	public int compareTo(SolicitarPagosRequestDTO o) {
		return 0;
	}

}
