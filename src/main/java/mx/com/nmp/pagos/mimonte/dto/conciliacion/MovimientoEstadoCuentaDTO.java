/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name MovimientoEstadoCuentaDTO
 * @description Clase que encapsula la informacion relacionada con un Movimiento
 *              de estado de cuenta
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 31/03/2019 18:33:50 hrs.
 * @version 0.1
 */
public class MovimientoEstadoCuentaDTO implements Comparable<MovimientoEstadoCuentaDTO> {

	private Long id;
	private Date fecha;
	private String descripcion;
	private BigDecimal depositos;
	private BigDecimal retiros;
	private BigDecimal saldo;

	public MovimientoEstadoCuentaDTO() {
		super();
	}

	public MovimientoEstadoCuentaDTO(Long id, Date fecha, String descripcion, BigDecimal depositos, BigDecimal retiros,
			BigDecimal saldo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.depositos = depositos;
		this.retiros = retiros;
		this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "MovimientoEstadoCuentaDTO [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion
				+ ", depositos=" + depositos + ", retiros=" + retiros + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fecha, descripcion, depositos, retiros, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof MovimientoEstadoCuentaDTO))
			return false;

		final MovimientoEstadoCuentaDTO other = (MovimientoEstadoCuentaDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(MovimientoEstadoCuentaDTO arg0) {
		return arg0.id.compareTo(this.id);
	}

}