package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;

/**
 * Nombre: Transaccion
 * Descripcion: Entidad que representa una transaccion dentro del sistema.
 *
 * @author Ismael Flores Aguilar iaguilar@quarksoft.net
 * Fecha: 21/11/2018 13:36 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "transacciones")
public class Transaccion {

	@Column(name ="", nullable = true)
	private ClienteDTO cliente;
	
	@Column(name ="", nullable = true)
	private TarjetaDTO tarjeta;
	
	@Column(name ="", nullable = true)
	private Date fechaCreacion;
	
	@Column(name ="", nullable = true)
	private Date fechaTarnsaccion;
	
	@Column(name ="", nullable = true)
	private Double monto;
	
	@Id
	@GeneratedValue
	@Column(name ="", nullable = false, unique = true)
	private Integer id;
	
	@Column(name ="", nullable = true)
	private Integer estatusTransaccion;
	
	@Column(name ="", nullable = true)
	private String autorizacion;
	
	@Column(name ="", nullable = true)
	private String metodo;
	
	@Column(name ="", nullable = true)
	private String idOpenPay;
	
	@Column(name ="", nullable = true)
	private String descripcion;
	
	@Column(name ="", nullable = true)
	private String idOrder;
	
	@Column(name ="", nullable = true)
	private String restResponse;
	
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
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEstatusTransaccion() {
		return estatusTransaccion;
	}
	public void setEstatusTransaccion(Integer estatusTransaccion) {
		this.estatusTransaccion = estatusTransaccion;
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

}
