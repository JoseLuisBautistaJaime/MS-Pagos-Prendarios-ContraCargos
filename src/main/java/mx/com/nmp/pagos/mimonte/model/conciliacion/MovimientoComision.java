/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * @name MovimientoComision
 * 
 * @description encapsula informacion de un movimiento de tipo comision
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:51 PM
 */
@Entity
@Table(name = "to_movimiento_comision")
public class MovimientoComision extends MovimientoConciliacion implements Serializable {

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

	@Size(max = 150, message = "La longitud de la descripcion es muy grande")
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estatus")
	private Boolean estatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoMovimientoComisionEnum tipo;

	@Column(name="id_movimiento_estado_cuenta")
	private Long idMovimientoEstadoCuenta;

	public MovimientoComision() {
		super();
	}

	public MovimientoComision(Date fechaOperacion, Date fechaCargo, BigDecimal monto, String descripcion,
			Boolean estatus) {
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

	public TipoMovimientoComisionEnum getTipoComision() {
		return tipo;
	}

	public void setTipoComision(TipoMovimientoComisionEnum tipo) {
		this.tipo = tipo;
	}

	public Long getIdMovimientoEstadoCuenta() {
		return idMovimientoEstadoCuenta;
	}

	public void setIdMovimientoEstadoCuenta(Long idMovimientoEstadoCuenta) {
		this.idMovimientoEstadoCuenta = idMovimientoEstadoCuenta;
	}

	@Transient
	public MovimientoConciliacionEnum getTipoMovimiento() {
		return MovimientoConciliacionEnum.COMISIONES;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fechaOperacion, fechaCargo, monto, descripcion, estatus, tipo, idMovimientoEstadoCuenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoComision))
			return false;

		final MovimientoComision other = (MovimientoComision) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "MovimientoComision [fechaOperacion=" + fechaOperacion + ", fechaCargo=" + fechaCargo + ", monto="
				+ monto + ", descripcion=" + descripcion + ", estatus=" + estatus + ", tipo=" + tipo + ", idMovimientoEstadoCuenta=" + idMovimientoEstadoCuenta + "]";
	}

}