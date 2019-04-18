/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;

/**
 * @name DevolucionEntidadDTO
 * @description Clase que encapsula la informacion de un objeto devolucion de
 *              respuesta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 12:06 hrs.
 * @version 0.1
 */
public class DevolucionEntidadDTO implements Comparable<DevolucionEntidadDTO> {

	private Long id;
	private BaseEntidadDTO entidad;
	private Date fecha;
	private EstatusDevolucionDTO estatus;
	private Integer sucursal;
	private String identificadorCuenta;
	private Double monto;
	private String esquemaTarjeta;
	private String titular;
	private String codigoAutorizacion;
	private Date fechaLiquidacion;

	public DevolucionEntidadDTO() {
		super();
	}

	public DevolucionEntidadDTO(Long id, BaseEntidadDTO entidad, Date fecha, EstatusDevolucionDTO estatus,
			Integer sucursal, String identificadorCuenta, Double monto, String esquemaTarjeta, 
			String titular, String codigoAutorizacion, Date fechaLiquidacion) {
		super();
		this.id = id;
		this.entidad = entidad;
		this.fecha = fecha;
		this.estatus = estatus;
		this.sucursal = sucursal;
		this.identificadorCuenta = identificadorCuenta;
		this.monto = monto;
		this.esquemaTarjeta = esquemaTarjeta;
		this.titular = titular;
		this.codigoAutorizacion = codigoAutorizacion;
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public BaseEntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(BaseEntidadDTO entidad) {
		this.entidad = entidad;
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

	public EstatusDevolucionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusDevolucionDTO estatus) {
		this.estatus = estatus;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}

	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
	}
	
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	@Override
	public String toString() {
		return "DevolucionEntidadDTO [id=" + id + ", entidad=" + entidad + ", fecha=" + fecha + ", estatus=" + estatus
				+ ", sucursal=" + sucursal + ", identificadorCuenta=" + identificadorCuenta + ", monto=" + monto
				+ ", esquemaTarjeta=" + esquemaTarjeta + ", titular=" + titular + ", codigoAutorizacion="
				+ codigoAutorizacion + ", fechaLiquidacion=" + fechaLiquidacion + "]";
	}

	@Override
	public int compareTo(DevolucionEntidadDTO o) {
		return o.id.compareTo(this.id);
	}

}
