package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:51 PM
 */
@Entity
@Table(name = "to_movimiento_comision")
public class MovimientoComision extends MovimientoConciliacion implements Serializable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 2417290206180226131L;
	
	@Column(name = "fecha_operacion")
	private Date fechaOperacion;
	
	@Column(name = "fecha_cargo")
	private Date fechaCargo;
	
	@Column(name = "monto")
	private BigDecimal monto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estatus")
	private Boolean estatus;

	public MovimientoComision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovimientoComision(Date fechaOperacion, Date fechaCargo, BigDecimal monto, String descripcion, Boolean estatus) {
		super();
		this.fechaOperacion = fechaOperacion;
		this.fechaCargo = fechaCargo;
		this.monto = monto;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fechaCargo == null) ? 0 : fechaCargo.hashCode());
		result = prime * result + ((fechaOperacion == null) ? 0 : fechaOperacion.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoComision other = (MovimientoComision) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (fechaCargo == null) {
			if (other.fechaCargo != null)
				return false;
		} else if (!fechaCargo.equals(other.fechaCargo))
			return false;
		if (fechaOperacion == null) {
			if (other.fechaOperacion != null)
				return false;
		} else if (!fechaOperacion.equals(other.fechaOperacion))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimientoComision [fechaOperacion=" + fechaOperacion + ", fechaCargo=" + fechaCargo + ", monto="
				+ monto + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}

}