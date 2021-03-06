/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;
import java.util.Objects;

/**
 * @name SaveEstadoCuentaResponseMultipleDTO
 * @description Clase que encapsula la informacion relacionada con un request de
 *              una peticion de lata de estado de cuenta con multiples folios de
 *              conciliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/Oct/2020
 * @version 0.1
 *
 */
public class SaveEstadoCuentaResponseMultipleDTO {

	private List<Long> folios;
	private Long folio;

	public SaveEstadoCuentaResponseMultipleDTO() {
	}

	public SaveEstadoCuentaResponseMultipleDTO(List<Long> folios, Long folio) {
		this.folios = folios;
		this.folio = folio;
	}


	@Override
	public int hashCode() {
		return Objects.hash(folios, folio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof SaveEstadoCuentaResponseMultipleDTO))
			return false;

		final SaveEstadoCuentaResponseMultipleDTO other = (SaveEstadoCuentaResponseMultipleDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	public List<Long> getFolios() {
		return folios;
	}

	public void setFolios(List<Long> folios) {
		this.folios = folios;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

}
