/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name SolicitarPagosMailDataDTO
 * @description Clase tipo DTO que se encarga de mapear la respuesta de la
 *              consulta de solicitud de pagos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/06/2019 17:35 hrs.
 * @version 0.1
 */
public class SolicitarPagosMailDataDTO implements java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaTransaccion;
	private Integer folioPartida;
	private String transaccion;
	private String codigoDescuento;
	private String tipoTransaccion;
	private BigDecimal montoTransaccion;
	private String tipoMoneda;
	private String tipoCuenta;
	private String codigoRespuestaMotorPagosTransaccion;
	private String metodoPago;
	private String identificadorCuenta;
	private String idTerminalAdquiriente;
	private String titularCuenta;
	private String codigoAutorizacion;
	private String codigoRespuestaAdquiriente;
	private String numeroLoteAdquiriente;
	private String fuenteTransaccion;

	public SolicitarPagosMailDataDTO() {
		super();
	}

	public SolicitarPagosMailDataDTO(Date fechaTransaccion, Integer folioPartida, String transaccion,
			String codigoDescuento, String tipoTransaccion, BigDecimal montoTransaccion, String tipoMoneda,
			String tipoCuenta, String codigoRespuestaMotorPagosTransaccion, String metodoPago,
			String identificadorCuenta, String idTerminalAdquiriente, String titularCuenta, String codigoAutorizacion,
			String codigoRespuestaAdquiriente, String numeroLoteAdquiriente, String fuenteTransaccion) {
		super();
		this.fechaTransaccion = fechaTransaccion;
		this.folioPartida = folioPartida;
		this.transaccion = transaccion;
		this.codigoDescuento = codigoDescuento;
		this.tipoTransaccion = tipoTransaccion;
		this.montoTransaccion = montoTransaccion;
		this.tipoMoneda = tipoMoneda;
		this.tipoCuenta = tipoCuenta;
		this.codigoRespuestaMotorPagosTransaccion = codigoRespuestaMotorPagosTransaccion;
		this.metodoPago = metodoPago;
		this.identificadorCuenta = identificadorCuenta;
		this.idTerminalAdquiriente = idTerminalAdquiriente;
		this.titularCuenta = titularCuenta;
		this.codigoAutorizacion = codigoAutorizacion;
		this.codigoRespuestaAdquiriente = codigoRespuestaAdquiriente;
		this.numeroLoteAdquiriente = numeroLoteAdquiriente;
		this.fuenteTransaccion = fuenteTransaccion;
	}

	public SolicitarPagosMailDataDTO(Date fechaTransaccion, Integer folioPartida, String transaccion,
			String tipoTransaccion, BigDecimal montoTransaccion, String tipoMoneda, String tipoCuenta,
			String codigoRespuestaMotorPagosTransaccion, String metodoPago, String identificadorCuenta,
			String titularCuenta, String codigoAutorizacion, String fuenteTransaccion) {
		super();
		this.fechaTransaccion = fechaTransaccion;
		this.folioPartida = folioPartida;
		this.transaccion = transaccion;
		this.tipoTransaccion = tipoTransaccion;
		this.montoTransaccion = montoTransaccion;
		this.tipoMoneda = tipoMoneda;
		this.tipoCuenta = tipoCuenta;
		this.codigoRespuestaMotorPagosTransaccion = codigoRespuestaMotorPagosTransaccion;
		this.metodoPago = metodoPago;
		this.identificadorCuenta = identificadorCuenta;
		this.titularCuenta = titularCuenta;
		this.codigoAutorizacion = codigoAutorizacion;
		this.fuenteTransaccion = fuenteTransaccion;
		// Se llenan con N/A ya que no es posible regresar estos datos dado que no
		// existen en niguna tabla
		this.codigoDescuento = "N/A";
		this.idTerminalAdquiriente = "N/A";
		this.codigoRespuestaAdquiriente = "N/A";
		this.numeroLoteAdquiriente = "N/A";
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Integer getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Integer folioPartida) {
		this.folioPartida = folioPartida;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal getMontoTransaccion() {
		return montoTransaccion;
	}

	public void setMontoTransaccion(BigDecimal montoTransaccion) {
		this.montoTransaccion = montoTransaccion;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCodigoRespuestaMotorPagosTransaccion() {
		return codigoRespuestaMotorPagosTransaccion;
	}

	public void setCodigoRespuestaMotorPagosTransaccion(String codigoRespuestaMotorPagosTransaccion) {
		this.codigoRespuestaMotorPagosTransaccion = codigoRespuestaMotorPagosTransaccion;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}

	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
	}

	public String getIdTerminalAdquiriente() {
		return idTerminalAdquiriente;
	}

	public void setIdTerminalAdquiriente(String idTerminalAdquiriente) {
		this.idTerminalAdquiriente = idTerminalAdquiriente;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public String getCodigoRespuestaAdquiriente() {
		return codigoRespuestaAdquiriente;
	}

	public void setCodigoRespuestaAdquiriente(String codigoRespuestaAdquiriente) {
		this.codigoRespuestaAdquiriente = codigoRespuestaAdquiriente;
	}

	public String getNumeroLoteAdquiriente() {
		return numeroLoteAdquiriente;
	}

	public void setNumeroLoteAdquiriente(String numeroLoteAdquiriente) {
		this.numeroLoteAdquiriente = numeroLoteAdquiriente;
	}

	public String getFuenteTransaccion() {
		return fuenteTransaccion;
	}

	public void setFuenteTransaccion(String fuenteTransaccion) {
		this.fuenteTransaccion = fuenteTransaccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaTransaccion, folioPartida, transaccion, codigoDescuento, tipoTransaccion,
				montoTransaccion, tipoMoneda, tipoCuenta, codigoRespuestaMotorPagosTransaccion, metodoPago,
				identificadorCuenta, idTerminalAdquiriente, titularCuenta, codigoAutorizacion,
				codigoRespuestaAdquiriente, numeroLoteAdquiriente, fuenteTransaccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof SolicitarPagosMailDataDTO))
			return false;

		final SolicitarPagosMailDataDTO other = (SolicitarPagosMailDataDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "SolicitarPagosMailDataDTO [fechaTransaccion=" + fechaTransaccion + ", folioPartida=" + folioPartida
				+ ", transaccion=" + transaccion + ", codigoDescuento=" + codigoDescuento + ", tipoTransaccion="
				+ tipoTransaccion + ", montoTransaccion=" + montoTransaccion + ", tipoMoneda=" + tipoMoneda
				+ ", tipoCuenta=" + tipoCuenta + ", codigoRespuestaMotorPagosTransaccion="
				+ codigoRespuestaMotorPagosTransaccion + ", metodoPago=" + metodoPago + ", identificadorCuenta="
				+ identificadorCuenta + ", idTerminalAdquiriente=" + idTerminalAdquiriente + ", titularCuenta="
				+ titularCuenta + ", codigoAutorizacion=" + codigoAutorizacion + ", codigoRespuestaAdquiriente="
				+ codigoRespuestaAdquiriente + ", numeroLoteAdquiriente=" + numeroLoteAdquiriente
				+ ", fuenteTransaccion=" + fuenteTransaccion + "]";
	}

}
