/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EntidadDTO
 * @description Clase que encapsula la informacion de la entidad.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:22 hrs.
 * @version 0.1
 */
public class EntidadDTO extends AbstractConciliacionBasicaDTO implements Comparable<EntidadDTO>{
	
	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 8230001730570538735L;
	
	private String nombre;
	
	public EntidadDTO() {
		super();
	}

	public EntidadDTO(Long id, String descripcion, Boolean estatus, String nombre) {
		super(id, descripcion, estatus);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EntidadDTO [nombre=" + nombre + "]";
	}
	
	@Override
	public int compareTo(EntidadDTO o) {
		return 0;
	}
	
}
