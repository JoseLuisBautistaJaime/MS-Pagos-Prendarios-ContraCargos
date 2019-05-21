/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name MovimientoMidasRequestDTO
 * @description Clase que encapsula la informacion de un movimiento midas
 * @author Ismael Flores aiguilar@quarksoft.net
 * @version 1.0
 * @created 31-Mar-2019 6:33:35 PM
 */
public class MovimientoMidasRequestDTO implements Comparable<MovimientoMidasRequestDTO> {

	private Long id;
	private Long folioPartida;
	private Long transaccion;
	private Integer sucursal;
	private String estadoTransaccion;
	private Date fecha;
	private Integer idOperacion;
	private String operacionAbr;
	private String operacionDesc;
	private BigDecimal montoOperacion;
	private Integer idTipoContrato;
	private String tipoContratoAbr;
	private String tipoContratoDesc;
	private String numAutorizacion;
	private BigDecimal capitalActual;
	private BigDecimal comisiones;
	private BigDecimal interes;
	private Boolean estatus;
	private String consumidor;
	private String codigoError;
	private String mensajeError;
	private String idTarjeta;
	private String marcaTarjeta;
	private String tipoTarjeta;
	private String tarjeta;
	private String monedaPago;
	private BigDecimal importeTransaccion;

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

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getMonedaPago() {
		return monedaPago;
	}

	public void setMonedaPago(String monedaPago) {
		this.monedaPago = monedaPago;
	}

	public BigDecimal getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(BigDecimal importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

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

	@Override
	public String toString() {
		return "MovimientoMidasRequestDTO [id=" + id + ", folioPartida=" + folioPartida + ", transaccion=" + transaccion
				+ ", sucursal=" + sucursal + ", estadoTransaccion=" + estadoTransaccion + ", fecha=" + fecha
				+ ", idOperacion=" + idOperacion + ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc
				+ ", montoOperacion=" + montoOperacion + ", idTipoContrato=" + idTipoContrato + ", tipoContratoAbr="
				+ tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc + ", numAutorizacion=" + numAutorizacion
				+ ", capitalActual=" + capitalActual + ", comisiones=" + comisiones + ", interes=" + interes
				+ ", estatus=" + estatus + ", consumidor=" + consumidor + ", codigoError=" + codigoError
				+ ", mensajeError=" + mensajeError + ", idTarjeta=" + idTarjeta + ", marcaTarjeta=" + marcaTarjeta
				+ ", tipoTarjeta=" + tipoTarjeta + ", tarjeta=" + tarjeta + ", monedaPago=" + monedaPago
				+ ", importeTransaccion=" + importeTransaccion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, folioPartida, transaccion, sucursal, estadoTransaccion, fecha, idOperacion,
				operacionAbr, operacionDesc, montoOperacion, idTipoContrato, tipoContratoAbr, tipoContratoDesc,
				numAutorizacion, capitalActual, comisiones, interes, estatus, consumidor, codigoError, mensajeError,
				idTarjeta, marcaTarjeta, tipoTarjeta, tarjeta, monedaPago, importeTransaccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoMidasRequestDTO))
			return false;

		final MovimientoMidasRequestDTO other = (MovimientoMidasRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(MovimientoMidasRequestDTO arg0) {
		return arg0.id.compareTo(this.id);
	}

}
