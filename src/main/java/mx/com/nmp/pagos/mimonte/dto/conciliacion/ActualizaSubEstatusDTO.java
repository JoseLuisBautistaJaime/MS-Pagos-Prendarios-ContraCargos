/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ActualizaSubEstatusDTO
 * @description Clase que encapsula el request de ActualizaSubEstatusDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 21:19 hrs.
 * @version 0.1
 */
public class ActualizaSubEstatusDTO implements Comparable<ActualizaSubEstatusDTO>{
	
	private Long folio;
	private Integer idSubEstatus;
	private String descripcion;

	public ActualizaSubEstatusDTO() {
		super();
	}

	public ActualizaSubEstatusDTO(Long folio, Integer idSubEstatus, String descripcion) {
		super();
		this.folio = folio;
		this.idSubEstatus = idSubEstatus;
		this.descripcion = descripcion;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Integer getIdSubEstatus() {
		return idSubEstatus;
	}

	public void setIdSubEstatus(Integer idSubEstatus) {
		this.idSubEstatus = idSubEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ActualizaSubEstatusDTO [folio=" + folio + ", idSubEstatus=" + idSubEstatus + ", descripcion="
				+ descripcion + "]";
	}

	@Override
	public int compareTo(ActualizaSubEstatusDTO o) {
		return 0;
	}

}
