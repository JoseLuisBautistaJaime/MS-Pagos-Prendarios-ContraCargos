/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @name Categoria
 * @description Clase que encapsula la informacion de una entidad de catalogo no
 *              admistrable de tipo Categoria
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/03/2019 15:10 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_categoria")
public class Categoria implements Comparable<Categoria> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private Set<CodigoEstadoCuenta> codigoEstadoCuentaSet;

	public Categoria() {
		super();
	}

	public Categoria(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<CodigoEstadoCuenta> getCodigoEstadoCuentaSet() {
		return codigoEstadoCuentaSet;
	}

	public void setCodigoEstadoCuentaSet(Set<CodigoEstadoCuenta> codigoEstadoCuentaSet) {
		this.codigoEstadoCuentaSet = codigoEstadoCuentaSet;
	}

	@Override
	public int compareTo(Categoria o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
