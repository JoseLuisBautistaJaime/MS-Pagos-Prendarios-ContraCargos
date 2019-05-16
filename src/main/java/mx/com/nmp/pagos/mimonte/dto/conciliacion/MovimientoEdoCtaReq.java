/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name MovimientoEdoCtaReq
 * @description Clase que encapsula el request de MovimientoEdoCtaReq para los
 *              movimientos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 22:44 hrs.
 * @version 0.1
 */
public class MovimientoEdoCtaReq implements Comparable<MovimientoEdoCtaReq>{
	
	private List<MovimientoEdoCtaRequest> movimientosRequest;

	public MovimientoEdoCtaReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovimientoEdoCtaReq(List<MovimientoEdoCtaRequest> movimientosRequest) {
		super();
		this.movimientosRequest = movimientosRequest;
	}

	public List<MovimientoEdoCtaRequest> getMovimientosRequest() {
		return movimientosRequest;
	}

	public void setMovimientosRequest(List<MovimientoEdoCtaRequest> movimientosRequest) {
		this.movimientosRequest = movimientosRequest;
	}

	@Override
	public String toString() {
		return "MovimientoEdoCtaReq [movimientosRequest=" + movimientosRequest + "]";
	}

	@Override
	public int compareTo(MovimientoEdoCtaReq o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
