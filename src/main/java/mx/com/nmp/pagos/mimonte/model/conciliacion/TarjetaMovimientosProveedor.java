/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @name TarjetaMovimientosProveedor
 * @description Clase que encapsula la informacion relacionada con un objeto
 *              tarjeta embebido en un movimiento de proveedor transaccional
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/05/2019 12203 hrs.
 * @version 0.1
 */
@Embeddable
public class TarjetaMovimientosProveedor {

	@Column(name = "card_id")
	private String id;

	@Column(name = "card_type")
	private String type;

	@Column(name = "card_brand")
	private String brand;

	@Column(name = "card_address")
	private String address;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "card_holder_name")
	private String holderName;

	@Column(name = "card_expiration_year")
	private String expirationYear;

	@Column(name = "card_expiration_month")
	private String expirationMonth;

	@Column(name = "card_allows_charges")
	private Boolean allowsCharges;

	@Column(name = "card_allows_payouts")
	private Boolean allowsPayouts;

	@Column(name = "card_creation_date")
	private Date creationDate;

	@Column(name = "card_bank_name")
	private String bankName;

	@Column(name = "card_bank_code")
	private String bankCode;

	@Column(name = "card_customer_id")
	private String customerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Boolean getAllowsCharges() {
		return allowsCharges;
	}

	public void setAllowsCharges(Boolean allowsCharges) {
		this.allowsCharges = allowsCharges;
	}

	public Boolean getAllowsPayouts() {
		return allowsPayouts;
	}

	public void setAllowsPayouts(Boolean allowsPayouts) {
		this.allowsPayouts = allowsPayouts;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
