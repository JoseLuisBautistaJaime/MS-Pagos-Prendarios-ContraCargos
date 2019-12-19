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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @name MovimientoEstadoCuenta
 * 
 * @description Encapsula la informacion de un movimiento de estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:58 PM
 */
@Entity
@Table(name = "to_movimiento_estado_cuenta")
public class MovimientoEstadoCuenta implements Comparable<MovimientoEstadoCuenta>, MovimientoReporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "estado_cuenta")
	private Long idEstadoCuenta;

	@Column(name = "clave_pais")
	private String clavePais;

	@Column(name = "sucursal")
	private String sucursal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_operacion")
	private Date fechaOperacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_valor")
	private Date fechaValor;

	@Column(name = "libre")
	private String libre;

	@Column(name = "clave_leyenda")
	private String claveLeyenda;

	@Column(name = "tipo_movimiento")
	private Integer tipoMovimiento;

	@Column(name = "importe")
	private BigDecimal importe;

	@Column(name = "dato")
	private String dato;

	@Column(name = "concepto")
	private String concepto;

	@Column(name = "codigo_dato")
	private String codigoDato;

	@Column(name = "referencia_ampliada")
	private String referenciaAmpliada;

	@Column(name = "referencia")
	private String referencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEstadoCuenta() {
		return idEstadoCuenta;
	}

	public void setIdEstadoCuenta(Long idEstadoCuenta) {
		this.idEstadoCuenta = idEstadoCuenta;
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

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public String getLibre() {
		return libre;
	}

	public void setLibre(String libre) {
		this.libre = libre;
	}

	public String getClaveLeyenda() {
		return claveLeyenda;
	}

	public void setClaveLeyenda(String claveLeyenda) {
		this.claveLeyenda = claveLeyenda;
	}

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCodigoDato() {
		return codigoDato;
	}

	public void setCodigoDato(String codigoDato) {
		this.codigoDato = codigoDato;
	}

	public String getReferenciaAmpliada() {
		return referenciaAmpliada;
	}

	public void setReferenciaAmpliada(String referenciaAmpliada) {
		this.referenciaAmpliada = referenciaAmpliada;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public MovimientoMidas getMovimientoMidas() {
		return null;
	}

	@Override
	public String toString() {
		return "MovimientoEstadoCuenta [id=" + id + ", idEstadoCuenta=" + idEstadoCuenta + ", clavePais=" + clavePais
				+ ", sucursal=" + sucursal + ", fechaOperacion=" + fechaOperacion + ", fechaValor=" + fechaValor
				+ ", libre=" + libre + ", claveLeyenda=" + claveLeyenda + ", tipoMovimiento=" + tipoMovimiento
				+ ", importe=" + importe + ", dato=" + dato + ", concepto=" + concepto + ", codigoDato=" + codigoDato
				+ ", referenciaAmpliada=" + referenciaAmpliada + ", referencia=" + referencia + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idEstadoCuenta, clavePais, sucursal, fechaOperacion, fechaValor, libre, claveLeyenda,
				tipoMovimiento, importe, dato, concepto, codigoDato, referenciaAmpliada, referencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoEstadoCuenta))
			return false;

		final MovimientoEstadoCuenta other = (MovimientoEstadoCuenta) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(MovimientoEstadoCuenta arg0) {
		return arg0.id.compareTo(this.id);
	}

}