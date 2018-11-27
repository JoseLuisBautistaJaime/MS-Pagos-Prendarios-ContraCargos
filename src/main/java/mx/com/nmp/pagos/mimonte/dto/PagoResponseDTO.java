package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * Nombre: PagoResponseDTO
 * Descripcion: Clase que encapsula la informacion perteneciente al response de un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 13:11 hrs.
 * @version 0.1
 */
public class PagoResponseDTO {

	public PagoResponseDTO() {
		super();
	}
	private List<EstatusPagoResponseDTO> estatusTransacciones;
	private Boolean exitoso;
	private Integer idTipoAfiliacion;
	
	public PagoResponseDTO(List<EstatusPagoResponseDTO> estatusTransacciones, Boolean exitoso,
			Integer idTipoAfiliacion) {
		super();
		this.estatusTransacciones = estatusTransacciones;
		this.exitoso = exitoso;
		this.idTipoAfiliacion = idTipoAfiliacion;
	}
	
	public Boolean getExitoso() {
		return exitoso;
	}
	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}
	public Integer getIdTipoAfiliacion() {
		return idTipoAfiliacion;
	}
	public void setIdTipoAfiliacion(Integer idTipoAfiliacion) {
		this.idTipoAfiliacion = idTipoAfiliacion;
	}
	
	public List<EstatusPagoResponseDTO> getEstatusTransacciones() {
		return estatusTransacciones;
	}

	public void setEstatusTransacciones(List<EstatusPagoResponseDTO> estatusTransacciones) {
		this.estatusTransacciones = estatusTransacciones;
	}

	@Override
	public String toString() {
		return "PagoResponseDTO [estatusTransacciones=" + estatusTransacciones + ", exitoso="
				+ exitoso + ", idTipoAfiliacion=" + idTipoAfiliacion + "]";
	}
	
}