package mx.com.nmp.pagos.mimonte.dto;

public class TipoAfiliacionDTO {

	public Integer getId() {
		return id;
	}

	public TipoAfiliacionDTO() {
		super();
	}

	public TipoAfiliacionDTO(Integer id, String descripcion, String descripcionCorta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}

	private Integer id;
	private String descripcion;
	private String descripcionCorta;

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	@Override
	public String toString() {
		return "TipoAfiliacionDTO [id=" + id + ", descripcion=" + descripcion + ", descripcionCorta="
				+ descripcionCorta;
	}

}
