/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;

/**
 * @name DevolucionEntidadDTO2
 * @description Clase que encapsula la informacion de un objeto devolucion para
 *              mostrar en el layout HTML del e-mail
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/07/2019 19:17 hrs.
 * @version 0.1
 */
public class DevolucionEntidadDTO2 {

	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

	private Integer id;
	private BaseEntidadDTO entidad;
	private Date fecha;
	private Date hora;
	private EstatusDevolucionDTO estatus;
	private Integer sucursal;
	private String identificadorCuenta;
	private BigDecimal monto;
	private String esquemaTarjeta;
	private String titular;
	private String codigoAutorizacion;
	private Date fechaLiquidacion;

	public DevolucionEntidadDTO2() {
		super();
	}

	public String getHora() {
		return sdf.format(hora);
	}

	public Date getHoraComleteFormat() {
		return this.hora;
	}
	
	public void setHora(Date hora) {
		this.hora = hora;
	}

	public BaseEntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(BaseEntidadDTO entidad) {
		this.entidad = entidad;
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

}
