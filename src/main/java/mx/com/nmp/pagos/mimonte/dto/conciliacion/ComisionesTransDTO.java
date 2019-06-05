/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name ComisionesTransDTO
 * @description Clase que encapsula la información de una conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 12/04/2019 23:00 hrs.
 * @version 0.1
 */
public class ComisionesTransDTO implements Comparable<ComisionesTransDTO> {

	private Date fechaDesde;
	private Date fechaHasta;
	private BigDecimal comision;
	private ComisionesTransProyeccionDTO proyeccion;
	private ComisionesTransRealDTO real;

	public ComisionesTransDTO() {
		super();
	}

	public ComisionesTransDTO(Date fechaDesde, Date fechaHasta, BigDecimal comision, ComisionesTransProyeccionDTO proyeccion, ComisionesTransRealDTO real) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.comision = comision;
		this.proyeccion = proyeccion;
		this.real = real;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public ComisionesTransProyeccionDTO getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(ComisionesTransProyeccionDTO proyeccion) {
		this.proyeccion = proyeccion;
	}

	public ComisionesTransRealDTO getReal() {
		return real;
	}

	public void setReal(ComisionesTransRealDTO real) {
		this.real = real;
	}

	@Override
	public String toString() {
		return "ComisionesTransDTO [fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", comision=" + comision
				+ ", proyeccion=" + proyeccion + ", real=" + real + "]";
	}

	@Override
	public int compareTo(ComisionesTransDTO o) {
		return 0;
	}

}
