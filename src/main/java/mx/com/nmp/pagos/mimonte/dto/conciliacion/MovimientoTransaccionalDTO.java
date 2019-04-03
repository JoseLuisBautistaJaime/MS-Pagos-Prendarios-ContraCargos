/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name MovimientoTransaccionalDTO
 * @description Clase que encapsula la informacion relacionada con un respuetsa
 *              a el alta de MovimientosProveedor
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 15:35 hrs.
 * @version 0.1
 */
public class MovimientoTransaccionalDTO implements Comparable<MovimientoTransaccionalDTO> {

	private Long id;
	private String idComerciante;
	private String idPedido;
	private String referenciaPedido;
	private String idTransaccion;
	private String referenciaTransaccion;
	private String entidadGestora;
	private Date fecha;
	private String metodoPago;
	private String tipoTransaccion;
	private Double monto;
	private String moneda;
	private String resultado;
	private String codigoPuertaEnlace;
	private String esquemaTarjeta;
	private String identificadorCuenta;
	private String identificadorBanco;
	private String titularCuenta;
	private String codigoAutorizacion;
	private String codigoRespuesta;
	private String numeroLotePago;
	private String reciboTransaccion;
	private String origenTransaccion;
	private String recomendacionRiesgo;
	private String resultadoRevisionRiesgo;
	private String respuestaAVS;
	private String respuestaCSC;
	private String respuesta3DS;
	private String t3dsECI;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdComerciante() {
		return idComerciante;
	}

	public void setIdComerciante(String idComerciante) {
		this.idComerciante = idComerciante;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getReferenciaPedido() {
		return referenciaPedido;
	}

	public void setReferenciaPedido(String referenciaPedido) {
		this.referenciaPedido = referenciaPedido;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getReferenciaTransaccion() {
		return referenciaTransaccion;
	}

	public void setReferenciaTransaccion(String referenciaTransaccion) {
		this.referenciaTransaccion = referenciaTransaccion;
	}

	public String getEntidadGestora() {
		return entidadGestora;
	}

	public void setEntidadGestora(String entidadGestora) {
		this.entidadGestora = entidadGestora;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getCodigoPuertaEnlace() {
		return codigoPuertaEnlace;
	}

	public void setCodigoPuertaEnlace(String codigoPuertaEnlace) {
		this.codigoPuertaEnlace = codigoPuertaEnlace;
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

	public String getIdentificadorBanco() {
		return identificadorBanco;
	}

	public void setIdentificadorBanco(String identificadorBanco) {
		this.identificadorBanco = identificadorBanco;
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

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getNumeroLotePago() {
		return numeroLotePago;
	}

	public void setNumeroLotePago(String numeroLotePago) {
		this.numeroLotePago = numeroLotePago;
	}

	public String getReciboTransaccion() {
		return reciboTransaccion;
	}

	public void setReciboTransaccion(String reciboTransaccion) {
		this.reciboTransaccion = reciboTransaccion;
	}

	public String getOrigenTransaccion() {
		return origenTransaccion;
	}

	public void setOrigenTransaccion(String origenTransaccion) {
		this.origenTransaccion = origenTransaccion;
	}

	public String getRecomendacionRiesgo() {
		return recomendacionRiesgo;
	}

	public void setRecomendacionRiesgo(String recomendacionRiesgo) {
		this.recomendacionRiesgo = recomendacionRiesgo;
	}

	public String getResultadoRevisionRiesgo() {
		return resultadoRevisionRiesgo;
	}

	public void setResultadoRevisionRiesgo(String resultadoRevisionRiesgo) {
		this.resultadoRevisionRiesgo = resultadoRevisionRiesgo;
	}

	public String getRespuestaAVS() {
		return respuestaAVS;
	}

	public void setRespuestaAVS(String respuestaAVS) {
		this.respuestaAVS = respuestaAVS;
	}

	public String getRespuestaCSC() {
		return respuestaCSC;
	}

	public void setRespuestaCSC(String respuestaCSC) {
		this.respuestaCSC = respuestaCSC;
	}

	public String getRespuesta3DS() {
		return respuesta3DS;
	}

	public void setRespuesta3DS(String respuesta3ds) {
		respuesta3DS = respuesta3ds;
	}

	public String getT3dsECI() {
		return t3dsECI;
	}

	public void setT3dsECI(String t3dsECI) {
		this.t3dsECI = t3dsECI;
	}

	@Override
	public String toString() {
		return "MovimientoTransaccionalDTO [id=" + id + ", idComerciante=" + idComerciante + ", idPedido=" + idPedido
				+ ", referenciaPedido=" + referenciaPedido + ", idTransaccion=" + idTransaccion
				+ ", referenciaTransaccion=" + referenciaTransaccion + ", entidadGestora=" + entidadGestora + ", fecha="
				+ fecha + ", metodoPago=" + metodoPago + ", tipoTransaccion=" + tipoTransaccion + ", monto=" + monto
				+ ", moneda=" + moneda + ", resultado=" + resultado + ", codigoPuertaEnlace=" + codigoPuertaEnlace
				+ ", esquemaTarjeta=" + esquemaTarjeta + ", identificadorCuenta=" + identificadorCuenta
				+ ", identificadorBanco=" + identificadorBanco + ", titularCuenta=" + titularCuenta
				+ ", codigoAutorizacion=" + codigoAutorizacion + ", codigoRespuesta=" + codigoRespuesta
				+ ", numeroLotePago=" + numeroLotePago + ", reciboTransaccion=" + reciboTransaccion
				+ ", origenTransaccion=" + origenTransaccion + ", recomendacionRiesgo=" + recomendacionRiesgo
				+ ", resultadoRevisionRiesgo=" + resultadoRevisionRiesgo + ", respuestaAVS=" + respuestaAVS
				+ ", respuestaCSC=" + respuestaCSC + ", respuesta3DS=" + respuesta3DS + ", t3dsECI=" + t3dsECI + "]";
	}

	@Override
	public int compareTo(MovimientoTransaccionalDTO o) {
		return o.id.compareTo(this.id);
	}

}
