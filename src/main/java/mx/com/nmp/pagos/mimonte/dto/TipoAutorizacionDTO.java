package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TipoAutorizacionDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a un tipo de autorizacion de un catalogo de afiliacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @Fecha 01/02/2019 11:58 hrs.
 * @version 0.1
 */
public class TipoAutorizacionDTO {

	public Integer getId() {
		return id;
	}

	public TipoAutorizacionDTO() {
		super();
	}

	public TipoAutorizacionDTO(Integer id, String descripcion, String descripcionCorta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	private Integer id;
	private String descripcion;

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoAutorizacionDTO [id=" + id + ", descripcion=" + descripcion;
	}

}
