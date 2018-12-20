package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Nombre: Variable
 * Descripcion: Entidad que representa una variable de base de
 * datos dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/12/2018 13:15 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "variable")
public class Variable {

	@Id
	@GeneratedValue()
	@Column(name = "id_variable", nullable = false, unique = true)
	private Integer idVariable;

	@Column(name = "clave", nullable = false)
	private String clave;

	@Column(name = "valor", nullable = false)
	private String valor;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "regla_negocio_variable", joinColumns = {
			@JoinColumn(name = "id_variable", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_regla_negocio", nullable = false) })
	private Set<ReglaNegocio> reglasNegocio;

	public Variable() {
		super();
	}

	public Variable(Integer idVariable, String clave, String valor, Set<ReglaNegocio> reglasNegocio) {
		super();
		this.idVariable = idVariable;
		this.clave = clave;
		this.valor = valor;
		this.reglasNegocio = reglasNegocio;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Set<ReglaNegocio> getReglasNegocio() {
		return reglasNegocio;
	}

	public void setReglasNegocio(Set<ReglaNegocio> reglasNegocio) {
		this.reglasNegocio = reglasNegocio;
	}

	@Override
	public String toString() {
		return "Variable [idVariable=" + idVariable + ", clave=" + clave + ", valor=" + valor + ", reglasNegocio="
				+ reglasNegocio + "]";
	}

}
