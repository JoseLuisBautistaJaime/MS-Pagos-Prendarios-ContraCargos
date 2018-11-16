package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TipoTarjeta
 * Descripcion: Clase que encapsula la informacion perteneciente a un Tipo de Tarjeta.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 16/11/2018 13:18 hrs.
 * @version 0.1
 */
public class TipoTarjetaDTO {

	private String idTipo;
	private String descripcion;
	
	public TipoTarjetaDTO() {
		super();
	}
	
	public TipoTarjetaDTO(String idTipo, String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	
	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoTarjetaDTO [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}
	
}
