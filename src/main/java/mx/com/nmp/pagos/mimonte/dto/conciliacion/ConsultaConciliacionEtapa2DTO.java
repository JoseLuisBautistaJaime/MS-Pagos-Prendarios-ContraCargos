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
 * @created 21-01-2022 12:33:55 PM
 */
public class ConsultaConciliacionEtapa2DTO {

	private Date fechaDesde;
	private Date fechaHasta;
	private String corresponsal;
	private List<Long> listaSubEstatus;

	public ConsultaConciliacionEtapa2DTO() {
		super();
	}

	public ConsultaConciliacionEtapa2DTO(Date fechaDesde, Date fechaHasta, List<Long> listaSubEstatus ,String corresponsal) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.listaSubEstatus = listaSubEstatus;
		this.corresponsal = corresponsal;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
		return "ConsultaConciliacionEtapa2DTO [fechaDesde="	+ fechaDesde + ", fechaHasta=" + fechaHasta + ", listaSubEstatus=" + listaSubEstatus + ", corresponsal=" + corresponsal + "]";
	}

}