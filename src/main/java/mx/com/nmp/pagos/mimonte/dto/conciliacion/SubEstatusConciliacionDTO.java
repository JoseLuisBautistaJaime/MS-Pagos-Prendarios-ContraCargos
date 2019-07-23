/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name SubEstatusConciliacionDTO
 * @description Clase que encapsula el request de SubEstatusConciliacionDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 16:43 hrs.
 * @version 0.1
 */
public class SubEstatusConciliacionDTO implements Comparable<SubEstatusConciliacionDTO>{
	
	private Long id;
	private String descripcion;

	public SubEstatusConciliacionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SubEstatusConciliacionDTO(Long id) {
		super();
		this.id = id;
	}

	public SubEstatusConciliacionDTO(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "SubEstatusConciliacionDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(SubEstatusConciliacionDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
