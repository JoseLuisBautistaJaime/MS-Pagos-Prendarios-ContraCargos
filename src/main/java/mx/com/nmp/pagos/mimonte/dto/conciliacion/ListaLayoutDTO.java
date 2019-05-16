/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ListaLayoutDTO
 * @description Clase que encapsula la informacion de un objeto
 *              layout.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 21:10 hrs.
 * @version 0.1
 */
public class ListaLayoutDTO implements Comparable<ListaLayoutDTO>{
	
	private Integer folio;
	private LayOutDTOs layouts;

	public ListaLayoutDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListaLayoutDTO(Integer folio, LayOutDTOs layouts) {
		super();
		this.folio = folio;
		this.layouts = layouts;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public LayOutDTOs getLayouts() {
		return layouts;
	}

	public void setLayouts(LayOutDTOs layouts) {
		this.layouts = layouts;
	}

	@Override
	public String toString() {
		return "ListaLayoutDTO [folio=" + folio + ", layouts=" + layouts + "]";
	}

	@Override
	public int compareTo(ListaLayoutDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
