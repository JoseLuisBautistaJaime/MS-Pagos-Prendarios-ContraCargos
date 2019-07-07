/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.time.LocalDate;
import java.util.Date;

/**
 * @name LayoutCabeceraDTO
 * @description Clase que encapsula la informacion de una cabecera de layout
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/04/2019 10:35 hrs.
 * @version 0.1
 */
public class LayoutCabeceraDTO implements Comparable<LayoutCabeceraDTO> {

	private Long id;
	private String cabecera;
	private String unidadNegocio;
	private String descripcion;
	private String codigoOrigen;
	private LocalDate fecha;

	public LayoutCabeceraDTO() {
		super();
	}

	public LayoutCabeceraDTO(Long id, String cabecera, String unidadNegocio, String descripcion, String codigoOrigen,
			LocalDate fecha) {
		super();
		this.id = id;
		this.cabecera = cabecera;
		this.unidadNegocio = unidadNegocio;
		this.descripcion = descripcion;
		this.codigoOrigen = codigoOrigen;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "LayoutCabeceraDTO [id=" + id + ", cabecera=" + cabecera + ", unidadNegocio=" + unidadNegocio
				+ ", descripcion=" + descripcion + ", codigoOrigen=" + codigoOrigen + ", fecha=" + fecha + "]";
	}

	@Override
	public int compareTo(LayoutCabeceraDTO o) {
		return o.id.compareTo(this.id);
	}

}
