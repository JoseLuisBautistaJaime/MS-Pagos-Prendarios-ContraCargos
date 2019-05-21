/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name EstadoCuentaCabecera
 * 
 * @description Encapsula informacion relacionada con una cabecera de estado de
 *              cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:44 PM
 */
@Entity
@Table(name = "to_estado_cuenta_cabecera")
public class EstadoCuentaCabecera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "clave_pais")
	private String clavePais;

	@Column(name = "sucursal")
	private String sucursal;

	@Column(name = "cuenta")
	private String cuenta;

	@Column(name = "fecha_inicial")
	private Date fechaInicial;

	@Column(name = "fecha_final")
	private Date fechaFinal;

	@Column(name = "tipo_saldo")
	private Integer tipoSaldo;

	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial;

	@Column(name = "moneda_alfabetica")
	private String monedaAlfabetica;

	@Column(name = "digito_cuenta_clabe")
	private String digitoCuentaClabe;

	@Column(name = "titular_cuenta")
	private String titularCuenta;

	@Column(name = "plaza_cuenta_clabe")
	private String plazaCuentaClabe;

	@Column(name = "libre")
	private String libre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClavePais() {
		return clavePais;
	}

	public void setClavePais(String clavePais) {
		this.clavePais = clavePais;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Integer getTipoSaldo() {
		return tipoSaldo;
	}

	public void setTipoSaldo(Integer tipoSaldo) {
		this.tipoSaldo = tipoSaldo;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getMonedaAlfabetica() {
		return monedaAlfabetica;
	}

	public void setMonedaAlfabetica(String monedaAlfabetica) {
		this.monedaAlfabetica = monedaAlfabetica;
	}

	public String getDigitoCuentaClabe() {
		return digitoCuentaClabe;
	}

	public void setDigitoCuentaClabe(String digitoCuentaClabe) {
		this.digitoCuentaClabe = digitoCuentaClabe;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public String getPlazaCuentaClabe() {
		return plazaCuentaClabe;
	}

	public void setPlazaCuentaClabe(String plazaCuentaClabe) {
		this.plazaCuentaClabe = plazaCuentaClabe;
	}

	public String getLibre() {
		return libre;
	}

	public void setLibre(String libre) {
		this.libre = libre;
	}

	@Override
	public String toString() {
		return "EstadoCuentaCabecera [id=" + id + ", clavePais=" + clavePais + ", sucursal=" + sucursal + ", cuenta="
				+ cuenta + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", tipoSaldo=" + tipoSaldo
				+ ", saldoInicial=" + saldoInicial + ", monedaAlfabetica="
				+ monedaAlfabetica + ", digitoCuentaClabe=" + digitoCuentaClabe + ", titularCuenta=" + titularCuenta
				+ ", plazaCuentaClabe=" + plazaCuentaClabe + ", libre=" + libre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clavePais, sucursal, cuenta, fechaInicial, fechaFinal, tipoSaldo, saldoInicial,
				monedaAlfabetica, digitoCuentaClabe, titularCuenta, plazaCuentaClabe, libre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EstadoCuentaCabecera))
			return false;

		final EstadoCuentaCabecera other = (EstadoCuentaCabecera) obj;
		return (this.hashCode() == other.hashCode());

	}

}