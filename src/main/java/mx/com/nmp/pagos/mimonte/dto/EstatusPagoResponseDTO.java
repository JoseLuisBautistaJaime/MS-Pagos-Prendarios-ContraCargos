package mx.com.nmp.pagos.mimonte.dto;

public class EstatusPagoResponseDTO {

	public EstatusPagoResponseDTO() {
		super();
	}
	
	public EstatusPagoResponseDTO(Integer idEstatusOperacion, String folioContrato) {
		super();
		this.idEstatusOperacion = idEstatusOperacion;
		this.folioContrato = folioContrato;
	}

	private Integer idEstatusOperacion;
	private String folioContrato;
	
	public Integer getIdEstatusOperacion() {
		return idEstatusOperacion;
	}
	public void setIdEstatusOperacion(Integer idEstatusOperacion) {
		this.idEstatusOperacion = idEstatusOperacion;
	}
	
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}

	@Override
	public String toString() {
		return "estatusPagos [idEstatusOperacion=" + idEstatusOperacion + ", folioContrato=" + folioContrato + "]";
	}
	
}
