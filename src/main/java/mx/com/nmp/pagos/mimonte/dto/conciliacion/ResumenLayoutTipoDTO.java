/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name ResumenLayoutTipoDTO
 * @description Clase que encapsula la informacion del resumen de un layout por tipo
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 30/03/2019 00:00:00 hrs.
 * @version 0.1
 */
public class ResumenLayoutTipoDTO {

	private BigDecimal cargos;
	private BigDecimal abonos;
	private TipoLayoutEnum tipoLayout;


	public ResumenLayoutTipoDTO() {
		super();
	}


	public ResumenLayoutTipoDTO(TipoLayoutEnum tipoLayout) {
		super();
		this.cargos = new BigDecimal(0);
		this.abonos = new BigDecimal(0);
		this.tipoLayout = tipoLayout;
	}

	public void add(BigDecimal monto) {
		if (monto != null) {
			if (monto.compareTo(new BigDecimal(0)) < 0) {
				this.cargos = this.cargos.add(monto);
			}
			else {
				this.abonos = this.abonos.add(monto);
			}
		}
	}

	public TipoLayoutEnum getTipoLayout() {
		return tipoLayout;
	}

	public void setTipoLayout(TipoLayoutEnum tipoLayout) {
		this.tipoLayout = tipoLayout;
	}

	public BigDecimal getCargos() {
		return cargos;
	}

	public void setCargos(BigDecimal cargos) {
		this.cargos = cargos;
	}

	public BigDecimal getAbonos() {
		return abonos;
	}

	public void setAbonos(BigDecimal abonos) {
		this.abonos = abonos;
	}

	@Override
	public String toString() {
		return "ResumenLayoutTipoDTO [cargos=" + cargos + ", abonos=" + abonos + ", tipoLayout=" + tipoLayout + "]";
	}

}