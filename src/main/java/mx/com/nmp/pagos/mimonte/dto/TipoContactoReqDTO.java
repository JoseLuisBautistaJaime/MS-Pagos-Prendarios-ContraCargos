package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: TipoContactoReqDTO Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 06/03/2019 21:11 hrs.
 * @version 0.1
 */
public class TipoContactoReqDTO {
	
	private Integer id;
	
	public TipoContactoReqDTO() {
		super();
	}

	public TipoContactoReqDTO(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TipoContactoReqDTO [id=" + id + "]";
	}
}
