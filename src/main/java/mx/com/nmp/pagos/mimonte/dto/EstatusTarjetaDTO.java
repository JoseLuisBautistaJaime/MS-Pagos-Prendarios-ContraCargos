package mx.com.nmp.pagos.mimonte.dto;

public class EstatusTarjetaDTO {
	
	private String idTipo;
	private String descripcion;
	
	public EstatusTarjetaDTO() {
		super();
	}
	
	public EstatusTarjetaDTO(String idTipo, String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	
	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstatusTarjetaDTO [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}

}
