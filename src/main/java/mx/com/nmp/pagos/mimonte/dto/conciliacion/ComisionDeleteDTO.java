/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name ComisionDeleteDTO
 * @description Clase que encapsula la informacion para una peticion de baja de
 *              comisiones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 14:56 hrs.
 * @version 0.1
 */
public class ComisionDeleteDTO implements Comparable<ComisionDeleteDTO> {

	private Integer folio;
	private List<Integer> idComisiones;

	public ComisionDeleteDTO() {
		super();
	}

	public ComisionDeleteDTO(Integer folio, List<Integer> idComisiones) {
		super();
		this.folio = folio;
		this.idComisiones = idComisiones;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public List<Integer> getIdComisiones() {
		return idComisiones;
	}

	public void setIdComisiones(List<Integer> idComisiones) {
		this.idComisiones = idComisiones;
	}

	@Override
	public int compareTo(ComisionDeleteDTO o) {
		return o.folio.compareTo(this.folio);
	}

}
