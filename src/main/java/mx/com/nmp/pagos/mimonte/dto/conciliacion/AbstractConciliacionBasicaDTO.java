/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;

/**
 * @name AbstractConciliacionBasicaDTO
 * @descriptipon Clase abstracta que que encapsula la informacion basica de una conciliacion.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:48 hrs.
 * @version 0.1
 */
public abstract class AbstractConciliacionBasicaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5798158867093327399L;
	
	protected Integer id;
	protected String descripcion;
	protected Boolean estatus;
	
	public AbstractConciliacionBasicaDTO() {
		super();
	}

	public AbstractConciliacionBasicaDTO(Integer id, String descripcion, Boolean estatus) {
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
		return "AbstractConciliacionBasicaDTO [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus
				+ "]";
	}

}
