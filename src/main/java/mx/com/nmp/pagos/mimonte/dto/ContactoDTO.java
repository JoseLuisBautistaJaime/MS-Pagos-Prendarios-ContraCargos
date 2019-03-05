package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class ContactoDTO extends AbstractCatalogoDTO {

	public ContactoDTO() {
		super();
	}

	public ContactoDTO(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion, Long usuarioCreador) {
		super(id, estatus, fechaCreacion, fechaModificacion, usuarioCreador);
	}

}
