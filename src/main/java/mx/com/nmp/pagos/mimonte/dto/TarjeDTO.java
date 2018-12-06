package mx.com.nmp.pagos.mimonte.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * Nombre: TarjeDTO
 * Descripcion: Clase que mantiene la informacion generica sobre un registro perteneciente a una tarjeta
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 05/12/2018 17:04 Hrs.
 * @version 0.1
 */
public class TarjeDTO extends AbstractTarjetaDTO implements Serializable {
	
	private static final long serialVersionUID = 4269680953481777058L;

	public TarjeDTO() {
		super();
	}

	public TarjeDTO(String token, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			TipoTarjetaDTO tipoTarjeta, EstatusTarjetaDTO estatusTarjeta) {
		super(token, digitos, alias, fechaAlta, fechaModificacion, tipoTarjeta, estatusTarjeta);

	}

}
