/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;

/**
 * @name MovimientoMidas
 * @description Encapsula la informacion de los movimientos de midas
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:57 PM
 */
@Entity
@Table(name = "to_movimiento_midas")
public class MovimientoMidas implements Comparable<MovimientoMidas> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false, unique = true)
	private Long id;

	@Column(name = "id_reporte", nullable = false)
	private Long reporte;

	@Column(name = "transaccion", nullable = false)
	private Long transaccion;

	@Column(name = "estado_transaccion", nullable = false)
	private String estadoTransaccion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "folio", nullable = true)
	private Long folio;

	@Column(name = "sucursal", nullable = true)
	private Integer sucursal;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "operacion_abr", nullable = true)
	private String operacionAbr;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "operacion_desc", nullable = true)
	private String operacionDesc;

	@Column(name = "monto", nullable = true)
	private BigDecimal monto;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "tipo_contrato_abr", nullable = true)
	private String tipoContratoAbr;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "tipo_contrato_desc", nullable = true)
	private String tipoContratoDesc;

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_45, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_45)
	@Column(name = "num_autorizacion", nullable = true)
	private String numAutorizacion;

	@Column(name = "capital", nullable = true)
	private BigDecimal capital;

	@Column(name = "comisiones", nullable = true)
	private BigDecimal comisiones;

	@Column(name = "interes", nullable = true)
	private BigDecimal interes;

	@Column(name = "estatus", nullable = true)
	private Boolean estatus;

	@Column(name = "consumidor")
	private String consumidor;

	@Column(name = "codigo_error", nullable = true)
	private String codigoError;

	@Column(name = "mensaje_error", nullable = true)
	private String mensajeError;

	@Column(name = "id_tarjeta", nullable = true)
	private String idTarjeta;

	@Column(name = "marca_tarjeta", nullable = true)
	private String marcaTarjeta;

	@Column(name = "tipo_tarjeta", nullable = true)
	private String tipoTarjeta;

	@Column(name = "tarjeta", nullable = true)
	private String tarjeta;

	@Column(name = "moneda_pago", nullable = true)
	private String monedaPago;

	@Column(name = "importe_transaccion", nullable = true)
	private BigDecimal importeTransaccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReporte() {
		return reporte;
	}

	public void setReporte(Long reporte) {
		this.reporte = reporte;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
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

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
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

	public Long getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Long transaccion) {
		this.transaccion = transaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getMarcaTarjeta() {
		return marcaTarjeta;
	}

	public void setMarcaTarjeta(String marcaTarjeta) {
		this.marcaTarjeta = marcaTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getMonedaPago() {
		return monedaPago;
	}

	public void setMonedaPago(String monedaPago) {
		this.monedaPago = monedaPago;
	}

	public BigDecimal getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(BigDecimal importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

	@Override
	public String toString() {
		return "MovimientoMidas [id=" + id + ", reporte=" + reporte + ", transaccion=" + transaccion
				+ ", estadoTransaccion=" + estadoTransaccion + ", fecha=" + fecha + ", folio=" + folio + ", sucursal="
				+ sucursal + ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc + ", monto=" + monto
				+ ", tipoContratoAbr=" + tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc
				+ ", numAutorizacion=" + numAutorizacion + ", capital=" + capital + ", comisiones=" + comisiones
				+ ", interes=" + interes + ", estatus=" + estatus + ", consumidor=" + consumidor + ", codigoError="
				+ codigoError + ", mensajeError=" + mensajeError + ", idTarjeta=" + idTarjeta + ", marcaTarjeta="
				+ marcaTarjeta + ", tipoTarjeta=" + tipoTarjeta + ", tarjeta=" + tarjeta + ", monedaPago=" + monedaPago
				+ ", importeTransaccion=" + importeTransaccion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((codigoError == null) ? 0 : codigoError.hashCode());
		result = prime * result + ((comisiones == null) ? 0 : comisiones.hashCode());
		result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((estadoTransaccion == null) ? 0 : estadoTransaccion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((folio == null) ? 0 : folio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTarjeta == null) ? 0 : idTarjeta.hashCode());
		result = prime * result + ((importeTransaccion == null) ? 0 : importeTransaccion.hashCode());
		result = prime * result + ((interes == null) ? 0 : interes.hashCode());
		result = prime * result + ((marcaTarjeta == null) ? 0 : marcaTarjeta.hashCode());
		result = prime * result + ((mensajeError == null) ? 0 : mensajeError.hashCode());
		result = prime * result + ((monedaPago == null) ? 0 : monedaPago.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((numAutorizacion == null) ? 0 : numAutorizacion.hashCode());
		result = prime * result + ((operacionAbr == null) ? 0 : operacionAbr.hashCode());
		result = prime * result + ((operacionDesc == null) ? 0 : operacionDesc.hashCode());
		result = prime * result + ((reporte == null) ? 0 : reporte.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((tarjeta == null) ? 0 : tarjeta.hashCode());
		result = prime * result + ((tipoContratoAbr == null) ? 0 : tipoContratoAbr.hashCode());
		result = prime * result + ((tipoContratoDesc == null) ? 0 : tipoContratoDesc.hashCode());
		result = prime * result + ((tipoTarjeta == null) ? 0 : tipoTarjeta.hashCode());
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
		MovimientoMidas other = (MovimientoMidas) obj;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (codigoError == null) {
			if (other.codigoError != null)
				return false;
		} else if (!codigoError.equals(other.codigoError))
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
		if (folio == null) {
			if (other.folio != null)
				return false;
		} else if (!folio.equals(other.folio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTarjeta == null) {
			if (other.idTarjeta != null)
				return false;
		} else if (!idTarjeta.equals(other.idTarjeta))
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
		if (marcaTarjeta == null) {
			if (other.marcaTarjeta != null)
				return false;
		} else if (!marcaTarjeta.equals(other.marcaTarjeta))
			return false;
		if (mensajeError == null) {
			if (other.mensajeError != null)
				return false;
		} else if (!mensajeError.equals(other.mensajeError))
			return false;
		if (monedaPago == null) {
			if (other.monedaPago != null)
				return false;
		} else if (!monedaPago.equals(other.monedaPago))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
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
		if (reporte == null) {
			if (other.reporte != null)
				return false;
		} else if (!reporte.equals(other.reporte))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (tarjeta == null) {
			if (other.tarjeta != null)
				return false;
		} else if (!tarjeta.equals(other.tarjeta))
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
		if (tipoTarjeta == null) {
			if (other.tipoTarjeta != null)
				return false;
		} else if (!tipoTarjeta.equals(other.tipoTarjeta))
			return false;
		if (transaccion == null) {
			if (other.transaccion != null)
				return false;
		} else if (!transaccion.equals(other.transaccion))
			return false;
		return true;
	}

	@Override
	public int compareTo(MovimientoMidas o) {
		return o.id.compareTo(this.id);
	}

}