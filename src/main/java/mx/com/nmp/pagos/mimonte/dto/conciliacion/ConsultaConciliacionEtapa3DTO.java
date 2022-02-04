/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 01-02-2022 12:33:55 PM
 */
public class ConsultaConciliacionEtapa3DTO {

	private String corresponsal;
	private List<Long> listaSubEstatus;

	public ConsultaConciliacionEtapa3DTO() {
		super();
	}

	public ConsultaConciliacionEtapa3DTO(List<Long> listaSubEstatus , String corresponsal) {
		super();
		this.listaSubEstatus = listaSubEstatus;
		this.corresponsal = corresponsal;
	}

	public String getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(String corresponsal) {
		this.corresponsal = corresponsal;
	}

	public List<Long> getListaSubEstatus() {
		return listaSubEstatus;
	}

	public void setListaSubEstatus(List<Long> listaSubEstatus) {
		this.listaSubEstatus = listaSubEstatus;
	}

	@Override
	public String toString() {
		return "ConsultaConciliacionEtapa2DTO [ listaSubEstatus=" + listaSubEstatus + ", corresponsal=" + corresponsal + "]";
	}

}