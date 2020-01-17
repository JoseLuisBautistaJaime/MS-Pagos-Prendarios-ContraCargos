/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name MovimientoProveedor
 * @description Encapsula la informacion de consulta de movimientos de proveedor
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:00 PM
 */
@Entity
@Table(name = "to_movimiento_proveedor")
public class MovimientoProveedor implements MovimientoReporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "id_movimiento", nullable = true)
	private String idMovimiento;

	@Column(name = "id_reporte", nullable = false)
	private Integer reporte;

	@Column(name = "authorization", nullable = false)
	private String authorization;

	@Column(name = "operation_type", nullable = false)
	private String operationType;

	@Column(name = "method", nullable = false)
	private String method;

	@Column(name = "transaction_type", nullable = false)
	private String transactionType;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "conciliated", nullable = false)
	private Boolean conciliated;

	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	@Column(name = "operation_date", nullable = false)
	private Date operationDate;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "error_message", nullable = false)
	private String errorMessage;

	@Column(name = "order_id", nullable = false)
	private String orderId;

	@Column(name = "customer_id", nullable = false)
	private String customerId;

	@Column(name = "error_code", nullable = false)
	private String errorCode;

	@Column(name = "currency", nullable = false)
	private String currency;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Embedded
	private MetodoPagoMovimientosProveedor metodoPagoMovimientosProveedor;

	@Embedded
	private TarjetaMovimientosProveedor tarjetaMovimientosProveedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(String idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getConciliated() {
		return conciliated;
	}

	public void setConciliated(Boolean conciliated) {
		this.conciliated = conciliated;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MetodoPagoMovimientosProveedor getMetodoPagoMovimientosProveedor() {
		return metodoPagoMovimientosProveedor;
	}

	public void setMetodoPagoMovimientosProveedor(MetodoPagoMovimientosProveedor metodoPagoMovimientosProveedor) {
		this.metodoPagoMovimientosProveedor = metodoPagoMovimientosProveedor;
	}

	public TarjetaMovimientosProveedor getTarjetaMovimientosProveedor() {
		return tarjetaMovimientosProveedor;
	}

	public void setTarjetaMovimientosProveedor(TarjetaMovimientosProveedor tarjetaMovimientosProveedor) {
		this.tarjetaMovimientosProveedor = tarjetaMovimientosProveedor;
	}

	public Integer getReporte() {
		return reporte;
	}

	public void setReporte(Integer reporte) {
		this.reporte = reporte;
	}

	@Override
	public MovimientoMidas getMovimientoMidas() {
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idMovimiento, reporte, authorization, operationType, method, transactionType, status,
				conciliated, creationDate, operationDate, description, errorMessage, orderId, customerId, errorCode,
				currency, amount, metodoPagoMovimientosProveedor, tarjetaMovimientosProveedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoProveedor))
			return false;

		final MovimientoProveedor other = (MovimientoProveedor) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "MovimientoProveedor [id=" + id + ", idMovimiento=" + idMovimiento + ", reporte=" + reporte
				+ ", authorization=" + authorization + ", operationType=" + operationType + ", method=" + method
				+ ", transactionType=" + transactionType + ", status=" + status + ", conciliated=" + conciliated
				+ ", creationDate=" + creationDate + ", operationDate=" + operationDate + ", description=" + description
				+ ", errorMessage=" + errorMessage + ", orderId=" + orderId + ", customerId=" + customerId
				+ ", errorCode=" + errorCode + ", currency=" + currency + ", amount=" + amount
				+ ", metodoPagoMovimientosProveedor=" + metodoPagoMovimientosProveedor
				+ ", tarjetaMovimientosProveedor=" + tarjetaMovimientosProveedor + "]";
	}

}