package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * Nombre: Pago
 * Descripcion: Clase que encapsula la informacion perteneciente a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 20/11/2018 12:56 hrs.
 * @version 0.1
 */
public class PagoDTO {

	private List<OperacionDTO> operaciones;
	private TarjetaDTO tarjeta;
	private Integer montoTotal;
	private String concepto;
	private Boolean guardaTarjeta;
	
	public PagoDTO() {
		super();
	}
	
	public PagoDTO(List<OperacionDTO> operaciones, TarjetaDTO tarjeta, Integer montoTotal, String concepto, Boolean guardaTarjeta) {
		this.operaciones = operaciones;
		this.tarjeta = tarjeta;
		this.montoTotal = montoTotal;
		this.concepto = concepto;
		this.guardaTarjeta = guardaTarjeta;
	}

	public List<OperacionDTO> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<OperacionDTO> operaciones) {
		this.operaciones = operaciones;
	}

	public TarjetaDTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaDTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Integer getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Integer montoTotal) {
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

	@Override
	public String toString() {
		return "RegistroPagoDTO [operaciones=" + operaciones + ", tarjeta=" + tarjeta + ", monto_total=" + montoTotal
				+ ", concepto=" + concepto + ", guardaTarjeta=" + guardaTarjeta + "]";
	}
	
}
