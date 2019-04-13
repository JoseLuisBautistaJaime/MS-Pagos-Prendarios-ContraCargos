/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ComisionesTransDTO
 * @description Clase que encapsula la información de una conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 12/04/2019 23:00 hrs.
 * @version 0.1
 */
public class ComisionesTransDTO implements Comparable<ComisionesTransDTO> {

	private ComisionesTransProyeccionDTO proyeccion;

	public ComisionesTransDTO() {
		super();
	}

	public ComisionesTransDTO(ComisionesTransProyeccionDTO proyeccion) {
		super();
		this.proyeccion = proyeccion;
	}

	public ComisionesTransProyeccionDTO getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(ComisionesTransProyeccionDTO proyeccion) {
		this.proyeccion = proyeccion;
	}

	@Override
	public String toString() {
		return "ComisionesTransDTO [proyeccion=" + proyeccion + "]";
	}

	@Override
	public int compareTo(ComisionesTransDTO o) {
		return 0;
	}

}
