/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name DevolucionesMovimientosDTO
 * @description Clase que encapsula la información para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:05 hrs.
 * @version 0.1
 */
public class DevolucionesMovimientosDTO implements Comparable<DevolucionesMovimientosDTO>{
	
	private Integer id;
	private Date fecha;
	private EstatusDevolucionDTO estatus;
	private BigDecimal monto;
	private String esquemaTarjeta;
	private String identificadorCuenta;
	private String titular;
	private String codigoAutorizacion;
	private Integer sucursal;
	private Date fechaLiquidacion;

	public DevolucionesMovimientosDTO() {
		super();
	}

	public DevolucionesMovimientosDTO(Integer id, Date fecha, EstatusDevolucionDTO estatus, BigDecimal monto,
			String esquemaTarjeta, String identificadorCuenta, String titular, String codigoAutorizacion, Integer sucursal,
			Date fechaLiquidacion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.estatus = estatus;
		this.monto = monto;
		this.esquemaTarjeta = esquemaTarjeta;
		this.identificadorCuenta = identificadorCuenta;
		this.titular = titular;
		this.codigoAutorizacion = codigoAutorizacion;
		this.sucursal = sucursal;
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
	}

	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}

	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
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

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	@Override
	public String toString() {
		return "DevolucionesMovimientosDTO [id=" + id + ", fecha=" + fecha + ", estatus=" + estatus + ", monto=" + monto
				+ ", esquemaTarjeta=" + esquemaTarjeta + ", identificadorCuenta=" + identificadorCuenta + ", titular="
				+ titular + ", codigoAutorizacion=" + codigoAutorizacion + ", sucursal=" + sucursal
				+ ", fechaLiquidacion=" + fechaLiquidacion + "]";
	}

	@Override
	public int compareTo(DevolucionesMovimientosDTO o) {
		return 0;
	}

}
