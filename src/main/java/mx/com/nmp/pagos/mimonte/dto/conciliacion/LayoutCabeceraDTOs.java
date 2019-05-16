/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name LayoutCabeceraDTOs
 * @description Clase que encapsula la informacion de un objeto
 *              LayoutCabeceraDTOs.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 23:28 hrs.
 * @version 0.1
 */
public class LayoutCabeceraDTOs implements Comparable<LayoutCabeceraDTOs>{
	
	private Integer	cabecera;
	private	String unidadNegocio;
	private	String descripcion;
	private	String codigoOrigen;
	private	Date fecha;

	public LayoutCabeceraDTOs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LayoutCabeceraDTOs(Integer cabecera, String unidadNegocio, String descripcion, String codigoOrigen,
			Date fecha) {
		super();
		this.cabecera = cabecera;
		this.unidadNegocio = unidadNegocio;
		this.descripcion = descripcion;
		this.codigoOrigen = codigoOrigen;
		this.fecha = fecha;
	}

	public Integer getCabecera() {
		return cabecera;
	}

	public void setCabecera(Integer cabecera) {
		this.cabecera = cabecera;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "LayoutCabeceraDTOs [cabecera=" + cabecera + ", unidadNegocio=" + unidadNegocio + ", descripcion="
				+ descripcion + ", codigoOrigen=" + codigoOrigen + ", fecha=" + fecha + "]";
	}

	@Override
	public int compareTo(LayoutCabeceraDTOs o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
