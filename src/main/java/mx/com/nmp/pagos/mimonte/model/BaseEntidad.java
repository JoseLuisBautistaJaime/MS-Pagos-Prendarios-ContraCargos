/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

/**
 * @name BaseEntidad 
 * @description Clase que encapsula la informacion de una
 * entidad de catalogo de tipo BaseEntidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:09 hrs.
 * @version 0.1
 */
public class BaseEntidad implements Comparable<BaseEntidad> {

	private Long id;
	private String nombre;
	private String descripcion;

	public BaseEntidad() {
		super();
	}

	public BaseEntidad(Long id, String nombre, String descripcion) {
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

	@Override
	public int compareTo(BaseEntidad o) {
		return o.getNombre().compareTo(this.nombre);
	}

	@Override
	public String toString() {
		return "BaseEntidad [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
