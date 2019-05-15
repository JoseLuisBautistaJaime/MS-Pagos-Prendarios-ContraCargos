/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.Objects;

/**
 * @name SaveEstadoCuentaRequestDTO
 * @description Clase que encapsula la informacion relacionada con un request de
 *              una peticion de lata de estado de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/05/2019 17:25 hrs.
 * @version 0.1
 */
public class SaveEstadoCuentaRequestDTO implements Comparable<SaveEstadoCuentaRequestDTO> {

	private Integer folio;
	private Date fechaInicial;
	private Date fechaFinal;

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

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

	@Override
	public String toString() {
		return "SaveEstadoCuentaRequestDTO [folio=" + folio + ", fechaInicial=" + fechaInicial + ", fechaFinal="
				+ fechaFinal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(folio, fechaInicial, fechaFinal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof SaveEstadoCuentaRequestDTO))
			return false;

		final SaveEstadoCuentaRequestDTO other = (SaveEstadoCuentaRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(SaveEstadoCuentaRequestDTO arg0) {
		return arg0.folio.compareTo(this.folio);
	}

}
