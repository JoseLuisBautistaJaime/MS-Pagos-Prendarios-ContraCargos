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
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:49 PM
 */
@Entity
@Table(name = "to_movimiento_devolucion")
public class MovimientoDevolucion extends MovimientoConciliacion implements Serializable{

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
		// TODO Auto-generated constructor stub
	}

	public MovimientoDevolucion(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
		// TODO Auto-generated constructor stub
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
		return Objects.hash(estatus, fecha, monto, esquemaTarjeta, identificadorCuenta, titular, codigoAutorizacion, sucursal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoDevolucion other = (MovimientoDevolucion) obj;
		if (codigoAutorizacion == null) {
			if (other.codigoAutorizacion != null)
				return false;
		} else if (!codigoAutorizacion.equals(other.codigoAutorizacion))
			return false;
		if (esquemaTarjeta == null) {
			if (other.esquemaTarjeta != null)
				return false;
		} else if (!esquemaTarjeta.equals(other.esquemaTarjeta))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (identificadorCuenta == null) {
			if (other.identificadorCuenta != null)
				return false;
		} else if (!identificadorCuenta.equals(other.identificadorCuenta))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimientoDevolucion [estatus=" + estatus + ", fecha=" + fecha + ", monto=" + monto
				+ ", esquemaTarjeta=" + esquemaTarjeta + ", identificadorCuenta=" + identificadorCuenta + ", titular="
				+ titular + ", codigoAutorizacion=" + codigoAutorizacion + ", sucursal=" + sucursal
				+ "]";
	}

}