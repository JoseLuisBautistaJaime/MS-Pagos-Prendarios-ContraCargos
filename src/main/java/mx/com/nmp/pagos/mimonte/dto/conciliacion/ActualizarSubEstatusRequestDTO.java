/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Objects;

/**
 * @name ActualizarSubEstatusRequestDTO
 * @description Clase que encapsula la información del request de la
 *              actualizacion del usb estatus de una conciliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 10/06/2019 19:25 hrs.
 * @version 0.1
 */
public class ActualizarSubEstatusRequestDTO {

	private Integer folio;
	private Integer idSubEstatus;
	private String descripcion;

	public ActualizarSubEstatusRequestDTO() {
		super();
	}

	public ActualizarSubEstatusRequestDTO(Integer folio, Integer idSubEstatus, String descripcion) {
		super();
		this.folio = folio;
		this.idSubEstatus = idSubEstatus;
		this.descripcion = descripcion;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getIdSubEstatus() {
		return idSubEstatus;
	}

	public void setIdSubEstatus(Integer idSubEstatus) {
		this.idSubEstatus = idSubEstatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folio, idSubEstatus, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ActualizarSubEstatusRequestDTO))
			return false;

		final ActualizarSubEstatusRequestDTO other = (ActualizarSubEstatusRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "ActualizarSubEstatusRequestDTO [folio=" + folio + ", idSubEstatus=" + idSubEstatus + ", descripcion="
				+ descripcion + "]";
	}

}
