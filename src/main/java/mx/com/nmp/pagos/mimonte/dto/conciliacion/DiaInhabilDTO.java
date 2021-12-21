/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name DiaInhabilDTO
 * @description Clase que encapsula la información del día inhábil.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 16/12/2021 09:22 hrs.
 * @version 0.1
 */


public class DiaInhabilDTO implements Comparable<DiaInhabilDTO> {

	private Integer id;
	private String descripcionCorta;
	private String descripcion;
	private Date fecha;

	public DiaInhabilDTO() {
		super();
	}

	public DiaInhabilDTO(Integer id) {
		super();
		this.id = id;
	}

	public DiaInhabilDTO(Integer id, String descripcionCorta, String descripcion, Date fecha) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "DiaInhabilDTO [id=" + id + ", descripcionCorta=" + descripcionCorta + ", descripcion=" + descripcion + ", fecha=" + fecha +"]";
	}

	@Override
	public int compareTo(DiaInhabilDTO o) {
		return 0;
	}
}
