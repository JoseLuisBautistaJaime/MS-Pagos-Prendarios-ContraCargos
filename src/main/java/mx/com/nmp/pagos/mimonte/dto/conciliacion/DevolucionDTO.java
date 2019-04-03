/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name LayoutDTO
 * @description Clase que encapsula la informacion de un objeto Devolucion de
 *              alta
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 31/03/2019 18:33:53 hrs.
 * @version 0.1
 */
public class DevolucionDTO implements Comparable<DevolucionDTO> {

	private Long id;
	private Long idEntidad;
	private Integer estatus;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer sucursal;
	private String cuenta;

	public DevolucionDTO() {
		super();
	}

	public DevolucionDTO(Long id, Long idEntidad, Integer estatus, Date fechaDesde, Date fechaHasta, Integer sucursal,
			String cuenta) {
		super();
		this.id = id;
		this.idEntidad = idEntidad;
		this.estatus = estatus;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.sucursal = sucursal;
		this.cuenta = cuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "DevolucionDTO [id=" + id + ", idEntidad=" + idEntidad + ", estatus=" + estatus + ", fechaDesde="
				+ fechaDesde + ", fechaHasta=" + fechaHasta + ", sucursal=" + sucursal + ", cuenta=" + cuenta + "]";
	}

	@Override
	public int compareTo(DevolucionDTO o) {
		return o.id.compareTo(this.id);
	}

}