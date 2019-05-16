/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name MovimientoEdoCtaRequest
 * @description Clase que encapsula el request de MovimientoEdoCtaRequest para los
 *              movimientos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 22:38 hrs.
 * @version 0.1
 */
public class MovimientoEdoCtaRequest implements Comparable<MovimientoEdoCtaRequest>{
	
	    private Integer folio;
	    private Date fechaInicial;
	    private Date fechaFinal;

	public MovimientoEdoCtaRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

	public MovimientoEdoCtaRequest(Integer folio, Date fechaInicial, Date fechaFinal) {
		super();
		this.folio = folio;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
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
		return "MovimientoEdoCtaRequest [folio=" + folio + ", fechaInicial=" + fechaInicial + ", fechaFinal="
				+ fechaFinal + "]";
	}

	@Override
	public int compareTo(MovimientoEdoCtaRequest o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
