/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ActualizaIdPsRequestDTO
 * @description Clase que encapsula la información del request del objeto ActualizaIdPsRequestDTO.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:08 hrs.
 * @version 0.1
 */
public class ActualizaIdPsRequestDTO implements Comparable<ActualizaIdPsRequestDTO>{
	
	private Long folio;
	private Integer idPeopleSoft;
	private Integer idEstatusConciliacion;

	public ActualizaIdPsRequestDTO() {
		super();
	}

	public ActualizaIdPsRequestDTO(Long folio, Integer idPeopleSoft, Integer idEstatusConciliacion) {
		super();
		this.folio = folio;
		this.idPeopleSoft = idPeopleSoft;
		this.idEstatusConciliacion = idEstatusConciliacion;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Integer getIdPeopleSoft() {
		return idPeopleSoft;
	}

	public void setIdPeopleSoft(Integer idPeopleSoft) {
		this.idPeopleSoft = idPeopleSoft;
	}

	public Integer getIdEstatusConciliacion() {
		return idEstatusConciliacion;
	}

	public void setIdEstatusConciliacion(Integer idEstatusConciliacion) {
		this.idEstatusConciliacion = idEstatusConciliacion;
	}

	@Override
	public String toString() {
		return "ActualizaIdPsRequestDTO [folio=" + folio + ", idPeopleSoft=" + idPeopleSoft + ", idEstatusConciliacion="
				+ idEstatusConciliacion + "]";
	}

	@Override
	public int compareTo(ActualizaIdPsRequestDTO o) {
		return 0;
	}

}
