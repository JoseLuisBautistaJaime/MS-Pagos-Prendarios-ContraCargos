/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name SolicitarPagosAdjuntoDTO
 * @description Clase que encapsula la informacion de objetos adjuntos del
 *              servicio que expone BUS para el envio de e-mail
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:57 hrs.
 */
public class SolicitarPagosAdjuntoDTO {

	List<AdjuntoSolicPagosDTO> adjunto;

	public SolicitarPagosAdjuntoDTO() {
		super();
	}

	public SolicitarPagosAdjuntoDTO(List<AdjuntoSolicPagosDTO> adjunto) {
		super();
		this.adjunto = adjunto;
	}

	public List<AdjuntoSolicPagosDTO> getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(List<AdjuntoSolicPagosDTO> adjunto) {
		this.adjunto = adjunto;
	}

	@Override
	public String toString() {
		return "SolicitarPagosAdjuntoDTO [adjunto=" + adjunto + "]";
	}

}
