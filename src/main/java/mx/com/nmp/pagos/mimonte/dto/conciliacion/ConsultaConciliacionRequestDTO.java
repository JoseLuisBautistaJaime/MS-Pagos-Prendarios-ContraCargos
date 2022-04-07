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
	private Long folio;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer idEstatus;
	private String idCorresponsal;

	public ConsultaConciliacionRequestDTO() {
		super();
	}

	public ConsultaConciliacionRequestDTO(Long idEntidad, Long folio, Date fechaDesde, Date fechaHasta,
			Integer idEstatus) {
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

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
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

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}	
	
	public String getIdCorresponsal() {
		return idCorresponsal;
	}

	public void setIdCorresponsal(String idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

	@Override
	public String toString() {
		return "ConsultaConciliacionRequestDTO [idEntidad=" + idEntidad + ", folio=" + folio + ", fechaDesde="
				+ fechaDesde + ", fechaHasta=" + fechaHasta + ", idEstatus=" + idEstatus + ", idCorresponsal="
				+ idCorresponsal + "]";
	}

}