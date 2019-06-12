/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ResumenConciliacionesRequest
 * @description Clase que encapsula el request de ResumenConciliacionesRequest para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 21:57 hrs.
 * @version 0.1
 */
public class ResumenConciliacionesRequest implements Comparable<ResumenConciliacionesRequest>{
	
    private Date fechaInicial;
    private Date fechaFinal;

	public ResumenConciliacionesRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResumenConciliacionesRequest(Integer folio, Date fechaInicial, Date fechaFinal) {
		super();
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	@Override
	public String toString() {
		return "ResumenConciliacionesRequest [fechaInicial=" + fechaInicial + ", fechaFinal="
				+ fechaFinal + "]";
	}

	@Override
	public int compareTo(ResumenConciliacionesRequest o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
