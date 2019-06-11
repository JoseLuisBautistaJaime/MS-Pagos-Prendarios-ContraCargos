/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ConsultaActividadesRequestDTO
 * @description Clase que encapsula informacion del request para consulta de
 *              actividades
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 10/06/2019 19:49 hrs.
 * @version 0.1
 */
public class ConsultaActividadesRequestDTO {

	private Integer folio;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public ConsultaActividadesRequestDTO() {
		super();
	}
	
	public ConsultaActividadesRequestDTO(Integer folio, Date fechaDesde, Date fechaHasta) {
		super();
		this.folio = folio;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}
	
	public Integer getFolio() {
		return folio;
	}
	public void setFolio(Integer folio) {
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
	
	@Override
	public String toString() {
		return "ConsultaActividadesRequestDTO [folio=" + folio + ", fechaDesde=" + fechaDesde + ", fechaHasta="
				+ fechaHasta + "]";
	}
	
}
