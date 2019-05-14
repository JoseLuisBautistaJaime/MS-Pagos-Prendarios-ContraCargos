/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @name EstadoCuentaTotalesAdicional
 * 
 * @description Encapsula informacion de totale adicionales de un estado de
 *              cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:46 PM
 */
@Entity
@Table(name = "to_estado_cuenta_totales_adicional")
public class EstadoCuentaTotalesAdicional implements Comparable<EstadoCuentaTotalesAdicional> {

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

	@Column(name = "no_cargos")
	private String noCargos;

	@Column(name = "importe_total_cargos")
	private BigDecimal importeTotalCargos;

	@Column(name = "no_abonos")
	private Integer noAbonos;

	@Column(name = "importe_total_abonos")
	private BigDecimal importeTotalAbonos;

	@Column(name = "tipo_saldo")
	private Integer tipoSaldo;

	@Column(name = "saldo_final")
	private BigDecimal saldoFinal;

	@Column(name = "moneda_alfabetica")
	private String monedaAlfabetica;

	@OneToMany(mappedBy = "totalesAdicional", fetch = FetchType.LAZY)
	private Set<EstadoCuenta> estadoCuentaSet;

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

	public String getNoCargos() {
		return noCargos;
	}

	public void setNoCargos(String noCargos) {
		this.noCargos = noCargos;
	}

	public BigDecimal getImporteTotalCargos() {
		return importeTotalCargos;
	}

	public void setImporteTotalCargos(BigDecimal importeTotalCargos) {
		this.importeTotalCargos = importeTotalCargos;
	}

	public Integer getNoAbonos() {
		return noAbonos;
	}

	public void setNoAbonos(Integer noAbonos) {
		this.noAbonos = noAbonos;
	}

	public BigDecimal getImporteTotalAbonos() {
		return importeTotalAbonos;
	}

	public void setImporteTotalAbonos(BigDecimal importeTotalAbonos) {
		this.importeTotalAbonos = importeTotalAbonos;
	}

	public Integer getTipoSaldo() {
		return tipoSaldo;
	}

	public void setTipoSaldo(Integer tipoSaldo) {
		this.tipoSaldo = tipoSaldo;
	}

	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getMonedaAlfabetica() {
		return monedaAlfabetica;
	}

	public void setMonedaAlfabetica(String monedaAlfabetica) {
		this.monedaAlfabetica = monedaAlfabetica;
	}

	public Set<EstadoCuenta> getEstadoCuentaSet() {
		return estadoCuentaSet;
	}

	public void setEstadoCuentaSet(Set<EstadoCuenta> estadoCuentaSet) {
		this.estadoCuentaSet = estadoCuentaSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clavePais, sucursal, cuenta, noCargos, importeTotalCargos, noAbonos, importeTotalAbonos,
				tipoSaldo, saldoFinal, monedaAlfabetica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EstadoCuentaTotalesAdicional))
			return false;

		final EstadoCuentaTotalesAdicional other = (EstadoCuentaTotalesAdicional) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "EstadoCuentaTotalesAdicional [id=" + id + ", clavePais=" + clavePais + ", sucursal=" + sucursal
				+ ", cuenta=" + cuenta + ", noCargos=" + noCargos + ", importeTotalCargos=" + importeTotalCargos
				+ ", noAbonos=" + noAbonos + ", importeTotalAbonos=" + importeTotalAbonos + ", tipoSaldo=" + tipoSaldo
				+ ", saldoFinal=" + saldoFinal + ", monedaAlfabetica=" + monedaAlfabetica + ", estadoCuentaSet="
				+ estadoCuentaSet + "]";
	}

	@Override
	public int compareTo(EstadoCuentaTotalesAdicional o) {
		return o.id.compareTo(this.id);
	}

}