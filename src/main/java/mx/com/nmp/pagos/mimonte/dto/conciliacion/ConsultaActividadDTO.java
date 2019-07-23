/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ConsultaActividadDTO
 * @description Clase que encapsula el request de ConsultaActividadDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 22:18 hrs.
 * @version 0.1
 */
public class ConsultaActividadDTO implements Comparable<ConsultaActividadDTO> {

	private Integer folio;
	private Date fecha;
	private String descripcion;

	public ConsultaActividadDTO() {
		super();
	}

	public ConsultaActividadDTO(Integer folio, Date fecha, String descripcion) {
		super();
		this.folio = folio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ConsultaActividadDTO [folio=" + folio + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int compareTo(ConsultaActividadDTO o) {
		return 0;
	}

}
