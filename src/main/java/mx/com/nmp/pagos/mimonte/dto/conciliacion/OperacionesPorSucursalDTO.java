/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name OperacionesPorSucursalDTO
 * @description Clase que encapsula la informacion de total de operaciones por sucursal
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:35 PM
 */
public class OperacionesPorSucursalDTO {

	private int total;
	private Integer sucursal;

	public OperacionesPorSucursalDTO() {
	}

	public OperacionesPorSucursalDTO(Integer sucursal, Integer total) {
		this.sucursal = sucursal;
		this.total = total != null ? total : 0;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

}