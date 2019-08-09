/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;
import java.util.Objects;

import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name LayoutRequestDTO
 * @description Clase que encapsula la informacion de un layout para la peticion
 *              o request (Sij cabecera)
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 09/08/2019 13:35:00 hrs.
 * @version 0.1
 */
public class LayoutRequestDTO {

	private Long folio;
	private TipoLayoutEnum tipoLayout;
	private List<LayoutLineaDTO> lineas;

	public LayoutRequestDTO() {
		super();
	}

	public LayoutRequestDTO(Long folio, TipoLayoutEnum tipoLayout, List<LayoutLineaDTO> lineas) {
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
	public int hashCode() {
		return Objects.hash(folio, tipoLayout, lineas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof LayoutRequestDTO))
			return false;

		final LayoutRequestDTO other = (LayoutRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "LayoutRequestDTO [folio=" + folio + ", tipoLayout=" + tipoLayout + ", lineas=" + lineas + "]";
	}

}
