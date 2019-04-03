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

	private Long id;
	private TipoLayoutEnum tipoLayout;
	private LayoutCabeceraDTO cabecera;
	private List<LayoutLineaDTO> lineas;

	public LayoutDTO() {
		super();
	}

	public LayoutDTO(Long id, TipoLayoutEnum tipoLayout, LayoutCabeceraDTO cabecera, List<LayoutLineaDTO> lineas) {
		super();
		this.id = id;
		this.tipoLayout = tipoLayout;
		this.cabecera = cabecera;
		this.lineas = lineas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "LayoutDTO [id=" + id + ", tipoLayout=" + tipoLayout + ", cabecera=" + cabecera + ", lineas=" + lineas
				+ "]";
	}

	@Override
	public int compareTo(LayoutDTO o) {
		return o.id.compareTo(this.id);
	}

}