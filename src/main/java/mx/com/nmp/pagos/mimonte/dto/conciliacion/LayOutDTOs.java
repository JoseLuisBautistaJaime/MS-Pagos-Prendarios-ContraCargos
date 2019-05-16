/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name LayOutDTOs
 * @description Clase que encapsula la informacion de un objeto
 *              LayOutDTOs.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 23:22 hrs.
 * @version 0.1
 */
public class LayOutDTOs implements Comparable<LayOutDTOs>{
	
	private Integer id;
	private String tipoLayout;
	private LayoutCabeceraDTOs cabecera;
	private List<LayoutLineaDTOs> lineas;

	public LayOutDTOs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LayOutDTOs(Integer id, String tipoLayout, LayoutCabeceraDTOs cabecera, List<LayoutLineaDTOs> lineas) {
		super();
		this.id = id;
		this.tipoLayout = tipoLayout;
		this.cabecera = cabecera;
		this.lineas = lineas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoLayout() {
		return tipoLayout;
	}

	public void setTipoLayout(String tipoLayout) {
		this.tipoLayout = tipoLayout;
	}

	public LayoutCabeceraDTOs getCabecera() {
		return cabecera;
	}

	public void setCabecera(LayoutCabeceraDTOs cabecera) {
		this.cabecera = cabecera;
	}

	public List<LayoutLineaDTOs> getLineas() {
		return lineas;
	}

	public void setLineas(List<LayoutLineaDTOs> lineas) {
		this.lineas = lineas;
	}

	@Override
	public String toString() {
		return "LayOutDTOs [id=" + id + ", tipoLayout=" + tipoLayout + ", cabecera=" + cabecera + ", lineas=" + lineas
				+ "]";
	}

	@Override
	public int compareTo(LayOutDTOs o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
