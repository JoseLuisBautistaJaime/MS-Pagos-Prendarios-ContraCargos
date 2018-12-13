package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: ReglaNegocioDTO
 * Descripcion: Clase que encapsula la informacion
 * perteneciente a una Regla de Negocio.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioDTO {

	private AfiliacionDTO afliacion;
	private Integer id;
	private String nombre;
	private String descripcion;
	private String consulta;

	public ReglaNegocioDTO() {
		super();
	}

	public ReglaNegocioDTO(Integer id, String nombre, String descripcion, String consulta, AfiliacionDTO afliacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.afliacion = afliacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public AfiliacionDTO getAfliacion() {
		return afliacion;
	}

	public void setAfliacion(AfiliacionDTO afliacion) {
		this.afliacion = afliacion;
	}

	@Override
	public String toString() {
		return "ReglaNegocioDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", consulta="
				+ consulta + ", afliaciones=" + afliacion + "]";
	}

}
