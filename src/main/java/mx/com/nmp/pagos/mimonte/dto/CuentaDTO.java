package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class CuentaDTO extends AbstractCatalogoDTO {

	public CuentaDTO() {
		super();
	}

	public CuentaDTO(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion, Long usuarioCreador) {
		super(id, estatus, fechaCreacion, fechaModificacion, usuarioCreador);
	}

}
