/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;

/**
 * @name ComisionesRequestDTO
 * @description Clase que encapsula el request de ComisionesRequestDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:18 hrs.
 * @version 0.1
 */
public class ComisionesRequestDTO implements Comparable<ComisionesRequestDTO>{
	
	private Long id;
	private String fecha;
	private BigDecimal monto;
	private BigDecimal cargoDiversoSucursal;
	private Boolean estatus;

	public ComisionesRequestDTO() {
		super();
	}

	public ComisionesRequestDTO(Long id, String fecha, BigDecimal monto, BigDecimal cargoDiversoSucursal,
			Boolean estatus) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.cargoDiversoSucursal = cargoDiversoSucursal;
		this.estatus = estatus;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getCargoDiversoSucursal() {
		return cargoDiversoSucursal;
	}

	public void setCargoDiversoSucursal(BigDecimal cargoDiversoSucursal) {
		this.cargoDiversoSucursal = cargoDiversoSucursal;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	@Override
	public String toString() {
		return "ComisionesRequestDTO [id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", cargoDiversoSucursal="
				+ cargoDiversoSucursal + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(ComisionesRequestDTO o) {
		return 0;
	}

}
