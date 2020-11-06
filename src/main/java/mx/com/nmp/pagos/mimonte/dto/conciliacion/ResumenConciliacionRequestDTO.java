/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.Objects;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @name ResumenConciliacionRequestDTO
 * @description Clase que encapsula informacion del request para consulta de
 *              resumen de conciliaciones
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 10/06/2019 19:37 hrs.
 * @version 0.1
 */
public class ResumenConciliacionRequestDTO {

	private Date fechaInicial;
	private Date fechaFinal;
	private CorresponsalEnum corresponsal;

	public ResumenConciliacionRequestDTO() {
		super();
	}

	public ResumenConciliacionRequestDTO(Date fechaInicial, Date fechaFinal) {
		super();
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
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
	public int hashCode() {
		return Objects.hash(fechaInicial, fechaFinal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ResumenConciliacionRequestDTO))
			return false;

		final ResumenConciliacionRequestDTO other = (ResumenConciliacionRequestDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "ResumenConciliacionRequestDTO [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal
				+ ", corresponsal=" + corresponsal + "]";
	}

}
