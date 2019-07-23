/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name LayoutEditionValidationDTO
 * @description Clase de tipo DTO que se encarga de mapear la repuesta del query
 *              que veriica si un layout linea sera nuevo o va a ser actualizado
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/07/2019 16:11 hrs.
 * @version 0.1|O
 */
public class LayoutEditionValidationDTO {

	private Long id;
	private String createdBy;

	public LayoutEditionValidationDTO() {
		super();
	}

	public LayoutEditionValidationDTO(Long id, String createdBy) {
		super();
		this.id = id;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "LayoutEditionValidationDTO [id=" + id + ", createdBy=" + createdBy + "]";
	}

}
