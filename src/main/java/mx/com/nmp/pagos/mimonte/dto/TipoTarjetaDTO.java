package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TipoTarjeta Descripcion: Clase que encapsula la informacion
 * perteneciente a un Tipo de Tarjeta.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 16/11/2018 13:18 hrs.
 * @version 0.1
 */
public class TipoTarjetaDTO {

	private String id;
	private String descripcion;
	private String descripcionCorta;
	
	public TipoTarjetaDTO(String id, String descripcion, String descripcionCorta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}

	public TipoTarjetaDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	@Override
	public String toString() {
		return "TipoTarjetaDTO [id=" + id + ", descripcion=" + descripcion + ", descripcionCorta=" + descripcionCorta
				+ "]";
	}

}
