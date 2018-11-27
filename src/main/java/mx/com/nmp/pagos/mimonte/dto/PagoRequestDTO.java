package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * Nombre: PagoRequestDTO
 * Descripcion: Clase que encapsula la informacion perteneciente a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 20/11/2018 12:56 hrs.
 * @version 0.1
 */
public class PagoRequestDTO {

	private List<OperacionDTO> operaciones;
	private TarjetaPagoDTO tarjeta;
	private Integer montoTotal;
	private Boolean guardaTarjeta;
	private Integer idCliente;
	private String concepto;
	
	public PagoRequestDTO() {
		super();
	}

	public PagoRequestDTO(List<OperacionDTO> operaciones, TarjetaPagoDTO tarjeta, Integer montoTotal, Boolean guardaTarjeta,
			Integer idCliente, String concepto) {
		super();
		this.operaciones = operaciones;
		this.tarjeta = tarjeta;
		this.montoTotal = montoTotal;
		this.guardaTarjeta = guardaTarjeta;
		this.idCliente = idCliente;
		this.concepto = concepto;
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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "PagoRequestDTO [operaciones=" + operaciones + ", tarjeta=" + tarjeta + ", montoTotal=" + montoTotal
				+ ", guardaTarjeta=" + guardaTarjeta + ", idCliente=" + idCliente + ", concepto=" + concepto + "]";
	}
	
}
