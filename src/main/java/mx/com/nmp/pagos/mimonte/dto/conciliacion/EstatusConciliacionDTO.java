/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusConciliacionDTO
 * @description Clase que encapsula la informacion del estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:22 hrs.
 * @version 0.1
 */
public class EstatusConciliacionDTO implements Comparable<EstatusConciliacionDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 6207923110827146624L;

	private Integer id;
	private String descripcion;
	private Boolean estatus;
	
	public EstatusConciliacionDTO() {
		super();
	}
	
	public EstatusConciliacionDTO(Integer id) {
		super();
		this.id = id;
	}

	public EstatusConciliacionDTO(Integer id, String descripcion, Boolean estatus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "EstatusConciliacionDTO [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(EstatusConciliacionDTO o) {
		return 0;
	}
}
