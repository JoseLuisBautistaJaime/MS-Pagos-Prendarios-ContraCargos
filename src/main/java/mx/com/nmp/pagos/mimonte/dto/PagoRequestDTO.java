/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name PagoRequestDTO
 * @description Clase que encapsula la informacion perteneciente a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/11/2018 12:56 hrs.
 * @version 0.1
 */
public class PagoRequestDTO {

	private List<OperacionDTO> operaciones;
	private TarjetaPagoDTO tarjeta;
	private BigDecimal montoTotal;
	private Boolean guardaTarjeta;
	private Long idCliente;
	private String concepto;
	private String idTransaccionMidas;

	public PagoRequestDTO() {
		super();
	}

	public PagoRequestDTO(List<OperacionDTO> operaciones, TarjetaPagoDTO tarjeta, BigDecimal montoTotal,
			Boolean guardaTarjeta, Long idCliente, String concepto, String idTransaccionMidas) {
		super();
		this.operaciones = operaciones;
		this.tarjeta = tarjeta;
		this.montoTotal = montoTotal;
		this.guardaTarjeta = guardaTarjeta;
		this.idCliente = idCliente;
		this.concepto = concepto;
		this.idTransaccionMidas = idTransaccionMidas;
	}

	public List<OperacionDTO> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<OperacionDTO> operaciones) {
		this.operaciones = operaciones;
	}

	public TarjetaPagoDTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaPagoDTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Boolean getGuardaTarjeta() {
		return guardaTarjeta;
	}

	public void setGuardaTarjeta(Boolean guardaTarjeta) {
		this.guardaTarjeta = guardaTarjeta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(String idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
	}

	@Override
	public String toString() {
		return "PagoRequestDTO [operaciones=" + operaciones + ", tarjeta=" + tarjeta + ", montoTotal=" + montoTotal
				+ ", guardaTarjeta=" + guardaTarjeta + ", idCliente=" + idCliente + ", concepto=" + concepto
				+ ", idTransaccion=" + idTransaccionMidas + "]";
	}

}
