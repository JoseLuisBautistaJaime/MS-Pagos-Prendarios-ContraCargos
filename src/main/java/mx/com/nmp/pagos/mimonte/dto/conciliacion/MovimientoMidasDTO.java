/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovimientoMidasDTO
 * @description Clase que encapsula la informacion de un movimiento midas
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:35 PM
 */
public class MovimientoMidasDTO implements Comparable<MovimientoMidasDTO> {

	private Long id;
	private Long folioPartida;
	private Long transaccion;
	private Integer sucursal;
	private String estadoTransaccion;
	private Date fecha;
	private String operacionAbr;
	private String operacionDesc;
	private BigDecimal montoOperacion;
	private String tipoContratoAbr;
	private String tipoContratoDesc;
	private String numAutorizacion;
	private BigDecimal capitalActual;
	private BigDecimal comisiones;
	private BigDecimal interes;
	private Boolean estatus;
	private String consumidor;
	private BigDecimal importeTransaccion;
	
	private long operaciones;

	public MovimientoMidasDTO() {
		super();
	}

	public MovimientoMidasDTO(Integer sucursal, BigDecimal montoOperacion, String operacionAbr) {
		this.sucursal = sucursal;
		this.montoOperacion = montoOperacion;
		this.operacionAbr = operacionAbr;
	}

	public MovimientoMidasDTO(Integer sucursal, BigDecimal montoOperacion, long operaciones) {
		this.sucursal = sucursal;
		this.montoOperacion = montoOperacion;
		this.operaciones = operaciones;
	}


	public MovimientoMidasDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Long folioPartida) {
		this.folioPartida = folioPartida;
	}

	public Long getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Long transaccion) {
		this.transaccion = transaccion;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacionAbr() {
		return operacionAbr;
	}

	public void setOperacionAbr(String operacionAbr) {
		this.operacionAbr = operacionAbr;
	}

	public String getOperacionDesc() {
		return operacionDesc;
	}

	public void setOperacionDesc(String operacionDesc) {
		this.operacionDesc = operacionDesc;
	}

	public BigDecimal getMontoOperacion() {
		return montoOperacion;
	}

	public void setMontoOperacion(BigDecimal montoOperacion) {
		this.montoOperacion = montoOperacion;
	}

	public String getTipoContratoAbr() {
		return tipoContratoAbr;
	}

	public void setTipoContratoAbr(String tipoContratoAbr) {
		this.tipoContratoAbr = tipoContratoAbr;
	}

	public String getTipoContratoDesc() {
		return tipoContratoDesc;
	}

	public void setTipoContratoDesc(String tipoContratoDesc) {
		this.tipoContratoDesc = tipoContratoDesc;
	}

	public String getNumAutorizacion() {
		return numAutorizacion;
	}

	public void setNumAutorizacion(String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}

	public BigDecimal getCapitalActual() {
		return capitalActual;
	}

	public void setCapitalActual(BigDecimal capitalActual) {
		this.capitalActual = capitalActual;
	}

	public BigDecimal getComisiones() {
		return comisiones;
	}

	public void setComisiones(BigDecimal comisiones) {
		this.comisiones = comisiones;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public String getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(String idConsumidor) {
		this.consumidor = idConsumidor;
	}

	public BigDecimal getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(BigDecimal importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

	public long getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(long operaciones) {
		this.operaciones = operaciones;
	}

	@Override
	public String toString() {
		return "MovimientoMidasDTO [id=" + id + ", folioPartida=" + folioPartida + ", transaccion=" + transaccion
				+ ", sucursal=" + sucursal + ", estadoTransaccion=" + estadoTransaccion + ", fecha=" + fecha
				+ ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc + ", montoOperacion="
				+ montoOperacion + ", tipoContratoAbr=" + tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc
				+ ", numAutorizacion=" + numAutorizacion + ", capitalActual=" + capitalActual + ", comisiones="
				+ comisiones + ", interes=" + interes + ", estatus=" + estatus + ", consumidor=" + consumidor
				+ ", importeTransaccion=" + importeTransaccion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capitalActual == null) ? 0 : capitalActual.hashCode());
		result = prime * result + ((comisiones == null) ? 0 : comisiones.hashCode());
		result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((estadoTransaccion == null) ? 0 : estadoTransaccion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((folioPartida == null) ? 0 : folioPartida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((importeTransaccion == null) ? 0 : importeTransaccion.hashCode());
		result = prime * result + ((interes == null) ? 0 : interes.hashCode());
		result = prime * result + ((montoOperacion == null) ? 0 : montoOperacion.hashCode());
		result = prime * result + ((numAutorizacion == null) ? 0 : numAutorizacion.hashCode());
		result = prime * result + ((operacionAbr == null) ? 0 : operacionAbr.hashCode());
		result = prime * result + ((operacionDesc == null) ? 0 : operacionDesc.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((tipoContratoAbr == null) ? 0 : tipoContratoAbr.hashCode());
		result = prime * result + ((tipoContratoDesc == null) ? 0 : tipoContratoDesc.hashCode());
		result = prime * result + ((transaccion == null) ? 0 : transaccion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoMidasDTO other = (MovimientoMidasDTO) obj;
		if (capitalActual == null) {
			if (other.capitalActual != null)
				return false;
		} else if (!capitalActual.equals(other.capitalActual))
			return false;
		if (comisiones == null) {
			if (other.comisiones != null)
				return false;
		} else if (!comisiones.equals(other.comisiones))
			return false;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (estadoTransaccion == null) {
			if (other.estadoTransaccion != null)
				return false;
		} else if (!estadoTransaccion.equals(other.estadoTransaccion))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (folioPartida == null) {
			if (other.folioPartida != null)
				return false;
		} else if (!folioPartida.equals(other.folioPartida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (importeTransaccion == null) {
			if (other.importeTransaccion != null)
				return false;
		} else if (!importeTransaccion.equals(other.importeTransaccion))
			return false;
		if (interes == null) {
			if (other.interes != null)
				return false;
		} else if (!interes.equals(other.interes))
			return false;
		if (montoOperacion == null) {
			if (other.montoOperacion != null)
				return false;
		} else if (!montoOperacion.equals(other.montoOperacion))
			return false;
		if (numAutorizacion == null) {
			if (other.numAutorizacion != null)
				return false;
		} else if (!numAutorizacion.equals(other.numAutorizacion))
			return false;
		if (operacionAbr == null) {
			if (other.operacionAbr != null)
				return false;
		} else if (!operacionAbr.equals(other.operacionAbr))
			return false;
		if (operacionDesc == null) {
			if (other.operacionDesc != null)
				return false;
		} else if (!operacionDesc.equals(other.operacionDesc))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (tipoContratoAbr == null) {
			if (other.tipoContratoAbr != null)
				return false;
		} else if (!tipoContratoAbr.equals(other.tipoContratoAbr))
			return false;
		if (tipoContratoDesc == null) {
			if (other.tipoContratoDesc != null)
				return false;
		} else if (!tipoContratoDesc.equals(other.tipoContratoDesc))
			return false;
		if (transaccion == null) {
			if (other.transaccion != null)
				return false;
		} else if (!transaccion.equals(other.transaccion))
			return false;
		return true;
	}

	@Override
	public int compareTo(MovimientoMidasDTO arg0) {
		return arg0.id.compareTo(this.id);
	}

}