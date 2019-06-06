/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name CommonConciliacionRequestDTO
 * @description Clase que encapsula la informacion relacionada con un request
 *              con paramatros comunes
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 15:13 hrs.
 * @version 0.1
 */
public class CommonConciliacionRequestDTO implements Comparable<CommonConciliacionRequestDTO> {

	private Integer folio;

	public CommonConciliacionRequestDTO() {
		super();
	}

	public CommonConciliacionRequestDTO(Integer folio) {
		super();
		this.folio = folio;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "CommonConciliacionRequestDTO [folio=" + folio + "]";
	}

	@Override
	public int compareTo(CommonConciliacionRequestDTO o) {
		return o.folio.compareTo(this.folio);
	}

}
