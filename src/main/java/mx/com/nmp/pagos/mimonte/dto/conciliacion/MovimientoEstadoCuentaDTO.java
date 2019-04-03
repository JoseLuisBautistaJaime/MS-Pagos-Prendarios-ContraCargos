/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

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
	private Double depositos;
	private Double retiros;
	private Double saldo;

	public MovimientoEstadoCuentaDTO() {
		super();
	}

	public MovimientoEstadoCuentaDTO(Long id, Date fecha, String descripcion, Double depositos, Double retiros,
			Double saldo) {
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

	public Double getDepositos() {
		return depositos;
	}

	public void setDepositos(Double depositos) {
		this.depositos = depositos;
	}

	public Double getRetiros() {
		return retiros;
	}

	public void setRetiros(Double retiros) {
		this.retiros = retiros;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "MovimientoEstadoCuentaDTO [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion
				+ ", depositos=" + depositos + ", retiros=" + retiros + ", saldo=" + saldo + "]";
	}

	@Override
	public int compareTo(MovimientoEstadoCuentaDTO arg0) {
		return arg0.id.compareTo(this.id);
	}

}