/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovimientoProveedorDTO
 * @description Encapsula la informacion d eun objeto de tipo movimientos
 *              proveedor
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:48 PM
 */
public class MovimientoProveedorDTO {

	private Integer id;
	private String idMovimiento;
	private String authorization;
	private String operationType;
	private String method;
	private String transactionType;
	private String status;
	private Boolean conciliated;
	private Date creationDate;
	private Date operationDate;
	private String description;
	private String errorMessage;
	private String orderId;
	private String customerId;
	private String errorCode;
	private String currency;
	private BigDecimal amount;
	private MetodoPagoMovimientosProveedorDTO paymentMethod;
	private TarjetaMovimientosProveedorDTO card;

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

	public MetodoPagoMovimientosProveedorDTO getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(MetodoPagoMovimientosProveedorDTO paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public TarjetaMovimientosProveedorDTO getCard() {
		return card;
	}

	public void setCard(TarjetaMovimientosProveedorDTO card) {
		this.card = card;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MovimientoProveedorDTO [id=" + id + ", idMovimiento=" + idMovimiento + ", authorization="
				+ authorization + ", operationType=" + operationType + ", method=" + method + ", transactionType="
				+ transactionType + ", status=" + status + ", conciliated=" + conciliated + ", creationDate="
				+ creationDate + ", operationDate=" + operationDate + ", description=" + description + ", errorMessage="
				+ errorMessage + ", orderId=" + orderId + ", customerId=" + customerId + ", errorCode=" + errorCode
				+ ", currency=" + currency + ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", card="
				+ card + "]";
	}

}