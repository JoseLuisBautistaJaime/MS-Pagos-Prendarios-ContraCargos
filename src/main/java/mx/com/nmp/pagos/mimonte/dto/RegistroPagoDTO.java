package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

public class RegistroPagoDTO {

	private List<OperacionDTO> operaciones;
	private TarjetaDTO tarjeta;
	private Integer montoTotal;
	private String concepto;
	private Boolean guardaTarjeta;
	
	public RegistroPagoDTO() {
		super();
	}
	
	public RegistroPagoDTO(List<OperacionDTO> operaciones, TarjetaDTO tarjeta, Integer montoTotal, String concepto, Boolean guardaTarjeta) {
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
