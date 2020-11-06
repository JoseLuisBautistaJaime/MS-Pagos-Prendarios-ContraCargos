/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @name SaveEstadoCuentaRequestMultipleDTO
 * @description Clase que encapsula la informacion relacionada con un request de
 *              una peticion de lata de estado de cuenta con multiples folios de
 *              conciliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/Oct/2020
 * @version 0.1
 *
 */
public class SaveEstadoCuentaRequestMultipleDTO {

	private List<Long> folios;
	private Date fechaInicial;
	private Date fechaFinal;

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public List<Long> getFolios() {
		return folios;
	}

	public void setFolios(List<Long> folios) {
		this.folios = folios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folios, fechaInicial, fechaFinal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof SaveEstadoCuentaRequestDTO))
			return false;

		final SaveEstadoCuentaRequestMultipleDTO other = (SaveEstadoCuentaRequestMultipleDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

}
