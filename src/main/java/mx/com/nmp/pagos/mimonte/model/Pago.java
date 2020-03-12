/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @name Pago
 * @description Entidad que representa un pago dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/11/2018 13:36 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "to_pagos")
public class Pago {

	public Pago() {
		super();
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

	@Column(name = "monto_total", nullable = true)
	private BigDecimal monto;

	@Column(name = "tarjeta", nullable = true)
	private String tarjeta;

	@Column(name = "fecha_creacion", nullable = true)
	private Date fechaCreacion;

	@Column(name = "concepto", nullable = true)
	private String concepto;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_estatus_transaccion", nullable = true)
	private EstatusPagos estatusPago;

	@Column(name = "id_transaccion_midas", nullable = true)
	private Long idTransaccionMidas;

	@Column(name = "id_tipo_autorizacion", nullable = true)
	private Integer idTipoAutorizacion;

	@OneToMany(mappedBy = "pagos", cascade = CascadeType.PERSIST)
	private List<PagoPartidas> pagoPartidasList;

	public Pago(Long id, Cliente cliente, Date fechaTarnsaccion, BigDecimal monto, String tarjeta, Date fechaCreacion,
			String concepto, EstatusPagos estatusPago, Long idTransaccionMidas, Integer idTipoAutorizacion,
			List<PagoPartidas> pagoPartidasList) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechaTarnsaccion = fechaTarnsaccion;
		this.monto = monto;
		this.tarjeta = tarjeta;
		this.fechaCreacion = fechaCreacion;
		this.concepto = concepto;
		this.estatusPago = estatusPago;
		this.idTransaccionMidas = idTransaccionMidas;
		this.idTipoAutorizacion = idTipoAutorizacion;
		this.pagoPartidasList = pagoPartidasList;
	}

	public List<PagoPartidas> getPagoPartidasList() {
		return pagoPartidasList;
	}

	public void setPagoPartidasList(List<PagoPartidas> pagoPartidasList) {
		this.pagoPartidasList = pagoPartidasList;
	}

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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
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

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(Long idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
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
				+ ", tarjeta=" + tarjeta + ", fechaCreacion=" + fechaCreacion + ", concepto=" + concepto
				+ ", estatusPago=" + estatusPago + ", idTransaccionMidas=" + idTransaccionMidas
				+ ", idTipoAutorizacion=" + idTipoAutorizacion + ", pagoPartidasList=" + pagoPartidasList + "]";
	}

}
