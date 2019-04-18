/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ComisionSaveResponseDTO
 * @description Clase que encapsula la informacion de de una peticion para alta
 *              de Comision
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 17/04/2019 17:32 hrs.
 * @version 0.1
 */
public class ComisionSaveResponseDTO implements Comparable<ComisionSaveResponseDTO>{
	
	private Integer folio;
	private Integer id;
	private Date fechaOperacion;
	private Date fechaCargo;
	private Double monto;
	private String descripcion;
	private Boolean nuevaComision;
	
	public ComisionSaveResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComisionSaveResponseDTO(Integer folio, Integer id, Date fechaOperacion, Date fechaCargo, Double monto,
			String descripcion, Boolean nuevaComision) {
		super();
		this.folio = folio;
		this.id = id;
		this.fechaOperacion = fechaOperacion;
		this.fechaCargo = fechaCargo;
		this.monto = monto;
		this.descripcion = descripcion;
		this.nuevaComision = nuevaComision;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getNuevaComision() {
		return nuevaComision;
	}

	public void setNuevaComision(Boolean nuevaComision) {
		this.nuevaComision = nuevaComision;
	}

	@Override
	public String toString() {
		return "ComisionSaveResponseDTO [folio=" + folio + ", id=" + id + ", fechaOperacion=" + fechaOperacion
				+ ", fechaCargo=" + fechaCargo + ", monto=" + monto + ", descripcion=" + descripcion
				+ ", nuevaComision=" + nuevaComision + "]";
	}

	@Override
	public int compareTo(ComisionSaveResponseDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
