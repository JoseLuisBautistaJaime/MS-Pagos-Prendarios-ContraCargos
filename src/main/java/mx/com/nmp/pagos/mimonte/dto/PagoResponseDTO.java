package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * Nombre: PagoResponseDTO Descripcion: Clase que encapsula la informacion
 * perteneciente al response de un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 13:11 hrs.
 * @version 0.1
 */
public class PagoResponseDTO {

	public PagoResponseDTO() {
		super();
	}

	private List<EstatusPagoResponseDTO> estatusPagos;
	private Boolean exitoso;
	private Integer idTipoAfiliacion;
	private TipoAutorizacionDTO tipoAfiliacion;

	public PagoResponseDTO(List<EstatusPagoResponseDTO> estatusPagos, Boolean exitoso, Integer idTipoAfiliacion,
			TipoAutorizacionDTO tipoAfiliacion) {
		super();
		this.estatusPagos = estatusPagos;
		this.exitoso = exitoso;
		this.idTipoAfiliacion = idTipoAfiliacion;
		this.tipoAfiliacion = tipoAfiliacion;
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

	public List<EstatusPagoResponseDTO> getEstatusPagos() {
		return estatusPagos;
	}

	public void setEstatusPagos(List<EstatusPagoResponseDTO> estatusPagos) {
		this.estatusPagos = estatusPagos;
	}

	public TipoAutorizacionDTO getTipoAfiliacion() {
		return tipoAfiliacion;
	}

	public void setTipoAfiliacion(TipoAutorizacionDTO tipoAfiliacion) {
		this.tipoAfiliacion = tipoAfiliacion;
	}

	@Override
	public String toString() {
		return "PagoResponseDTO [estatusPagos=" + estatusPagos + ", exitoso=" + exitoso + ", idTipoAfiliacion="
				+ idTipoAfiliacion + ", tipoAfiliacion=" + tipoAfiliacion;
	}

}