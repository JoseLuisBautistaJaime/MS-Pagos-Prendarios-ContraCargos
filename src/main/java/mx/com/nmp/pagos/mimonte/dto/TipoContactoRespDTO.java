/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TipoContactoRespDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a los tipo de contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/03/2019 16:27 hrs.
 * @version 0.1
 */
public class TipoContactoRespDTO {

	private Long id;
	private String description;

	public TipoContactoRespDTO() {
		super();
	}

	public TipoContactoRespDTO(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TipoContactoRespDTO [id=" + id + ", description=" + description + "]";
	}

}
