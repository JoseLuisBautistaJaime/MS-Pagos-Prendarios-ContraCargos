/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Nombre: Pago Descripcion: Entidad que representa un pago dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 21/11/2018 13:36 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "to_pagos")
public class Pago {

	public Pago() {
		super();
	}

	public Pago(Long id, Cliente cliente, Date fechaTarnsaccion, Double monto, String autorizacion, String metodo,
			String tarjeta, String idOpenPay, Date fechaCreacion, String descripcion, String idOrder,
			EstatusPagos estatusPago, String restResponse, Long idTransaccionMidas, Long folioPartida,
			Integer idOperacion, Integer idTipoAutorizacion) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechaTarnsaccion = fechaTarnsaccion;
		this.monto = monto;
		this.autorizacion = autorizacion;
		this.metodo = metodo;
		this.tarjeta = tarjeta;
		this.idOpenPay = idOpenPay;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.idOrder = idOrder;
		this.estatusPago = estatusPago;
		this.restResponse = restResponse;
		this.idTransaccionMidas = idTransaccionMidas;
		this.folioPartida = folioPartida;
		this.idOperacion = idOperacion;
		this.idTipoAutorizacion = idTipoAutorizacion;
	}

	public Pago(Long id, Cliente cliente, Date fechaTarnsaccion, Double monto, String autorizacion, String metodo,
			String tarjeta, String idOpenPay, Date fechaCreacion, String descripcion, String idOrder,
			EstatusPagos estatusPago, String restResponse, Long idTransaccionMidas, Long folioPartida,
			Integer idOperacion) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechaTarnsaccion = fechaTarnsaccion;
		this.monto = monto;
		this.autorizacion = autorizacion;
		this.metodo = metodo;
		this.tarjeta = tarjeta;
		this.idOpenPay = idOpenPay;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.idOrder = idOrder;
		this.estatusPago = estatusPago;
		this.restResponse = restResponse;
		this.idTransaccionMidas = idTransaccionMidas;
		this.folioPartida = folioPartida;
		this.idOperacion = idOperacion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_cliente", nullable = true)
	private Cliente cliente;

	@Column(name = "fecha_transaccion", nullable = true)
	private Date fechaTarnsaccion;

	@Column(name = "monto", nullable = true)
	private Double monto;

	@Column(name = "autorizacion", nullable = true)
	private String autorizacion;

	@Column(name = "metodo", nullable = true)
	private String metodo;

	@Column(name = "tarjeta", nullable = true)
	private String tarjeta;

	@Column(name = "id_openpay", nullable = true)
	private String idOpenPay;

	@Column(name = "fecha_creacion", nullable = true)
	private Date fechaCreacion;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@Column(name = "id_order", nullable = true)
	private String idOrder;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_estatus_transaccion", nullable = true)
	private EstatusPagos estatusPago;

	@Column(name = "rest_response", nullable = true)
	private String restResponse;

	@Column(name = "id_transaccion_midas", nullable = true)
	private Long idTransaccionMidas;

	@Column(name = "folio_partida", nullable = true)
	private Long folioPartida;

	@Column(name = "id_operacion", nullable = true)
	private Integer idOperacion;

	@Column(name = "id_tipo_autorizacion", nullable = true)
	private Integer idTipoAutorizacion;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTarnsaccion() {
		return fechaTarnsaccion;
	}

	public void setFechaTarnsaccion(Date fechaTarnsaccion) {
		this.fechaTarnsaccion = fechaTarnsaccion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusPagos getEstatusPago() {
		return estatusPago;
	}

	public void setEstatusPago(EstatusPagos estatusPago) {
		this.estatusPago = estatusPago;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getIdOpenPay() {
		return idOpenPay;
	}

	public void setIdOpenPay(String idOpenPay) {
		this.idOpenPay = idOpenPay;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getRestResponse() {
		return restResponse;
	}

	public void setRestResponse(String restResponse) {
		this.restResponse = restResponse;
	}

	public Long getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(Long idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
	}

	public Long getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Long folioPartida) {
		this.folioPartida = folioPartida;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdTipoAutorizacion() {
		return idTipoAutorizacion;
	}

	public void setIdTipoAutorizacion(Integer idTipoAutorizacion) {
		this.idTipoAutorizacion = idTipoAutorizacion;
	}

	@Override
	public String toString() {
		return "Pago [id=" + id + ", cliente=" + cliente + ", fechaTarnsaccion=" + fechaTarnsaccion + ", monto=" + monto
				+ ", autorizacion=" + autorizacion + ", metodo=" + metodo + ", tarjeta=" + tarjeta + ", idOpenPay="
				+ idOpenPay + ", fechaCreacion=" + fechaCreacion + ", descripcion=" + descripcion + ", idOrder="
				+ idOrder + ", estatusPago=" + estatusPago + ", restResponse=" + restResponse + ", idTransaccionMidas="
				+ idTransaccionMidas + ", folioPartida=" + folioPartida + ", idOperacion=" + idOperacion
				+ ", idTipoAutorizacion=" + idTipoAutorizacion + "]";
	}

}
