/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;

/**
 * @name EstatusBonificacionDTO
 * @description Clase que encapsula la informacion del estatus bonificacion
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 11/11/2020 23:12 hrs.
 * @version 0.1
 */
public class EstatusBonificacionDTO implements Serializable {

	private static final long serialVersionUID = 6530936570284122162L;

	private Long id;
	private String descripcion;
	private Boolean estatus;

	public EstatusBonificacionDTO() {
		super();
	}

	public EstatusBonificacionDTO(Long id, String descripcion, Boolean estatus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estatus = estatus;
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

}
