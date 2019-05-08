/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovimientoMidasDTO
 * @description Clase que encapsula la informacion de un movimiento midas
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:35 PM
 */
public class MovimientoMidasDTO {

	public MovimientoMidasDTO() {
		super();
	}

	private Long id;
	private Long folioPartida;
	private Long transaccion;
	private Long sucursal;
	private String estadoTransaccion;
	private Date fecha;
	private String operacionAbr;
	private String operacionDesc;
	private BigDecimal montoOperacion;
	private String tipoContratoAbr;
	private String tipoContratoDesc;
	private String numAutorizacion;
	private BigDecimal capitalActual;
	private BigDecimal comisiones;
	private BigDecimal interes;
	private Boolean estatus;
	private String consumidor;
	private BigDecimal importeTransaccion;

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public String getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(String idConsumidor) {
		this.consumidor = idConsumidor;
	}

	public BigDecimal getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(BigDecimal importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getMontoOperacion() {
		return montoOperacion;
	}

	public void setMontoOperacion(BigDecimal montoOperacion) {
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

	public BigDecimal getCapitalActual() {
		return capitalActual;
	}

	public void setCapitalActual(BigDecimal capitalActual) {
		this.capitalActual = capitalActual;
	}

	public BigDecimal getComisiones() {
		return comisiones;
	}

	public void setComisiones(BigDecimal comisiones) {
		this.comisiones = comisiones;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

}