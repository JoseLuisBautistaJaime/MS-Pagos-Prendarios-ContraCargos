package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class TarjetaPagoDTO extends AbstractTarjetaDTO {

	public TarjetaPagoDTO() {
		super();
	}

	public TarjetaPagoDTO(String token, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus) {
		super(token,digitos,alias,fechaAlta,fechaModificacion,tipo,estatus);
	}
}
