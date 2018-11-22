package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

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
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer idCliente, String nombreTitular, Date fechaAlta) {
		super();
		this.idCliente = idCliente;
		this.nombreTitular = nombreTitular;
		this.fechaAlta = fechaAlta;
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
	
}
