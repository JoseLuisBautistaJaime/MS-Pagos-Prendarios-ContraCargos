/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name RespuestaMailResponseDTO
 * @description Clase de tipo DTO que mapea el response de el servicio para la
 *              respuesta interna del envio de e-mail
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/06/2019 14:02 hrs.
 * @version 0.1
 */
public class RespuestaMailResponseDTO {

	private String codigo;
	private String mensaje;

	public RespuestaMailResponseDTO() {
		super();
	}

	public RespuestaMailResponseDTO(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "RespuestaMailResponseDTO [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}

}
