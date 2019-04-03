/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;
import java.util.List;

/**
 * @name ReporteRequestDTO
 * @description Clase que encapsula la informacion de un request para reporte
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 13:25 hrs.
 * @version 0.1
 */
public class ReporteRequestDTO implements Comparable<ReporteRequestDTO> {

	private Date fechaDesde;
	private Date fechaHasta;
	private String producto;
	private Integer operacion;
	private List<String> sucursal;
	private String partida;

	public ReporteRequestDTO() {
		super();
	}

	public ReporteRequestDTO(Date fechaDesde, Date fechaHasta, String producto, Integer operacion,
			List<String> sucursal, String partida) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.producto = producto;
		this.operacion = operacion;
		this.sucursal = sucursal;
		this.partida = partida;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Integer getOperacion() {
		return operacion;
	}

	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}

	public List<String> getSucursal() {
		return sucursal;
	}

	public void setSucursal(List<String> sucursal) {
		this.sucursal = sucursal;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "ReporteRequestDTO [fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", producto=" + producto
				+ ", operacion=" + operacion + ", sucursal=" + sucursal + ", partida=" + partida + "]";
	}

	@Override
	public int compareTo(ReporteRequestDTO o) {
		return 0;
	}

}
