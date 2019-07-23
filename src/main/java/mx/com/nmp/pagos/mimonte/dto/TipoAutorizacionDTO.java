package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name TipoAutorizacionDTO
 * @description Clase que encapsula la informacion perteneciente a un tipo de
 *              autorizacion de un catalogo de afiliacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @Fecha 01/02/2019 11:58 hrs.
 * @version 0.1
 */
public class TipoAutorizacionDTO implements Comparable<TipoAutorizacionDTO>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public TipoAutorizacionDTO() {
		super();
	}

	public TipoAutorizacionDTO(Integer id, String descripcion) {
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

	@Override
	public int compareTo(TipoAutorizacionDTO o) {
		return o.id.compareTo(this.id);
	}

}
