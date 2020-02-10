/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name DevolucionRequestDTO
 * @description Clase que encapsula la informacion de un objeto Devolucion.
 *
 * @author José Rodríguez jgrodríguez@quarksoft.net
 * @creationDate 17/04/2019 18:47:53 hrs.
 * @version 0.1
 */
public class DevolucionRequestDTO implements Comparable<DevolucionRequestDTO>{
	
	private Long idEntidad;
	private Integer estatus;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer sucursal;
	private String identificadorCuenta;

	
	public DevolucionRequestDTO() {
		super();
	}


	public DevolucionRequestDTO(Long idEntidad, Integer estatus, Date fechaDesde, Date fechaHasta, Integer sucursal,
			String identificadorCuenta) {
		super();
		this.idEntidad = idEntidad;
		this.estatus = estatus;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.sucursal = sucursal;
		this.identificadorCuenta = identificadorCuenta;
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


	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}


	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
	}


	@Override
	public String toString() {
		return "DevolucionRequestDTO [idEntidad=" + idEntidad + ", estatus=" + estatus + ", fechaDesde=" + fechaDesde
				+ ", fechaHasta=" + fechaHasta + ", sucursal=" + sucursal + ", identificadorCuenta="
				+ identificadorCuenta + "]";
	}


	@Override
	public int compareTo(DevolucionRequestDTO o) {
		return 0;
	}

}
