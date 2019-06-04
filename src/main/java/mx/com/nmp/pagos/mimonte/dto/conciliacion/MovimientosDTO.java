/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name MovimientosDTO
 * @description Clase que encapsula el request de MovimientosDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:05 hrs.
 * @version 0.1
 */
public class MovimientosDTO implements Comparable<MovimientosDTO> {

	private Integer id;
	private Date fecha;

	public MovimientosDTO() {
		super();
	}

	public MovimientosDTO(Integer id, Date fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "MovimientosDTO [id=" + id + ", fecha=" + fecha + "]";
	}

	@Override
	public int compareTo(MovimientosDTO o) {
		return 0;
	}

}
