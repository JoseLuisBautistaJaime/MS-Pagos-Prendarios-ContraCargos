/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.List;

/**
 * @name MovimientoTransaccionalRequestDTO
 * @description Clase que encapsula la informacion relacionada con un respuetsa
 *              a el alta de MovimientosProveedor
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 16:27 hrs.
 * @version 0.1
 */
public class MovimientoTransaccionalListRequestDTO implements Comparable<MovimientoTransaccionalListRequestDTO> {

	private Long folio;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<MovimientoProveedorDTO> movimientos;

	public MovimientoTransaccionalListRequestDTO() {
		super();
	}

	public MovimientoTransaccionalListRequestDTO(Long folio, Date fechaDesde, Date fechaHasta,
			List<MovimientoProveedorDTO> movimientos) {
		super();
		this.folio = folio;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.movimientos = movimientos;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public List<MovimientoProveedorDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoProveedorDTO> movimientos) {
		this.movimientos = movimientos;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	@Override
	public String toString() {
		return "MovimientoTransaccionalListRequestDTO [folio=" + folio + ", fechaDesde=" + fechaDesde + ", fechaHasta="
				+ fechaHasta + ", movimientos=" + movimientos + "]";
	}

	@Override
	public int compareTo(MovimientoTransaccionalListRequestDTO o) {
		return 0;
	}

}
