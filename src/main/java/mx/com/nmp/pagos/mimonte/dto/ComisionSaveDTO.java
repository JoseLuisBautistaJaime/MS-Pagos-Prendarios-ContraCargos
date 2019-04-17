/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * @name ComisionSaveDTO
 * @description Clase que encapsula la informacion de de una peticion para alta
 *              de Comision
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 14:09 hrs.
 * @version 0.1
 */
public class ComisionSaveDTO implements Comparable<ComisionSaveDTO> {

	private Integer folio;
	private Date fechaOperacion;
	private Date fechaCargo;
	private Double monto;
	private String descripcion;

	public ComisionSaveDTO() {
		super();
	}

	public ComisionSaveDTO(Integer folio, Date fechaOperacion, Date fechaCargo, Double monto, String descripcion) {
		super();
		this.folio = folio;
		this.fechaOperacion = fechaOperacion;
		this.fechaCargo = fechaCargo;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ComisionSaveDTO [folio=" + folio + ", fechaOperacion=" + fechaOperacion + ", fechaCargo=" + fechaCargo
				+ ", monto=" + monto + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(ComisionSaveDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
