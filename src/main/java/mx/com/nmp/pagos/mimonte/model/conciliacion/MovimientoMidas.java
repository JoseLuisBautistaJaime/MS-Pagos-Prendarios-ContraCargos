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
public class MovimientoMidas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false, unique = true)
	private Long id;

	@Column(name = "id_reporte", nullable = false)
	private Long idReporte;

	@Column(name = "transaccion", nullable = false)
	private Long transaccion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "folio", nullable = true)
	private Long folio;

	@Column(name = "sucursal", nullable = true)
	private Long sucursal;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
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

	@Override
	public String toString() {
		return "MovimientoMidas [id=" + id + ", idReporte=" + idReporte + ", sucursal=" + sucursal + ", folio=" + folio
				+ ", operacionAbr=" + operacionAbr + ", operacionDesc=" + operacionDesc + ", monto=" + monto
				+ ", tipoContratoAbr=" + tipoContratoAbr + ", tipoContratoDesc=" + tipoContratoDesc
				+ ", numAutorizacion=" + numAutorizacion + ", capital=" + capital + ", comisiones=" + comisiones
				+ ", interes=" + interes + ", estatus=" + estatus + "]";
	}

}