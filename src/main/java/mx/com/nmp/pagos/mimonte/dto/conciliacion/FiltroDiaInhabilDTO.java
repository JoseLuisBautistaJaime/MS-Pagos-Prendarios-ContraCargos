/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 16-Dic-2021 11:33:55 AM
 */
public class FiltroDiaInhabilDTO {

	private String descripcion;
	private Date fecha;

	public FiltroDiaInhabilDTO() {
		super();
	}

	public FiltroDiaInhabilDTO(Date fecha, String descripcion) {
		super();
		this.fecha=fecha;
		this.descripcion=descripcion;
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
		return "FiltroDiaInhabilDTO [fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}

}