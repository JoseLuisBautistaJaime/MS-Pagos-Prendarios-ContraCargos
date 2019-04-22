/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name DevolucionesIdsMovimientosDTO
 * @description Clase que encapsula los ids de los movimientos de devoluciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 12:42 hrs.
 * @version 0.1
 */
public class DevolucionesIdsMovimientosDTO {

	private List<Integer> idsMovimientos;

	public DevolucionesIdsMovimientosDTO() {
		super();
	}

	public DevolucionesIdsMovimientosDTO(List<Integer> idsMovimientos) {
		super();
		this.idsMovimientos = idsMovimientos;
	}

	public List<Integer> getIdsMovimientos() {
		return idsMovimientos;
	}

	public void setIdsMovimientos(List<Integer> idsMovimientos) {
		this.idsMovimientos = idsMovimientos;
	}

	@Override
	public String toString() {
		return "DevolucionesIdsMovimientosDTO [idsMovimientos=" + idsMovimientos + "]";
	}

}
