/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name MovimientosEstadoCuentaResponseDTO
 * @description Clase de tipo DTO que mapea el response del servicio para la
 *             carga de los movimientos del estado de cuenta
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 20/01/2022 09:46 hrs.
 */
public class MovimientosEstadoCuentaResponseDTO {


	private String codigo;
	private String descripcion;
	private String message;
	private Boolean cargaCorrecta;

	public MovimientosEstadoCuentaResponseDTO(String codigo, String descripcion, String message, Boolean cargaCorrecta) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.message = message;
		this.cargaCorrecta = cargaCorrecta;
	}

	public MovimientosEstadoCuentaResponseDTO() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getCargaCorrecta() {
		return cargaCorrecta;
	}

	public void setCargaCorrecta(Boolean cargaCorrecta) {
		this.cargaCorrecta = cargaCorrecta;
	}

	@Override
	public String toString() {
		return "MovimientosEstadoCuentaResponseDTO [codigo=" + codigo + ", descripcion=" + descripcion + ", message=" + message + ", cargaCorrecta=" + cargaCorrecta + "]";
	}

}
