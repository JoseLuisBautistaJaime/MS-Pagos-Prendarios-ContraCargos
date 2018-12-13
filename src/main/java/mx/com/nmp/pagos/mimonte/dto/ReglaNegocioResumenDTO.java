package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: ReglaNegocioResumenDTO
 * Descripcion: Clase que encapsula la
 * informacion perteneciente a una Regla de Negocio Resumen.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioResumenDTO {

	private Integer id;
	private Integer idAfiliacion;
	private Boolean valido;
	
	public ReglaNegocioResumenDTO() {
		super();
	}
	
	public ReglaNegocioResumenDTO(Integer id, Boolean valido, Integer idAfiliacion) {
		super();
		this.id = id;
		this.valido = valido;
		this.idAfiliacion = idAfiliacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public Integer getIdAfiliacion() {
		return idAfiliacion;
	}

	public void setIdAfiliacion(Integer idAfiliacion) {
		this.idAfiliacion = idAfiliacion;
	}

	@Override
	public String toString() {
		return "ReglaNegocioResumenDTO [id=" + id + ", valido=" + valido + ", idAfiliacion=" + idAfiliacion + "]";
	}
	
}
