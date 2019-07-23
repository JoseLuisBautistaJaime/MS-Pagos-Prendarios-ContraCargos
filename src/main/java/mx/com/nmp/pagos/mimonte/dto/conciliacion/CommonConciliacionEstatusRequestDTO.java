/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name CommonConciliacionEstatusRequestDTO
 * @description Clase que encapsula la informacion relacionada con un request
 *              con paramatros comunes
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/05/2019 17:33 hrs.
 * @version 0.1
 */
public class CommonConciliacionEstatusRequestDTO implements Comparable<CommonConciliacionEstatusRequestDTO> {

	private Integer folio;
	private Boolean estatus;

	public CommonConciliacionEstatusRequestDTO() {
		super();
	}

	public CommonConciliacionEstatusRequestDTO(Integer folio, Boolean estatus) {
		super();
		this.folio = folio;
		this.estatus = estatus;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "CommonConciliacionEstatusRequestDTO [folio=" + folio + ", resultados=" + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(CommonConciliacionEstatusRequestDTO o) {
		return o.folio.compareTo(this.folio);
	}

}
