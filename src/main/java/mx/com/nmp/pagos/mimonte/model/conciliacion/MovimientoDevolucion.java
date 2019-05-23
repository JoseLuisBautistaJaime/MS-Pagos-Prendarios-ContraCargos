/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;

/**
 * @name MovimientoDevolucion
 * 
 * @description encapsula informacion de un movimiento estado cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:49 PM
 */
@Entity
@Table(name = "to_movimiento_devolucion")
public class MovimientoDevolucion extends MovimientoConciliacion implements Serializable {

	/**
	 * Serial id;
	 */
	private static final long serialVersionUID = 8987562819738981175L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estatus")
	private EstatusDevolucion estatus;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "esquema_tarjeta")
	private String esquemaTarjeta;

	@Column(name = "identificador_cuenta")
	private String identificadorCuenta;

	@Column(name = "titular")
	private String titular;

	@Column(name = "codigo_autorizacion")
	private String codigoAutorizacion;

	@Column(name = "sucursal")
	private Integer sucursal;

	public MovimientoDevolucion() {
		super();
	}

	public MovimientoDevolucion(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public MovimientoDevolucion(EstatusDevolucion estatus, Date fecha, BigDecimal monto, String esquemaTarjeta,
			String identificadorCuenta, String titular, String codigoAutorizacion, Integer sucursal,
			Set<MovimientoConciliacion> movimientoConciliacionSet) {
		super();
		this.estatus = estatus;
		this.fecha = fecha;
		this.monto = monto;
		this.esquemaTarjeta = esquemaTarjeta;
		this.identificadorCuenta = identificadorCuenta;
		this.titular = titular;
		this.codigoAutorizacion = codigoAutorizacion;
		this.sucursal = sucursal;
	}

	public EstatusDevolucion getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusDevolucion estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
	}

	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}

	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estatus, fecha, monto, esquemaTarjeta, identificadorCuenta, titular, codigoAutorizacion,
				sucursal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoDevolucion))
			return false;

		final MovimientoDevolucion other = (MovimientoDevolucion) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "MovimientoDevolucion [estatus=" + estatus + ", fecha=" + fecha + ", monto=" + monto
				+ ", esquemaTarjeta=" + esquemaTarjeta + ", identificadorCuenta=" + identificadorCuenta + ", titular="
				+ titular + ", codigoAutorizacion=" + codigoAutorizacion + ", sucursal=" + sucursal + "]";
	}

}