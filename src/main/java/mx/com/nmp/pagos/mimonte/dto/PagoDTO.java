/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name PagoDTO
 * @description Clase que encapsula la informacion perteneciente a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/11/2018 13:25 hrs.
 * @version 0.1
 */
public class PagoDTO {

	private Long id;
	private ClienteDTO cliente;
	private TarjetaDTO tarjeta;
	private EstatusPagoDTO estatusPago;
	private Date fechaCreacion;
	private Date fechaTarnsaccion;
	private BigDecimal monto;
	private String concepto;
	private Long idTransaccionMidas;

	public PagoDTO() {
		super();
	}

	public PagoDTO(Long id, ClienteDTO cliente, TarjetaDTO tarjeta, EstatusPagoDTO estatusPago, Date fechaCreacion,
			Date fechaTarnsaccion, BigDecimal monto, String concepto, Long idTransaccionMidas) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.tarjeta = tarjeta;
		this.estatusPago = estatusPago;
		this.fechaCreacion = fechaCreacion;
		this.fechaTarnsaccion = fechaTarnsaccion;
		this.monto = monto;
		this.concepto = concepto;
		this.idTransaccionMidas = idTransaccionMidas;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public TarjetaDTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaDTO tarjeta) {
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

	public EstatusPagoDTO getEstatusPago() {
		return estatusPago;
	}

	public void setEstatusPago(EstatusPagoDTO estatusPago) {
		this.estatusPago = estatusPago;
	}

	public Long getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(Long idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Override
	public String toString() {
		return "PagoDTO [id=" + id + ", cliente=" + cliente + ", tarjeta=" + tarjeta + ", estatusPago=" + estatusPago
				+ ", fechaCreacion=" + fechaCreacion + ", fechaTarnsaccion=" + fechaTarnsaccion + ", monto=" + monto
				+ ", concepto=" + concepto + ", idTransaccionMidas=" + idTransaccionMidas + "]";
	}

}
