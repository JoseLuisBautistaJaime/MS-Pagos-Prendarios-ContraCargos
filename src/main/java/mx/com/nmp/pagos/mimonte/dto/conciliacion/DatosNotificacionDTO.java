/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestBodyDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name DatosNotificacionDTO
 * @description Clase que encapsula la información implementada para notificar el resultado de los procesos automatizados
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 03/02/2022 13:46 hrs.
 */
public class DatosNotificacionDTO {

	private static final SimpleDateFormat sf;

	private Long folio;
	private String fechaInicio;
	private String fechaFin;
	private String correponsal;
	private Long idEntidad;
	private Long idCuenta;
	private String entidad;
	private String cuenta;
	private String fechaInicial;
	private String fechaFinal;

	static {
		sf = new SimpleDateFormat("yyyy-MM-dd");
	}

	public DatosNotificacionDTO() {
		super();
	}

	public DatosNotificacionDTO(Long folio, Date fechaInicio, Date fechaFin) {
		this.folio = folio;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
	}

	public DatosNotificacionDTO(Long folio, Date fechaInicio, Date fechaFin, String correponsal) {
		this.folio = folio;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
		this.correponsal = correponsal;
	}

	public DatosNotificacionDTO(Long folio, Long idEntidad, String entidad, Long idCuenta, String cuenta, Date fechaInicio, Date fechaFin, String correponsal, Date fechaInicial, Date fechaFinal) {
		this.folio = folio;
		this.idEntidad = idEntidad;
		this.entidad = entidad;
		this.idCuenta = idCuenta;
		this.cuenta = cuenta;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
		this.correponsal = correponsal;
		this.fechaInicial = null != fechaInicial ? sf.format(fechaInicial) : null;
		this.fechaFinal = null != fechaFinal ? sf.format(fechaFinal) : null;
	}

	public DatosNotificacionDTO(Long idEntidad, String entidad, Long idCuenta, String cuenta, Date fechaInicio, Date fechaFin, String correponsal) {
		this.idEntidad = idEntidad;
		this.entidad = entidad;
		this.idCuenta = idCuenta;
		this.cuenta = cuenta;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
		this.correponsal = correponsal;
	}

	public DatosNotificacionDTO(Long folio, Long idEntidad, String entidad, Long idCuenta, String cuenta, Date fechaInicio, Date fechaFin, String correponsal) {
		this.folio = folio;
		this.idEntidad = idEntidad;
		this.entidad = entidad;
		this.idCuenta = idCuenta;
		this.cuenta = cuenta;
		this.fechaInicio = null != fechaInicio ? sf.format(fechaInicio) : null;
		this.fechaFin = null != fechaFin ? sf.format(fechaFin) : null;
		this.correponsal = correponsal;
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

	public String getCorreponsal() {
		return correponsal;
	}

	public void setCorreponsal(String correponsal) {
		this.correponsal = correponsal;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
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
		return "DatosNotificacionDTO [folio=" + folio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", correponsal=" + correponsal + ", idEntidad=" + idEntidad + ", entidad=" + entidad + ", idCuenta=" + idCuenta
				+ ", cuenta=" + cuenta + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal+"]";
	}

}
