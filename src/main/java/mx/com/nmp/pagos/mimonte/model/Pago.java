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
 * Nombre: Pago
 * Descripcion: Entidad que representa un pago dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 21/11/2018 13:36 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "pagos", schema = "compose")
public class Pago {
	
	public Pago() {
		super();
	}
	
	public Pago(Integer id, Cliente cliente, Date fechaTarnsaccion, Double monto, String autorizacion,
			String metodo, String tarjeta, String idOpenPay, Date fechaCreacion, String descripcion, String idOrder,
			EstatusPago estatusPago, String restResponse) {
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
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="id", nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name ="idcliente", nullable = true)
	private Cliente cliente;
	
	@Column(name ="fecha_transaccion", nullable = true)
	private Date fechaTarnsaccion;
	
	@Column(name ="monto", nullable = true)
	private Double monto;
	
	@Column(name ="autorizacion", nullable = true)
	private String autorizacion;
	
	@Column(name ="metodo", nullable = true)
	private String metodo;
	
	@Column(name ="tarjeta", nullable = true)
	private String tarjeta;
	
	@Column(name ="idopenpay", nullable = true)
	private String idOpenPay;	
	
	@Column(name ="fecha_creacion", nullable = true)
	private Date fechaCreacion;
	
	@Column(name ="descripcion", nullable = true)
	private String descripcion;
	
	@Column(name ="idorder", nullable = true)
	private String idOrder;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name ="estatus_transaccion", nullable = true)
	private EstatusPago estatusPago;
	
	@Column(name ="restresponse", nullable = true)
	private String restResponse;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EstatusPago getEstatusPago() {
		return estatusPago;
	}
	public void setEstatusPago(EstatusPago estatusPago) {
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

	@Override
	public String toString() {
		return "Pago [id=" + id + ", cliente=" + cliente + ", fechaTarnsaccion=" + fechaTarnsaccion + ", monto="
				+ monto + ", autorizacion=" + autorizacion + ", metodo=" + metodo + ", tarjeta=" + tarjeta
				+ ", idOpenPay=" + idOpenPay + ", fechaCreacion=" + fechaCreacion + ", descripcion=" + descripcion
				+ ", idOrder=" + idOrder + ", estatusPago=" + estatusPago+ ", restResponse="
				+ restResponse + "]";
	}
	
}
