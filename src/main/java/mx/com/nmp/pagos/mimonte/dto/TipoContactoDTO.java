package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: TipoContactoDTO Descripcion: Clase que encapsula la informacion perteneciente al tipo de contacto.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 05/03/2019 14:48 hrs.
 * @version 0.1
 */
public class TipoContactoDTO extends AbstractCatalogoDTO{

	private String descripcion;
	
	public TipoContactoDTO() {
		super();
		
	}

	public TipoContactoDTO(String descripcion, Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion,
			Long usuarioCreador) {
		super(id, estatus, fechaCreacion, fechaModificacion, usuarioCreador);
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoContactoDTO [descripcion=" + descripcion + ", id=" + id + ", estatus=" + estatus
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioCreador="
				+ usuarioCreador + "]";
	}

}
