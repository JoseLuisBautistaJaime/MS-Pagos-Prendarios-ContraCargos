/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.RespuestaMailResponseDTO;

/**
 * @name SendMailResponseDTO
 * @description Clase de tipo DTO que mapea el response de el servicio para
 *              envio de e-mail
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/06/2019 14:01 hrs.
 * @version 0.1
 */
public class BusRestMailResponseDTO {

	private RespuestaMailResponseDTO respuesta;

	public RespuestaMailResponseDTO getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaMailResponseDTO respuesta) {
		this.respuesta = respuesta;
	}

	public BusRestMailResponseDTO(RespuestaMailResponseDTO respuesta) {
		super();
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "SendMailResponseDTO [respuesta=" + respuesta + "]";
	}

}
