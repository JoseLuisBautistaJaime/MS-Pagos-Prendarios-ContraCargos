package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * Nombre: ReglaNegocio
 * Descripcion: Entidad que representa una regla de negocio
 * dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 12/12/2018 16:58 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_regla_negocio")
@SqlResultSetMapping(name = "ReglaNegocioResumenDTO", classes = {
		@ConstructorResult(targetClass = mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "idAfiliacion", type = Integer.class),
				@ColumnResult(name = "valido", type = Boolean.class) }) })
@NamedNativeQueries({
		@NamedNativeQuery(name = "ReglaNegocio.execQuery", query = "query", resultSetMapping = "ReglaNegocioResumenDTO") })
public class ReglaNegocio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "consulta", nullable = false)
	private String consulta;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_afiliacion", nullable = false)
	private Afiliacion afiliacion;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reglasNegocio")
	private Set<Cliente> clientes;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "reglasNegocio")
	private Set<Variable> variables;

	public ReglaNegocio() {
		/**
		 * empty constructor
		 */
	}

	public ReglaNegocio(Integer id, String nombre, String descripcion, String consulta, Afiliacion afiliacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.afiliacion = afiliacion;
	}

	public ReglaNegocio(Integer id, String nombre, String descripcion, String consulta, Afiliacion afiliacion,
			Set<Cliente> clientes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.afiliacion = afiliacion;
		this.clientes = clientes;
	}

	public ReglaNegocio(Integer id, String nombre, String descripcion, String consulta, Afiliacion afiliacion,
			Set<Cliente> clientes, Set<Variable> variables) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.afiliacion = afiliacion;
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

	public Afiliacion getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliaciones(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}

	public Set<Variable> getVariables() {
		return variables;
	}

	public void setVariables(Set<Variable> variables) {
		this.variables = variables;
	}

	@Override
	public String toString() {
		return "ReglaNegocio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", consulta="
				+ consulta + ", afiliacion=" + afiliacion + ", clientes=" + clientes + ", variables=" + variables + "]";
	}

}
