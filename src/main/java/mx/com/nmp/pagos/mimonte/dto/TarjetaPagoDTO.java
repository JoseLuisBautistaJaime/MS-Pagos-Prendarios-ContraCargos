package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class TarjetaPagoDTO extends AbstractTarjetaDTO {

	public TarjetaPagoDTO() {
		super();
	}

	public TarjetaPagoDTO(String id_openpay, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus,String token) {
		super(id_openpay,digitos,alias,fechaAlta,fechaModificacion,tipo,estatus,token);
	}
}
