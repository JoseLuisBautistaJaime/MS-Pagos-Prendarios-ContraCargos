/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name AdjuntoSolicPagosDTO
 * @description Clase que encapsula la informacion de objetos adjuntos anidados
 *              del servicio que expone BUS para el envio de e-mail
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:58 hrs.
 */
public class AdjuntoSolicPagosDTO {

	private String nombreArchivo;
	private String contenido;

	public AdjuntoSolicPagosDTO() {
		super();
	}

	public AdjuntoSolicPagosDTO(String nombreArchivo, String contenido) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.contenido = contenido;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "AdjuntoSolicPagosDTO [nombreArchivo=" + nombreArchivo + ", contenido=" + contenido + "]";
	}

}
