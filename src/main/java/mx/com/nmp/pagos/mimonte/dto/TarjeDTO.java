package mx.com.nmp.pagos.mimonte.dto;

import java.io.Serializable;
import java.util.Date;

public class TarjeDTO  extends AbstractTarjetaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4269680953481777058L;

	public TarjeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TarjeDTO(String token, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			TipoTarjetaDTO tipoTarjeta, EstatusTarjetaDTO estatusTarjeta) {
		super(token, digitos, alias, fechaAlta, fechaModificacion, tipoTarjeta, estatusTarjeta);
		
	}

	

}
