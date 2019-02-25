package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.List;

/**
 * Nombre: TarjetaDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a una Tarjeta.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/11/2018 13:27 hrs.
 * @version 0.1
 */
public class TarjetaDTO extends AbstractTarjetaDTO {

	private ClienteDTO cliente;

	public TarjetaDTO() {
		super();
	}

	public TarjetaDTO(String id_openpay, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			ClienteDTO cliente, TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus,String token) {
		super(id_openpay, digitos, alias, fechaAlta, fechaModificacion, tipo, estatus, token);
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "TarjetaDTO [cliente=" + cliente + ", id_openpay=" + id_openpay + ", digitos=" + digitos + ", alias=" + alias
				+ ", fechaAlta=" + fechaAlta + ", fechaModificacion=" + fechaModificacion + ", tipo=" + tipo
				+ ", estatus=" + estatus + ", token=" + token + "]";
	}
	
}
