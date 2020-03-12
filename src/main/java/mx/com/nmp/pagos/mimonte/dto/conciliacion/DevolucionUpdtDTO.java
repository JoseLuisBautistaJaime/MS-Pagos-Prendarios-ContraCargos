/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name DevolucionUpdtDTO
 * @description Clase que encapsula la informacion de un objeto de tipo
 *              Devolucion para actualizacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 12:36 hrs.
 * @version 0.1
 */
public class DevolucionUpdtDTO implements Comparable<DevolucionUpdtDTO> {

	private Integer idMovimiento;
	private Date fecha;
	private Boolean liquidar;

	public DevolucionUpdtDTO() {
		super();
	}

	public DevolucionUpdtDTO(Integer idMovimiento, Date fecha, Boolean liquidar) {
		super();
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.liquidar = liquidar;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getLiquidar() {
		return liquidar;
	}

	public void setLiquidar(Boolean liquidar) {
		this.liquidar = liquidar;
	}

	@Override
	public String toString() {
		return "DevolucionUpdtDTO [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", liquidar=" + liquidar + "]";
	}

	@Override
	public int compareTo(DevolucionUpdtDTO o) {
		return o.idMovimiento.compareTo(this.idMovimiento);
	}

}
