package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: EstatusPagoDTO
 * Descripcion: Clase que encapsula la informacion perteneciente al catalogo de estatus de pagos.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 21/11/2018 13:45 hrs.
 * @version 0.1
 */
public class EstatusPagoDTO {

	private Integer id;
	private String descripcion;
	private String descripcionCorta;
	
	public EstatusPagoDTO() {
		super();
	}
	
	public EstatusPagoDTO(Integer id, String descripcion, String descripcionCorta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		return "EstatusPagoDTO [id=" + id + ", descripcion=" + descripcion + ", descripcionCorta="
				+ descripcionCorta + "]";
	}
	
}
