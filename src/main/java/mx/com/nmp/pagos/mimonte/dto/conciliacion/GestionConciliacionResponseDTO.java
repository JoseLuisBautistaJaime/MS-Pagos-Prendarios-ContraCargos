/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name MovimientosEstadoCuentaResponseDTO
 * @description Clase de tipo DTO que mapea el response del servicio para la
 *             creación del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 24/01/2022 09:46 hrs.
 */
public class GestionConciliacionResponseDTO {


	private String codigo;
	private String mensaje;
	private String folio;
	private Boolean cargaCorrecta;


	public GestionConciliacionResponseDTO(String codigo, String mensaje, String folio, Boolean cargaCorrecta) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.folio = folio;
		this.cargaCorrecta = cargaCorrecta;
	}

	public GestionConciliacionResponseDTO() {
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Boolean getCargaCorrecta() {
		return cargaCorrecta;
	}

	public void setCargaCorrecta(Boolean cargaCorrecta) {
		this.cargaCorrecta = cargaCorrecta;
	}

	@Override
	public String toString() {
		return "GestionConciliacionResponseDTO [codigo=" + codigo + ", mensaje=" + mensaje + ", folio=" + folio + ", cargaCorrecta=" + cargaCorrecta + "]";
	}

}
