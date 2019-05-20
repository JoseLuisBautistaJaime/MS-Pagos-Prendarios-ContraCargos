/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovimientoProcesosNocturnosDTO
 * @description Clase que encapsula la informacion relacionada con una peticion
 *              de alta de movimientos nocturnos
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 15:49 hrs.
 * @version 0.1
 */
public class MovimientoProcesosNocturnosResponseDTO {

	private Long folioPartida;
	private Long transaccion;
	private String estadoTransaccion;
	private Long sucursal;
	private Date fecha;
	private Integer idOperacion;
	private String operacionAbr;
	private String operacionDesc;
	private Double montoOperacion;
	private Integer idTipoContrato;
	private String tipoContratoAbr;
	private String tipoContratoDesc;
	private String numAutorizacion;
	private Double capitalActual;
	private Double comisiones;
	private Double interes;
	private Boolean estatus;
	private String consumidor;
	private BigDecimal importeTransaccion;
	
	private String codigoError;
	private String idTarjeta;
	private String marcaTarjeta;
	private String mensajeError;
	private String monedaPago;
	private String tarjeta;
	private String tipoTarjeta;
	
	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(Integer idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public Long getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Long folioPartida) {
		this.folioPartida = folioPartida;
	}

	public Long getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Long transaccion) {
		this.transaccion = transaccion;
	}

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacionAbr() {
		return operacionAbr;
	}

	public void setOperacionAbr(String operacionAbr) {
		this.operacionAbr = operacionAbr;
	}

	public String getOperacionDesc() {
		return operacionDesc;
	}

	public void setOperacionDesc(String operacionDesc) {
		this.operacionDesc = operacionDesc;
	}

	public Double getMontoOperacion() {
		return montoOperacion;
	}

	public void setMontoOperacion(Double montoOperacion) {
		this.montoOperacion = montoOperacion;
	}

	public String getTipoContratoAbr() {
		return tipoContratoAbr;
	}

	public void setTipoContratoAbr(String tipoContratoAbr) {
		this.tipoContratoAbr = tipoContratoAbr;
	}

	public String getTipoContratoDesc() {
		return tipoContratoDesc;
	}

	public void setTipoContratoDesc(String tipoContratoDesc) {
		this.tipoContratoDesc = tipoContratoDesc;
	}

	public String getNumAutorizacion() {
		return numAutorizacion;
	}

	public void setNumAutorizacion(String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}

	public Double getCapitalActual() {
		return capitalActual;
	}

	public void setCapitalActual(Double capitalActual) {
		this.capitalActual = capitalActual;
	}

	public Double getComisiones() {
		return comisiones;
	}

	public void setComisiones(Double comisiones) {
		this.comisiones = comisiones;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(String consumidor) {
		this.consumidor = consumidor;
	}

	public BigDecimal getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(BigDecimal importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

	
	
	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getMarcaTarjeta() {
		return marcaTarjeta;
	}

	public void setMarcaTarjeta(String marcaTarjeta) {
		this.marcaTarjeta = marcaTarjeta;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMonedaPago() {
		return monedaPago;
	}

	public void setMonedaPago(String monedaPago) {
		this.monedaPago = monedaPago;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	@Override
	public String toString() {
		return "MovimientoProcesosNocturnosResponseDTO [folioPartida=" + folioPartida + ", transaccion=" + transaccion
				+ ", estadoTransaccion=" + estadoTransaccion + ", sucursal=" + sucursal + ", fecha=" + fecha
				+ ", idOperacion=" + idOperacion + ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc
				+ ", montoOperacion=" + montoOperacion + ", idTipoContrato=" + idTipoContrato + ", tipoContratoAbr="
				+ tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc + ", numAutorizacion=" + numAutorizacion
				+ ", capitalActual=" + capitalActual + ", comisiones=" + comisiones + ", interes=" + interes
				+ ", estatus=" + estatus + ", consumidor=" + consumidor + ", importeTransaccion=" + importeTransaccion
				+ ", codigoError=" + codigoError + ", idTarjeta=" + idTarjeta + ", marcaTarjeta=" + marcaTarjeta
				+ ", mensajeError=" + mensajeError + ", monedaPago=" + monedaPago + ", tarjeta=" + tarjeta
				+ ", tipoTarjeta=" + tipoTarjeta + "]";
	}

}
