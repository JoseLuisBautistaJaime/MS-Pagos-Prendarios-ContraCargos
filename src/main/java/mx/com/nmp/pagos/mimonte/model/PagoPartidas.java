/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @name PagoPartidas
 * @description Entidad que representa las partidas correspondientes a un pago
 *              dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/07/2019 11:54 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "to_pagos_partidas")
public class PagoPartidas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "folio_partida", nullable = false)
	private Long folioPartida;

	@Column(name = "id_operacion", nullable = false)
	private Integer idOperacion;

	@Column(name = "nombre_operacion", nullable = true)
	private String nombreOperacion;

	@Column(name = "monto", nullable = false)
	private BigDecimal monto;

	@ManyToOne
	@JoinColumn(name = "id_pago", nullable = false)
	private Pago pagos;

	public PagoPartidas() {
		super();
	}

	public PagoPartidas(Long id, Long folioPartida, Integer idOperacion, String nombreOperacion, BigDecimal monto,
			Pago pagos) {
		super();
		this.id = id;
		this.folioPartida = folioPartida;
		this.idOperacion = idOperacion;
		this.nombreOperacion = nombreOperacion;
		this.monto = monto;
		this.pagos = pagos;
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

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Pago getPagos() {
		return pagos;
	}

	public void setPagos(Pago pagos) {
		this.pagos = pagos;
	}

	@Override
	public String toString() {
		return "PagoPartidas [id=" + id + ", folioPartida=" + folioPartida + ", idOperacion=" + idOperacion
				+ ", nombreOperacion=" + nombreOperacion + ", monto=" + monto + ", pagos=" + pagos + "]";
	}

}
