package mx.com.nmp.pagos.mimonte.dto;

public class EstatusTarjetaDTO {

	private Long id;
	private String descripcion_corta;
	private String descripcion;

	public EstatusTarjetaDTO() {
		super();
	}
	
	public EstatusTarjetaDTO(Long id, String descripcion_corta, String descripcion) {
		super();
		this.id = id;
		this.descripcion_corta = descripcion_corta;
		this.descripcion = descripcion;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion_corta() {
		return descripcion_corta;
	}

	public void setDescripcion_corta(String descripcion_corta) {
		this.descripcion_corta = descripcion_corta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstatusTarjetaDTO [id=" + id + ", descripcion_corta=" + descripcion_corta + ", descripcion="
				+ descripcion + "]";
	}

}
