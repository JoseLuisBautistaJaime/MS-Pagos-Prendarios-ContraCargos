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
public class MovimientoEstadoCuenta implements Comparable<MovimientoEstadoCuenta> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_reporte")
	private Long idReporte;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "depositos")
	private BigDecimal depositos;

	@Column(name = "retiros")
	private BigDecimal retiros;

	@Column(name = "saldo")
	private BigDecimal saldo;
	private long idEstadoCuenta;
	private String clavePais;
	private String sucursal;
	private Date fechaOperacion;
	private Date fechaValor;
	private String libre;
	private String claveLeyenda;
	private int tipoMovimiento;
	private BigDecimal importe;
	private String dato;
	private String concepto;
	private String codigoDato;
	private String referenciaAmpliada;
	private String referencia;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDepositos() {
		return depositos;
	}

	public void setDepositos(BigDecimal depositos) {
		this.depositos = depositos;
	}

	public BigDecimal getRetiros() {
		return retiros;
	}

	public void setRetiros(BigDecimal retiros) {
		this.retiros = retiros;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "MovimientoEstadoCuenta [id=" + id + ", idReporte=" + idReporte + ", fecha=" + fecha + ", descripcion="
				+ descripcion + ", depositos=" + depositos + ", retiros=" + retiros + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idReporte, fecha, descripcion, depositos, retiros, saldo);
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

	public void finalize() throws Throwable {

	}

}