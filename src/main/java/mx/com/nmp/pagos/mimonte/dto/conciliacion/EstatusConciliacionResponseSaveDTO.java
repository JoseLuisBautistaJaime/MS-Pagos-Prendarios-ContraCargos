/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;

/**
 * @name EstatusConciliacionResponseSaveDTO
 * @description Clase que encapsula la informacion del estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/05/2019 17:40 hrs.
 * @version 0.1
 */
public class EstatusConciliacionResponseSaveDTO implements Serializable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -7928335862433836894L;
	
	private Integer id;
	private String descripcion;
	private Boolean estatus;
	
	public EstatusConciliacionResponseSaveDTO() {
		super();
	}

	public EstatusConciliacionResponseSaveDTO(Integer id, String descripcion, Boolean estatus) {
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
		return "EstatusConciliacionResponseSaveDTO [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus
				+ "]";
	}

}
