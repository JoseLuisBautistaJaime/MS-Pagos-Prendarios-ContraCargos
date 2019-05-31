/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name ComisionesDTO
 * @description Clase que encapsula las comisiones de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 16:50 hrs.
 * @version 0.1
 */
public class ComisionesDTO implements Comparable<ComisionesDTO> {

	private Long id;
	private Date fecha;
	private Date fechaCargo;
	private BigDecimal monto;
	private String descripcion;

	public ComisionesDTO() {
		super();
	}

	public ComisionesDTO(Long id, Date fecha, Date fechaCargo, BigDecimal monto, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.fechaCargo = fechaCargo;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public String toString() {
		return "ComisionesDTO [id=" + id + ", fecha=" + fecha + ", fechaCargo=" + fechaCargo + ", monto=" + monto
				+ ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(ComisionesDTO o) {
		return 0;
	}

}
