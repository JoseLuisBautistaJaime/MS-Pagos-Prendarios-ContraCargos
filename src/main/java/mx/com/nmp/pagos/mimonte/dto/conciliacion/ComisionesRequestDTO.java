/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name ComisionesRequestDTO
 * @description Clase que encapsula el request de ComisionesRequestDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:18 hrs.
 * @version 0.1
 */
public class ComisionesRequestDTO implements Comparable<ComisionesRequestDTO> {

	private Integer id;
	private Date fechaCargo;
	private Date fechaOperacion;
	private BigDecimal monto;
	private String descripcion;
	private Boolean estatus;

	public ComisionesRequestDTO() {
		super();
	}

	public ComisionesRequestDTO(Integer id, Date fechaCargo, Date fechaOperacion, BigDecimal monto, String descripcion,
			Boolean estatus) {
		super();
		this.id = id;
		this.fechaCargo = fechaCargo;
		this.fechaOperacion = fechaOperacion;
		this.monto = monto;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "ComisionesRequestDTO [id=" + id + ", fechaCargo=" + fechaCargo + ", fechaOperacion=" + fechaOperacion
				+ ", monto=" + monto + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(ComisionesRequestDTO o) {
		return 0;
	}

}
