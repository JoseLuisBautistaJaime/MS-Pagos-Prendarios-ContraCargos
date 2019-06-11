/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name GeneralBusMailDTO
 * @description Clase que encapsula la informacion del servicio que expone BUS
 *              para el envio de e-mail
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:55 hrs.
 */
public class GeneralBusMailDTO {

	private String para;
	private String de;
	private String asunto;
	private String contenidoHTML;
	private SolicitarPagosAdjuntoDTO adjuntos;

	public GeneralBusMailDTO() {
		super();
	}

	public GeneralBusMailDTO(String para, String de, String asunto, String contenidoHTML,
			SolicitarPagosAdjuntoDTO adjuntos) {
		super();
		this.para = para;
		this.de = de;
		this.asunto = asunto;
		this.contenidoHTML = contenidoHTML;
		this.adjuntos = adjuntos;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenidoHTML() {
		return contenidoHTML;
	}

	public void setContenidoHTML(String contenidoHTML) {
		this.contenidoHTML = contenidoHTML;
	}

	public SolicitarPagosAdjuntoDTO getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(SolicitarPagosAdjuntoDTO adjuntos) {
		this.adjuntos = adjuntos;
	}

	@Override
	public String toString() {
		return "GeneralBusMailDTO [para=" + para + ", de=" + de + ", asunto=" + asunto + ", contenidoHTML="
				+ contenidoHTML + ", adjuntos=" + adjuntos + "]";
	}

}
