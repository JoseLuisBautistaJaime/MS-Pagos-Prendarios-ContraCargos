package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.List;

/**
 * Nombre: Tarjeta
 * Descripcion: Clase que encapsula la informacion perteneciente a una Tarjeta.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 16/11/2018 13:27 hrs.
 * @version 0.1
 */
public class TarjetaDTO {

	private String token;
	private String digitos;
	private String alias;
	private Date fechaAlta;
	private Date fechaModificacion;
	private List<ClienteDTO> clientes;
	private TipoTarjetaDTO tipo;
	private EstatusTarjetaDTO estatus;
	
	public TarjetaDTO() {
		super();
	}
	
	public TarjetaDTO(String token, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			List<ClienteDTO> clientes, TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus) {
		super();
		this.token = token;
		this.digitos = digitos;
		this.alias = alias;
		this.fechaAlta = fechaAlta;
		this.fechaModificacion = fechaModificacion;
		this.clientes = clientes;
		this.tipo = tipo;
		this.estatus = estatus;
	}



	public TipoTarjetaDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarjetaDTO tipo) {
		this.tipo = tipo;
	}

	public EstatusTarjetaDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusTarjetaDTO estatus) {
		this.estatus = estatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getDigitos() {
		return digitos;
	}

	public void setDigitos(String digitos) {
		this.digitos = digitos;
	}

	public List<ClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "TarjetaDTO [token=" + token + ", digitos=" + digitos + ", alias=" + alias + ", fechaAlta=" + fechaAlta
				+ ", fechaModificacion=" + fechaModificacion + ", clientes=" + clientes + ", tipo=" + tipo
				+ ", estatus=" + estatus + "]";
	}
	
}
