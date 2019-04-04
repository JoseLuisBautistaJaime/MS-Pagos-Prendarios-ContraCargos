/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:55 PM
 */
public class ConsultaConciliacionRequestDTO {

	private Long idEntidad;
	private String folio;
	private String fechaDesde;
	private String fechaHasta;
	private Long idEstatus;

	public ConsultaConciliacionRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaConciliacionRequestDTO(Long idEntidad, String folio, String fechaDesde, String fechaHasta,
			Long idEstatus) {
		super();
		this.idEntidad = idEntidad;
		this.folio = folio;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.idEstatus = idEstatus;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}

	@Override
	public String toString() {
		return "ConsultaConciliacionRequestDTO [idEntidad=" + idEntidad + ", folio=" + folio + ", fechaDesde="
				+ fechaDesde + ", fechaHasta=" + fechaHasta + ", idEstatus=" + idEstatus + "]";
	}

}