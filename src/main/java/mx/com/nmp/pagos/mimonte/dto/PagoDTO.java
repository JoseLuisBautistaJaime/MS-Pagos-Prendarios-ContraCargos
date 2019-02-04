package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: PagoDTO
 * Descripcion: Clase que encapsula la informacion perteneciente a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @Fecha 21/11/2018 13:25 hrs.
 * @version 0.1
 */
public class PagoDTO {

	private ClienteDTO cliente;
	private TarjetaDTO tarjeta;
	private EstatusPagoDTO estatusPago;
	private Date fechaCreacion;
	private Date fechaTarnsaccion;
	private Double monto;
	private Long id;
	private String autorizacion;
	private String metodo;
	private String idOpenPay;
	private String descripcion;
	private String idOrder;
	private String restResponse;
	
	public PagoDTO() {
		super();
	}
	
	public PagoDTO(ClienteDTO cliente, TarjetaDTO tarjeta, Date fechaCreacion, Date fechaTarnsaccion,
			Double monto, Long id, EstatusPagoDTO estatusPago, String autorizacion, String metodo, String idOpenPay,
			String descripcion, String idOrder, String restResponse) {
		super();
		this.cliente = cliente;
		this.tarjeta = tarjeta;
		this.fechaCreacion = fechaCreacion;
		this.fechaTarnsaccion = fechaTarnsaccion;
		this.monto = monto;
		this.id = id;
		this.estatusPago = estatusPago;
		this.autorizacion = autorizacion;
		this.metodo = metodo;
		this.idOpenPay = idOpenPay;
		this.descripcion = descripcion;
		this.idOrder = idOrder;
		this.restResponse = restResponse;
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
	
	public EstatusPagoDTO getEstatusPago() {
		return estatusPago;
	}

	public void setEstatusPago(EstatusPagoDTO estatusPago) {
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
		return "PagoDTO [cliente=" + cliente + ", tarjeta=" + tarjeta + ", fechaCreacion=" + fechaCreacion
				+ ", fechaTarnsaccion=" + fechaTarnsaccion + ", monto=" + monto + ", id=" + id + ", estatusPago="
				+ estatusPago + ", autorizacion=" + autorizacion + ", metodo=" + metodo + ", idOpenPay="
				+ idOpenPay + ", descripcion=" + descripcion + ", idOrder=" + idOrder + ", restResponse=" + restResponse
				+ "]";
	}
	
}
