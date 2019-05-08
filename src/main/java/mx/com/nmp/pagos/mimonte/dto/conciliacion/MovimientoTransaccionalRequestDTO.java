/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name MovimientoTransaccionalRequestDTO
 * @description Clase que encapsula la informacion relacionada con un respuetsa
 *              a el alta de MovimientosProveedor
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net
 * @creationDate 17/04/2019 18:10 hrs.
 * @version 0.1
 */
public class MovimientoTransaccionalRequestDTO {

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

	public void setIdMovimiento(String id) {
		this.idMovimiento = id;
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

}
