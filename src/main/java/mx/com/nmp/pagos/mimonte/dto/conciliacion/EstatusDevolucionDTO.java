/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusDevolucionDTO
 * @description Clase que encapsula la informacion de la devolición de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:12 hrs.
 * @version 0.1
 */
public class EstatusDevolucionDTO implements Comparable<EstatusDevolucionDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 3417477420340701274L;

	private Integer id;
	private String descripcion;
	private Boolean estatus;

	public EstatusDevolucionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstatusDevolucionDTO(Integer id, String descripcion, Boolean estatus) {
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
		return "EstatusDevolucionDTO [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(EstatusDevolucionDTO o) {
		return 0;
	}

}
