/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusDevolucionDTO
 * @description Clase que encapsula la informacion de un objeto de estatus de
 *              devolucion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 12:12 hrs.
 * @version 0.1
 */
public class EstatusDevolucionDTO implements Comparable<EstatusDevolucionDTO> {

	private Integer id;
	private String descripcion;
	private String descripcionCorta;
	private Boolean estatus;

	public EstatusDevolucionDTO() {
		super();
	}

	public EstatusDevolucionDTO(Integer id, String descripcion, String descripcionCorta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}

	public EstatusDevolucionDTO(Integer id, String descripcion, Boolean estatus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
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

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	@Override
	public String toString() {
		return "EstatusDevolucionDTO [id=" + id + ", descripcion=" + descripcion + ", descripcionCorta="
				+ descripcionCorta + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(EstatusDevolucionDTO o) {
		return o.id.compareTo(this.id);
	}

}
