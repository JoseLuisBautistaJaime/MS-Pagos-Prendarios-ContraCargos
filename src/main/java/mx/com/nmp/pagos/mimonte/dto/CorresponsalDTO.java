/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CorresponsalDTO
 * @description Clase que encapsula la informacion perteneciente a un tipo de Corresponsal del catalogo.
 * @author Daniel Hernandez danhernandez@quarksoft.net
 * @creationDate 06/11/2020.
 * @version 0.1
 */
public class CorresponsalDTO implements Comparable<CorresponsalDTO> {

	private String nombre;
	private String descripcion;
	
	public CorresponsalDTO() {}

	public CorresponsalDTO(String nombre, String descripcion) {
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
		return "AfiliacionDTO [nombre=" + nombre + "descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(CorresponsalDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
