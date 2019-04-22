/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name AfiliacionSaveDTO
 * @description Clase que encapsula la informacion sobre una afiliacion en el
 *              requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 12:59 hrs.
 * @version 0.1
 */
public class AfiliacionSaveDTO {

	private Long id;

	public AfiliacionSaveDTO() {
		super();
	}

	public AfiliacionSaveDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AfiliacionSaveDTO [id=" + id + "]";
	}

}
