/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name TarjetaMovimientosProveedorDTO
 * @description Clase que encapsula la informacion relacionada con un objeto
 *              tarjeta embebido en un movimiento de proveedor transaccional
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/05/2019 15:44 hrs.
 * @version 0.1
 */
public class TarjetaMovimientosProveedorDTO {

	private String id;
	private String type;
	private String brand;
	private String address;
	private String cardNumber;
	private String holderName;
	private String expirationYear;
	private String expirationMonth;
	private Boolean allowsCharges;
	private Boolean allowsPayouts;
	private Date creationDate;
	private String bankName;
	private String bankCode;
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

	@Override
	public String toString() {
		return "TarjetaMovimientosProveedorDTO [id=" + id + ", type=" + type + ", brand=" + brand + ", address="
				+ address + ", cardNumber=" + cardNumber + ", holderName=" + holderName + ", expirationYear="
				+ expirationYear + ", expirationMonth=" + expirationMonth + ", allowsCharges=" + allowsCharges
				+ ", allowsPayouts=" + allowsPayouts + ", creationDate=" + creationDate + ", bankName=" + bankName
				+ ", bankCode=" + bankCode + ", customerId=" + customerId + "]";
	}

}
