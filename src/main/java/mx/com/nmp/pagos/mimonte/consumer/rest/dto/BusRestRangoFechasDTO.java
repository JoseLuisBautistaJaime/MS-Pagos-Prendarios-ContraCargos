/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name BusRestRangoFechasDTO
 * @description Clase que encapsula el rengo de fechas como parametros del servicio expuesto por BUS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 06/12/2021 09:46 hrs.
 */
public class BusRestRangoFechasDTO {

	private SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd");

	private String fechaInicio;
	private String fechaFin;

	public BusRestRangoFechasDTO() {}

	public BusRestRangoFechasDTO(Date fechaInicio, Date fechaFin) {
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "BusRestRangoFechasDTO [fechaInicio=" + fechaInicio +", fechaFin=" + fechaFin +"]";
	}

}
