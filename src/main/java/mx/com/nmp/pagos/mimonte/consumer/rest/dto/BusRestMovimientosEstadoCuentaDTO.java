/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name BusRestMovimientosEstadoCuentaDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *              para cargar los movimientos del estado de cuenta.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 20/01/2022 13:46 hrs.
 */
public class BusRestMovimientosEstadoCuentaDTO implements BusRestBodyDTO {

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	private Long folio;
	private String fechaInicial;
	private String fechaFinal;


	public BusRestMovimientosEstadoCuentaDTO() {
		super();
	}

	public BusRestMovimientosEstadoCuentaDTO(Long folio, Date fechaInicial, Date fechaFinal) {
		super();
		this.folio = folio;
		this.fechaInicial = null != fechaInicial ? sf.format(fechaInicial) : null;
		this.fechaFinal = null != fechaFinal ? sf.format(fechaFinal) : null;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	@Override
	public String toString() {
		return "BusRestMovimientosEstadoCuentaDTO [folio=" + folio + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal +"]";
	}

}
