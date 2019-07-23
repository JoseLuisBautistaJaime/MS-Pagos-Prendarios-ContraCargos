package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * Nombre: ReglaNegocioDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a una Regla de Negocio.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioDTO implements Comparable<ReglaNegocioDTO> {

	private Integer id;
	private String nombre;
	private String descripcion;
	private String consulta;
	private Set<VariableDTO> variables;
	private Set<TipoAutorizacionDTO> tipoAutorizacionSet;

	public ReglaNegocioDTO() {
		super();
	}

	public ReglaNegocioDTO(Integer id, String nombre, String descripcion, String consulta, Set<VariableDTO> variables,
			Set<TipoAutorizacionDTO> tipoAutorizacionSet) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.variables = variables;
		this.tipoAutorizacionSet = tipoAutorizacionSet;
	}

	public ReglaNegocioDTO(Integer id, String nombre, String descripcion, String consulta, Set<VariableDTO> variables) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.variables = variables;
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

	public Set<VariableDTO> getVariables() {
		return variables;
	}

	public void setVariables(Set<VariableDTO> variables) {
		this.variables = variables;
	}

	public Set<TipoAutorizacionDTO> getTipoAutorizacionSet() {
		return tipoAutorizacionSet;
	}

	public void setTipoAutorizacionSet(Set<TipoAutorizacionDTO> tipoAutorizacionSet) {
		this.tipoAutorizacionSet = tipoAutorizacionSet;
	}

	@Override
	public String toString() {
		return "ReglaNegocioDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", consulta="
				+ consulta + ", variables=" + variables + ", tipoAutorizacionSet=" + tipoAutorizacionSet + "]";
	}

	@Override
	public int compareTo(ReglaNegocioDTO o) {
		int val = 0;
		if (this.id > o.id)
			val = 1;
		else
			val = -1;
		return val;
	}

}
