package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Nombre: Cliente
 * Descripcion: Clase que encapsula la informacion perteneciente a un cliente.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 21/11/2018 12:00 hrs.
 * @version 0.1
 */
public class ClienteDTO {

	private Integer idCliente;
	private String nombreTitular;
	private Date fechaAlta;
	private Set<TransaccionDTO> transacciones;
	private List<TarjetaDTO> tarjetas;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer idCliente, String nombreTitular, Date fechaAlta, Set<TransaccionDTO> transacciones,
			List<TarjetaDTO> tarjetas) {
		super();
		this.idCliente = idCliente;
		this.nombreTitular = nombreTitular;
		this.fechaAlta = fechaAlta;
		this.transacciones = transacciones;
		this.tarjetas = tarjetas;
	}



	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Set<TransaccionDTO> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<TransaccionDTO> transacciones) {
		this.transacciones = transacciones;
	}

	public List<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nombreTitular=" + nombreTitular + ", fechaAlta=" + fechaAlta
				+ ", transacciones=" + transacciones + ", tarjetas=" + tarjetas + "]";
	}
	
}
