package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: Cliente Descripcion: Clase que encapsula la informacion perteneciente
 * a un cliente.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 21/11/2018 12:00 hrs.
 * @version 0.1
 */
public class ClienteDTO implements Comparable<ClienteDTO> {

	private Long idCliente;
	private String nombreTitular;
	private Date fechaAlta;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Long idCliente, String nombreTitular, Date fechaAlta) {
		super();
		this.idCliente = idCliente;
		this.nombreTitular = nombreTitular;
		this.fechaAlta = fechaAlta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
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

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nombreTitular=" + nombreTitular + ", fechaAlta=" + fechaAlta
				+ "]";
	}

	@Override
	public int compareTo(ClienteDTO o) {
		int val = 0;
		if(this.idCliente > o.getIdCliente())
			val = 1;
		else
			val = -1;
		return val;
	}

}
