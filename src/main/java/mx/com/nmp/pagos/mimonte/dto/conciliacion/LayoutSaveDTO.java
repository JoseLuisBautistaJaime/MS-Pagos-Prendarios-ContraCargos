/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name LayoutSaveDTO
 * @description Clase que encapsula la informacion de un layout para alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 11:06 hrs.
 * @version 0.1
 */
public class LayoutSaveDTO implements Comparable<LayoutSaveDTO> {

	private Long folio;
	private TipoLayoutEnum tipoLayout;
	private List<LayoutLineaDTO> lineas;

	public LayoutSaveDTO() {
		super();
	}

	public LayoutSaveDTO(Long folio, TipoLayoutEnum tipoLayout, List<LayoutLineaDTO> lineas) {
		super();
		this.folio = folio;
		this.tipoLayout = tipoLayout;
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

	public List<LayoutLineaDTO> getLineas() {
		return lineas;
	}

	public void setLineas(List<LayoutLineaDTO> lineas) {
		this.lineas = lineas;
	}

	@Override
	public String toString() {
		return "LayoutSaveDTO [folio=" + folio + ", tipoLayout=" + tipoLayout + ", lineas=" + lineas + "]";
	}

	@Override
	public int compareTo(LayoutSaveDTO o) {
		return o.folio.compareTo(this.folio);
	}

}
