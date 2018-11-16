package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: Operacion
 * Descripcion: Clase que encapsula la informacion perteneciente a una Operacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 16/11/2018 13:10 hrs.
 * @version 0.1
 */
public class OperacionDTO {

	private Integer idOperacion;
	private String nombreOperacion;
	private String folioContrato;
	private Double monto;
	
	public OperacionDTO() {
		super();
	}

	public OperacionDTO(Integer idOperacion, String nombreOperacion, String folioContrato, Double monto) {
		this.idOperacion = idOperacion;
		this.nombreOperacion = nombreOperacion;
		this.folioContrato = folioContrato;
		this.monto = monto;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public String getFolioContrato() {
		return folioContrato;
	}

	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "OperacionDTO [idOperacion=" + idOperacion + ", nombreOperacion=" + nombreOperacion + ", folioContrato="
				+ folioContrato + ", monto=" + monto + "]";
	}

}
