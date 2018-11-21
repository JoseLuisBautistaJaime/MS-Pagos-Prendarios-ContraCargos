package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TarjetaDTO Descripcion: Clase que encapsula la información
 * perteneciente al estado de una Tarjeta.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 21/11/2018 11:38 hrs.
 * @version 0.1
 */
public class EstatusTarjetaDTO {

	private Long id;
	private String descripcionCorta;
	private String descripcion;

	public EstatusTarjetaDTO() {
		super();
	}

	public EstatusTarjetaDTO(Long id, String descripcionCorta, String descripcion) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstatusTarjetaDTO [id=" + id + ", descripcion_corta=" + descripcionCorta + ", descripcion="
				+ descripcion + "]";
	}

}
