/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;

/**
 * @name MovimientoProveedor
 * @description Encapsula la informacion de consulta de movimientos de proveedor
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:00 PM
 */
@Entity
@Table(name = "to_movimiento_proveedor")
public class MovimientoProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false, unique = true)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name = "id_reporte", nullable = false)
	@Column(name = "id_reporte", nullable = false)
	private Long reporte;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "id_comerciante", nullable = true)
	private String idComerciante;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "id_pedido", nullable = true)
	private String idPedido;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "referencia_pedido", nullable = true)
	private String referenciaPedido;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "id_transaccion", nullable = true)
	private String idTransaccion;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "referencia_transaccion", nullable = true)
	private String referenciaTransaccion;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "entidad_gestora", nullable = true)
	private String entidadGestora;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = true)
	private Date fecha;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "metodo_pago", nullable = true)
	private String metodoPago;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "tipo_transaccion", nullable = true)
	private String tipoTransaccion;

	@Column(name = "monto", nullable = true)
	private BigDecimal monto;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "moneda", nullable = true)
	private String moneda;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "resutado", nullable = true)
	private String resultado;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "codigo_puerta_enlace", nullable = true)
	private String codigoPuertaEnlace;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "esquema_tarjeta", nullable = true)
	private String esquemaTarjeta;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "identificador_cuenta", nullable = true)
	private String identificadorCuenta;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "identificador_banco", nullable = true)
	private String identificadorBanco;

	@Size(max = 150, message = "Debe ingresar maximo 150 caracteres")
	@Column(name = "titular_cuenta", nullable = true)
	private String titularCuenta;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "codigo_autorizacion", nullable = true)
	private String codigoAutorizacion;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "codigo_respuesta", nullable = true)
	private String codigoRespuesta;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "numero_lote_pago", nullable = true)
	private String numeroLotePago;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "origen_transaccion", nullable = true)
	private String origenTransaccion;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "recomendacion_riesgo", nullable = true)
	private String recomendacionRiesgo;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "resultado_revision_riesgo", nullable = true)
	private String resultadoRevisionRiesgo;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "respuesta_avs", nullable = true)
	private String respuestaAvs;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "respuesta_csc", nullable = true)
	private String respuestaCsc;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "respuesta_3ds", nullable = true)
	private String respuesta3ds;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "3dseci", nullable = true)
	private String t3dseci;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "recibo_transaccion", nullable = true)
	private String reciboTransaccion;

	public MovimientoProveedor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReporte() {
		return reporte;
	}

	public void setReporte(Long reporte) {
		this.reporte = reporte;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
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

	public String getRespuestaAvs() {
		return respuestaAvs;
	}

	public void setRespuestaAvs(String respuestaAvs) {
		this.respuestaAvs = respuestaAvs;
	}

	public String getRespuestaCsc() {
		return respuestaCsc;
	}

	public void setRespuestaCsc(String respuestaCsc) {
		this.respuestaCsc = respuestaCsc;
	}

	public String getRespuesta3ds() {
		return respuesta3ds;
	}

	public void setRespuesta3ds(String respuesta3ds) {
		this.respuesta3ds = respuesta3ds;
	}

	public String getT3dseci() {
		return t3dseci;
	}

	public void setT3dseci(String t3dseci) {
		this.t3dseci = t3dseci;
	}

	public String getReciboTransaccion() {
		return reciboTransaccion;
	}

	public void setReciboTransaccion(String reciboTransaccion) {
		this.reciboTransaccion = reciboTransaccion;
	}

	@Override
	public String toString() {
		return "MovimientoProveedor [id=" + id + ", idReporte=" + reporte + ", idComerciante=" + idComerciante
				+ ", idPedido=" + idPedido + ", referenciaPedido=" + referenciaPedido + ", idTransaccion="
				+ idTransaccion + ", referenciaTransaccion=" + referenciaTransaccion + ", entidadGestora="
				+ entidadGestora + ", fecha=" + fecha + ", metodoPago=" + metodoPago + ", tipoTransaccion="
				+ tipoTransaccion + ", monto=" + monto + ", moneda=" + moneda + ", resultado=" + resultado
				+ ", codigoPuertaEnlace=" + codigoPuertaEnlace + ", esquemaTarjeta=" + esquemaTarjeta
				+ ", identificadorCuenta=" + identificadorCuenta + ", identificadorBanco=" + identificadorBanco
				+ ", titularCuenta=" + titularCuenta + ", codigoAutorizacion=" + codigoAutorizacion
				+ ", codigoRespuesta=" + codigoRespuesta + ", numeroLotePago=" + numeroLotePago + ", origenTransaccion="
				+ origenTransaccion + ", recomendacionRiesgo=" + recomendacionRiesgo + ", resultadoRevisionRiesgo="
				+ resultadoRevisionRiesgo + ", respuestaAvs=" + respuestaAvs + ", respuestaCsc=" + respuestaCsc
				+ ", respuesta3ds=" + respuesta3ds + ", t3dseci=" + t3dseci + ", reciboTransaccion=" + reciboTransaccion
				+ "]";
	}

}