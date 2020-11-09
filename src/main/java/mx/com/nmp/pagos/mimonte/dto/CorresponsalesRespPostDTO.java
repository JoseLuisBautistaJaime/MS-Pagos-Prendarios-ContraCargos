/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CorresponsalesRespPostDTO
 * @description Clase que encapsula la informacion perteneciente a un
 *              objeto corresponsal del catalogo de tk_proveedores.
 *
 * @author Daniel Hernandez danhernandez@quarksoft.net
 * @creationDate 06/11/2020.
 * @version 0.1
 */
public class CorresponsalesRespPostDTO implements Comparable<CorresponsalesRespPostDTO> {

	private String nombre;
	private String descripcion;

	public CorresponsalesRespPostDTO() {
		super();
	}

	public CorresponsalesRespPostDTO(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	public String toString() {
		return "CorresponsalesRespPostDTO [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(CorresponsalesRespPostDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
