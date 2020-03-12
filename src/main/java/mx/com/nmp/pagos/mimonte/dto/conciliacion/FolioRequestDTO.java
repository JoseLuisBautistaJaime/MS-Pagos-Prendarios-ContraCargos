/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name FolioRequestDTO
 * @description Clase que encapsula los ids de los movimientos de devoluciones
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 17:27 hrs.
 * @version 0.1
 */
public class FolioRequestDTO implements Comparable<FolioRequestDTO>{
	
	private Long folio;

	public FolioRequestDTO() {
		super();
	}

	public FolioRequestDTO(Long folio) {
		super();
		this.folio = folio;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "FolioRequestDTO [folio=" + folio + "]";
	}

	@Override
	public int compareTo(FolioRequestDTO o) {
		return 0;
	}

}
