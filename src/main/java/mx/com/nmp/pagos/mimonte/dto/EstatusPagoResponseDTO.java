package mx.com.nmp.pagos.mimonte.dto;

public class EstatusPagoResponseDTO {

	public EstatusPagoResponseDTO() {
		super();
	}
	
	public EstatusPagoResponseDTO(Integer idEstatusOperacion, String folioContratro) {
		super();
		this.idEstatusOperacion = idEstatusOperacion;
		this.folioContratro = folioContratro;
	}

	private Integer idEstatusOperacion;
	private String folioContratro;
	
	public Integer getIdEstatusOperacion() {
		return idEstatusOperacion;
	}
	public void setIdEstatusOperacion(Integer idEstatusOperacion) {
		this.idEstatusOperacion = idEstatusOperacion;
	}
	
	public String getFolioContratro() {
		return folioContratro;
	}
	public void setFolioContratro(String folioContratro) {
		this.folioContratro = folioContratro;
	}

	@Override
	public String toString() {
		return "estatusPagos [idEstatusOperacion=" + idEstatusOperacion + ", folioContratro=" + folioContratro + "]";
	}
	
}
