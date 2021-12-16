/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ProcesoPreconciliacionResponseDTO
 * @description Clase de tipo DTO que mapea el response de el servicio para la
 *             ejecución del proceso de pre-conciliación
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 07/12/2021 13:46 hrs.
 */
public class ProcesoPreconciliacionResponseDTO {

	private String codigo;
	private String mensaje;
	private Boolean ejecucionCorrecta;

	public ProcesoPreconciliacionResponseDTO() {
	}

	public ProcesoPreconciliacionResponseDTO(String codigo, String mensaje, Boolean ejecucionCorrecta) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.ejecucionCorrecta = ejecucionCorrecta;
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

	public Boolean getEjecucionCorrecta() {
		return ejecucionCorrecta;
	}

	public void setEjecucionCorrecta(Boolean ejecucionCorrecta) {
		this.ejecucionCorrecta = ejecucionCorrecta;
	}

	@Override
	public String toString() {
		return "ProcesoPreconciliacionResponseDTO [codigo=" + codigo + ", mensaje=" + mensaje + ", ejecucionCorrecta=" + ejecucionCorrecta + "]";
	}

}
