/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientoIDDTO
 * @description Clase que contiene los identificadores de los movimientos dados
 *              de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 09:50 hrs.
 * @version 0.1
 */
public class MovimientoIDDTO implements Comparable<MovimientoIDDTO> {

	private List<Long> idsMovimientos;

	public MovimientoIDDTO() {
		super();
	}

	public MovimientoIDDTO(List<Long> idsMovimientos) {
		super();
		this.idsMovimientos = idsMovimientos;
	}

	public List<Long> getIdsMovimientos() {
		return idsMovimientos;
	}

	public void setIdsMovimientos(List<Long> idsMovimientos) {
		this.idsMovimientos = idsMovimientos;
	}

	@Override
	public String toString() {
		return "MovimientoIDDTO [idsMovimientos=" + idsMovimientos + "]";
	}

	@Override
	public int compareTo(MovimientoIDDTO o) {
		return 0;
	}

}
