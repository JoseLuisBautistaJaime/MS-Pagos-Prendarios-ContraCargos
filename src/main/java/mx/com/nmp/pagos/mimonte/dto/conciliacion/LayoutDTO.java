/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name LayoutDTO
 * @description Clase que encapsula la informacion de un layout
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 30/03/2019 00:00:00 hrs.
 * @version 0.1
 */
public class LayoutDTO implements Comparable<LayoutDTO> {

	private Long folio;
	private TipoLayoutEnum tipoLayout;
	private LayoutCabeceraDTO cabecera;
	private List<LayoutLineaDTO> lineas;

	public LayoutDTO() {
		super();
	}

	public LayoutDTO(Long folio, TipoLayoutEnum tipoLayout, LayoutCabeceraDTO cabecera, List<LayoutLineaDTO> lineas) {
		super();
		this.folio = folio;
		this.tipoLayout = tipoLayout;
		this.cabecera = cabecera;
		this.lineas = lineas;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public TipoLayoutEnum getTipoLayout() {
		return tipoLayout;
	}

	public void setTipoLayout(TipoLayoutEnum tipoLayout) {
		this.tipoLayout = tipoLayout;
	}

	public LayoutCabeceraDTO getCabecera() {
		return cabecera;
	}

	public void setCabecera(LayoutCabeceraDTO cabecera) {
		this.cabecera = cabecera;
	}

	public List<LayoutLineaDTO> getLineas() {
		return lineas;
	}

	public void setLineas(List<LayoutLineaDTO> lineas) {
		this.lineas = lineas;
	}

	@Override
	public String toString() {
		return "LayoutDTO [folio=" + folio + ", tipoLayout=" + tipoLayout + ", cabecera=" + cabecera + ", lineas="
				+ lineas + "]";
	}

	@Override
	public int compareTo(LayoutDTO o) {
		return o.folio.compareTo(this.folio);
	}

}