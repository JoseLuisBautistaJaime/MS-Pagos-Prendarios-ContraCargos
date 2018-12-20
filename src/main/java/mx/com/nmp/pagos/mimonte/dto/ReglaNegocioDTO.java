package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * Nombre: ReglaNegocioDTO
 * Descripcion: Clase que encapsula la informacion
 * perteneciente a una Regla de Negocio.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioDTO implements Comparable<ReglaNegocioDTO> {

	private AfiliacionDTO afliacion;
	private Integer id;
	private String nombre;
	private String descripcion;
	private String consulta;
	private Set<ClienteDTO> clientes;
	private Set<VariableDTO> variables;

	public ReglaNegocioDTO() {
		super();
	}

	public ReglaNegocioDTO(AfiliacionDTO afliacion, Integer id, String nombre, String descripcion, String consulta,
			Set<ClienteDTO> clientes, Set<VariableDTO> variables) {
		super();
		this.afliacion = afliacion;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.clientes = clientes;
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

	public AfiliacionDTO getAfliacion() {
		return afliacion;
	}

	public void setAfliacion(AfiliacionDTO afliacion) {
		this.afliacion = afliacion;
	}

	public Set<ClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(Set<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

	public Set<VariableDTO> getVariables() {
		return variables;
	}

	public void setVariables(Set<VariableDTO> variables) {
		this.variables = variables;
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
