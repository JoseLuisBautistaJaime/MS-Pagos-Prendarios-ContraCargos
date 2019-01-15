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
import javax.validation.constraints.Size;

/**
 * Nombre: Pago Descripcion: Entidad que representa un pago dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 21/11/2018 13:36 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "pagos")
public class Pago {

	public Pago() {
		super();
	}

	public Pago(Integer id, Cliente cliente, Date fechaTarnsaccion, Double monto,
			@Size(max = 100, message = "El campo debe ser un valor alfanumerico de 1 a 100 caracteres") String autorizacion,
			@Size(min = 1, max = 45, message = "El campo debe ser un valor alfanumerico de 1 a 45 caracteres") String metodo,
			@Size(min = 4, max = 4, message = "El campo debe ser un valor de 4 caracteres") String tarjeta,
			@Size(min = 1, max = 4, message = "El campo debe ser un valor de 1 a 100 caracteres") String idOpenPay,
			Date fechaCreacion,
			@Size(min = 1, max = 200, message = "El campo debe ser un valor de 1 a 200 caracteres") String descripcion,
			@Size(min = 1, max = 100, message = "El campo debe ser un valor de 1 a 100 caracteres") String idOrder,
			EstatusPago estatusPago,
			@Size(min = 1, max = 400, message = "El campo debe ser un valor de 1 a 400 caracteres") String restResponse,
			Integer idTransaccionMidas, Integer folioPartida, Integer idOperacion) {
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
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idcliente", nullable = true)
	private Cliente cliente;

	@Column(name = "fecha_transaccion", nullable = true)
	private Date fechaTarnsaccion;

	@Column(name = "monto", nullable = true)
	private Double monto;

	@Size(max = 100, message = "El campo debe ser un valor alfanumerico de 1 a 100 caracteres")
	@Column(name = "autorizacion", nullable = true)
	private String autorizacion;

	@Size(min = 1, max = 45, message = "El campo debe ser un valor alfanumerico de 1 a 45 caracteres")
	@Column(name = "metodo", nullable = true)
	private String metodo;

	@Size(min = 4, max = 4, message = "El campo debe ser un valor de 4 caracteres")
	@Column(name = "tarjeta", nullable = true)
	private String tarjeta;

	@Size(min = 1, max = 4, message = "El campo debe ser un valor de 1 a 100 caracteres")
	@Column(name = "idopenpay", nullable = true)
	private String idOpenPay;

	@Column(name = "fecha_creacion", nullable = true)
	private Date fechaCreacion;

	@Size(min = 1, max = 200, message = "El campo debe ser un valor de 1 a 200 caracteres")
	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@Size(min = 1, max = 100, message = "El campo debe ser un valor de 1 a 100 caracteres")
	@Column(name = "idorder", nullable = true)
	private String idOrder;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "estatus_transaccion", nullable = true)
	private EstatusPago estatusPago;

	@Size(min = 1, max = 400, message = "El campo debe ser un valor de 1 a 400 caracteres")
	@Column(name = "restresponse", nullable = true)
	private String restResponse;

	@Column(name = "id_transaccion_midas", nullable = true)
	private Integer idTransaccionMidas;
	
	@Column(name = "folio_partida", nullable = true)
	private Integer folioPartida;
	
	@Column(name = "id_operacion", nullable = true)
	private Integer idOperacion;

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

	public Integer getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(Integer idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
	}
	
	public Integer getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Integer folioPartida) {
		this.folioPartida = folioPartida;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	@Override
	public String toString() {
		return "Pago [id=" + id + ", cliente=" + cliente + ", fechaTarnsaccion=" + fechaTarnsaccion + ", monto=" + monto
				+ ", autorizacion=" + autorizacion + ", metodo=" + metodo + ", tarjeta=" + tarjeta + ", idOpenPay="
				+ idOpenPay + ", fechaCreacion=" + fechaCreacion + ", descripcion=" + descripcion + ", idOrder="
				+ idOrder + ", estatusPago=" + estatusPago + ", restResponse=" + restResponse + ", idTransaccionMidas="
				+ idTransaccionMidas + ", folioPartida=" + folioPartida + ", idOperacion=" + idOperacion + "]";
	}

}
