package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: Afiliacion
 * Descripcion: Entidad que representa un tipo de afiliacion
 * dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net Fecha: 12/12/2018 16:59 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "catalogo_afiliacion")
public class Afiliacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "afiliacion")
	private Set<ReglaNegocio> reglas;

	public Afiliacion() {
		/**
		 * empty constructor
		 */
	}

	public Afiliacion(Integer id, String descripcion, Set<ReglaNegocio> reglas) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.reglas = reglas;
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

	public Set<ReglaNegocio> getRegla() {
		return reglas;
	}

	public void setRegla(Set<ReglaNegocio> reglas) {
		this.reglas = reglas;
	}

	@Override
	public String toString() {
		return "Afiliacion [id=" + id + ", descripcion=" + descripcion + ", reglas=" + reglas + "]";
	}

}
