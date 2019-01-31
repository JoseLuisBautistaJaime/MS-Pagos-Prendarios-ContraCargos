package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: ReglaNegocioResumenDTO Descripcion: Clase que encapsula la
 * informacion perteneciente a una Regla de Negocio Resumen.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioResumenDTO implements Comparable<ReglaNegocioResumenDTO> {

	private Integer id;
	private Integer idAfiliacion;
	private Boolean valido;
	private Integer tipo;

	public ReglaNegocioResumenDTO() {
		super();
	}

	public ReglaNegocioResumenDTO(Integer id, Integer idAfiliacion, Boolean valido, Integer tipo) {
		super();
		this.id = id;
		this.valido = valido;
		this.idAfiliacion = idAfiliacion;
		this.tipo = tipo;
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ReglaNegocioResumenDTO [id=" + id + ", valido=" + valido + ", idAfiliacion=" + idAfiliacion + ", tipo="
				+ tipo + "]";
	}

	@Override
	public int compareTo(ReglaNegocioResumenDTO o) {
		int val = 0;
		if (this.id > o.id)
			val = 1;
		else
			val = -1;
		return val;
	}

}
