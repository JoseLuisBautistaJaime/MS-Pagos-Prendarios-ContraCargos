/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class MovimientoMidas implements Comparable<MovimientoMidas>, java.io.Serializable, MovimientoReporte {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

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

	@Column(name = "id_operacion", nullable = true)
	private Integer idOperacion;

	@Column(name = "id_tipo_contrato", nullable = true)
	private Integer idTipoContrato;

	@OneToMany(mappedBy = "movimientoMidas")
	private Set<MovimientoConciliacion> movimientoConciliacionSet;

	public MovimientoMidas(Long id) {
		super();
		this.id = id;
	}
	
	public MovimientoMidas() {
		super();
	}
	
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

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(Integer idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public Set<MovimientoConciliacion> getMovimientoConciliacionSet() {
		return movimientoConciliacionSet;
	}

	public void setMovimientoConciliacionSet(Set<MovimientoConciliacion> movimientoConciliacionSet) {
		this.movimientoConciliacionSet = movimientoConciliacionSet;
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
				+ ", importeTransaccion=" + importeTransaccion + ", idOperacion=" + idOperacion + ", idTipoContrato="
				+ idTipoContrato + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reporte, transaccion, estadoTransaccion, fecha, folio, sucursal, operacionAbr,
				operacionDesc, monto, tipoContratoAbr, tipoContratoDesc, numAutorizacion, capital, comisiones, interes,
				estatus, consumidor, codigoError, mensajeError, idTarjeta, marcaTarjeta, tipoTarjeta, tarjeta,
				monedaPago, importeTransaccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoMidas))
			return false;

		final MovimientoMidas other = (MovimientoMidas) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(MovimientoMidas o) {
		return o.id.compareTo(this.id);
	}

}