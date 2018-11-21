package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: Tarjeta
 * Descripcion: Clase que encapsula la informacion perteneciente a una Tarjeta.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 16/11/2018 13:27 hrs.
 * @version 0.1
 */
public class TarjetaDTO {

	private ClienteDTO cliente;
	private String idTarjeta;
	private String token;
	private String alias;
	private Integer digitos;
	private TipoTarjetaDTO tipo;
	private EstatusTarjetaDTO estatus;
	
	public TarjetaDTO() {
		super();
	}

	public TarjetaDTO(TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus, String idTarjeta, String token, String alias, Integer digitos) {
		this.tipo = tipo;
		this.estatus = estatus;
		this.idTarjeta = idTarjeta;
		this.token = token;
		this.alias = alias;
		this.digitos = digitos;
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

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
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

	public Integer getDigitos() {
		return digitos;
	}

	public void setDigitos(Integer digitos) {
		this.digitos = digitos;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "TarjetaDTO [cliente=" + cliente + ", idTarjeta=" + idTarjeta + ", token=" + token + ", alias=" + alias
				+ ", digitos=" + digitos + ", tipo=" + tipo + ", estatus=" + estatus + "]";
	}
	
}
