/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

	private Long folio;
	private Integer id;
	private Date fechaOperacion;
	private Date fechaCargo;
	private BigDecimal monto;
	private String descripcion;

	public ComisionSaveDTO() {
		super();
	}

	public ComisionSaveDTO(Long folio, Integer id, Date fechaOperacion, Date fechaCargo, BigDecimal monto,
			String descripcion) {
		super();
		this.folio = folio;
		this.id = id;
		this.fechaOperacion = fechaOperacion;
		this.fechaCargo = fechaCargo;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folio, id, fechaOperacion, fechaCargo, monto, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ComisionSaveDTO))
			return false;

		final ComisionSaveDTO other = (ComisionSaveDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "ComisionSaveDTO [id=" + id + ", folio=" + folio + ", fechaOperacion=" + fechaOperacion + ", fechaCargo="
				+ fechaCargo + ", monto=" + monto + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(ComisionSaveDTO o) {
		return o.id.compareTo(this.id);
	}

}
