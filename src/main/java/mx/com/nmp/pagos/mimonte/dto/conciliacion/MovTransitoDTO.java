/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovTransitoDTO
 * @description Clase que encapsula los movimientos de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:24 hrs.
 * @version 0.1
 */
public class MovTransitoDTO implements Comparable<MovTransitoDTO> {

	private Integer id;
	private EstatusMovTransitoDTO estatus;
	private Integer folio;
	private Integer sucursal;
	private Date fecha;
	private String operacion;
	private BigDecimal monto;
	private String tipoContrato;
	private String esquemaTarjeta;
	private String cuenta;
	private String titularCuenta;
	private String numAutorizacion;
	private String transaccion;

	public MovTransitoDTO() {
		super();
	}

	public MovTransitoDTO(Integer id, EstatusMovTransitoDTO estatus, Integer folio, Integer sucursal, Date fecha,
			String operacion, BigDecimal monto, String tipoContrato, String esquemaTarjeta, String cuenta,
			String titularCuenta, String transaccion) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.folio = folio;
		this.sucursal = sucursal;
		this.fecha = fecha;
		this.operacion = operacion;
		this.monto = monto;
		this.tipoContrato = tipoContrato;
		this.esquemaTarjeta = esquemaTarjeta;
		this.cuenta = cuenta;
		this.titularCuenta = titularCuenta;
		this.transaccion = transaccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstatusMovTransitoDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusMovTransitoDTO estatus) {
		this.estatus = estatus;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	@Override
	public String toString() {
		return "MovTransitoDTO [id=" + id + ", estatus=" + estatus + ", folio=" + folio + ", sucursal=" + sucursal
				+ ", fecha=" + fecha + ", operacion=" + operacion + ", monto=" + monto + ", tipoContrato="
				+ tipoContrato + ", esquemaTarjeta=" + esquemaTarjeta + ", cuenta=" + cuenta + ", titularCuenta="
				+ titularCuenta + "]";
	}

	@Override
	public int compareTo(MovTransitoDTO o) {
		return 0;
	}

	public String getNumAutorizacion() {
		return numAutorizacion;
	}

	public void setNumAutorizacion(String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

}
