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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "id_reporte", nullable = false)
	private Reporte reporte;

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

	@Size(max = ConciliacionConstants.ENTITY_VALIDATION_SIZE_VALUE_10, message = ConciliacionConstants.ENTITY_VALIDATION_SIZE_MESSAGE_10)
	@Column(name = "estatus", nullable = true)
	private String estatus;

	@Column(name = "canal")
	private String canal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
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

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
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

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	@Override
	public String toString() {
		return "MovimientoMidas [id=" + id + ", reporte=" + reporte + ", transaccion=" + transaccion
				+ ", estadoTransaccion=" + estadoTransaccion + ", fecha=" + fecha + ", folio=" + folio + ", sucursal="
				+ sucursal + ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc + ", monto=" + monto
				+ ", tipoContratoAbr=" + tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc
				+ ", numAutorizacion=" + numAutorizacion + ", capital=" + capital + ", comisiones=" + comisiones
				+ ", interes=" + interes + ", estatus=" + estatus + ", canal=" + canal + "]";
	}

	@Override
	public int compareTo(MovimientoMidas o) {
		return o.id.compareTo(this.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canal == null) ? 0 : canal.hashCode());
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((comisiones == null) ? 0 : comisiones.hashCode());
		result = prime * result + ((estadoTransaccion == null) ? 0 : estadoTransaccion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((folio == null) ? 0 : folio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interes == null) ? 0 : interes.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((numAutorizacion == null) ? 0 : numAutorizacion.hashCode());
		result = prime * result + ((operacionAbr == null) ? 0 : operacionAbr.hashCode());
		result = prime * result + ((operacionDesc == null) ? 0 : operacionDesc.hashCode());
		result = prime * result + ((reporte == null) ? 0 : reporte.hashCode());
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
		MovimientoMidas other = (MovimientoMidas) obj;
		if (canal == null) {
			if (other.canal != null)
				return false;
		} else if (!canal.equals(other.canal))
			return false;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (comisiones == null) {
			if (other.comisiones != null)
				return false;
		} else if (!comisiones.equals(other.comisiones))
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
		if (interes == null) {
			if (other.interes != null)
				return false;
		} else if (!interes.equals(other.interes))
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

}