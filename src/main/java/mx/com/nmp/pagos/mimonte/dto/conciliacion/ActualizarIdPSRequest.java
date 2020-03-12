/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Objects;

/**
 * @name ActualizarIdPSRequest
 * @description Clase que encapsula la información del request de la
 *              actualizacion de id de people soft
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 10/06/2019 19:13 hrs.
 * @version 0.1
 */
public class ActualizarIdPSRequest {

	private Long folio;
	private String idPolizaTesoreria;
	private String idAsientoContable;

	public ActualizarIdPSRequest() {
		super();
	}

	public ActualizarIdPSRequest(Long folio, String idPolizaTesoreria, String idAsientoContable) {
		super();
		this.folio = folio;
		this.idPolizaTesoreria = idPolizaTesoreria;
		this.idAsientoContable = idAsientoContable;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public String getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(String idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public String getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(String idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folio, idPolizaTesoreria, idAsientoContable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ActualizarIdPSRequest))
			return false;

		final ActualizarIdPSRequest other = (ActualizarIdPSRequest) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "ActualizarIdPSRequest [folio=" + folio + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + "]";
	}

}
