/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name BusRestMovimientosNocturnosDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *              para cargar los movimientos nocturnos de MIDAS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 07/01/2022 13:46 hrs.
 */
public class BusRestMovimientosNocturnosDTO implements BusRestBodyDTO {

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	private Long folio;
	private String fechaInicio;
	private String fechaFin;
	private BusRestCorresponsalDTO corresponsal;
	private Long estadoProceso;

	public BusRestMovimientosNocturnosDTO() {
		super();
	}

	public BusRestMovimientosNocturnosDTO(Long folio, Date fechaInicio, Date fechaFin, BusRestCorresponsalDTO corresponsal, Long estadoProceso) {
		super();
		this.folio = folio;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
		this.corresponsal = corresponsal;
		this.estadoProceso = estadoProceso;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
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

	public BusRestCorresponsalDTO getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(BusRestCorresponsalDTO corresponsal) {
		this.corresponsal = corresponsal;
	}

	public Long getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(Long estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	@Override
	public String toString() {
		return "BusRestMovimientosNocturnosDTO [folio=" + folio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", corresponsal=" + corresponsal + ", estadoProceso=" + estadoProceso +"]";
	}

}
