/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ReportePagosLibresDTO
 * @description Clase que encapsula la informacion de una respuesta de reporte
 *              de pagos en linea
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 13:30 hrs.
 * @version 0.1
 */
public class ReportePagosLibresDTO implements Comparable<ReportePagosLibresDTO> {

	private Date fecha;
	private String canal;
	private Long partida;
	private String tipoProducto;
	private String operacion;
	private Integer sucursal;
	private Double monto;

	public ReportePagosLibresDTO() {
		super();
	}

	public ReportePagosLibresDTO(Date fecha, String canal, Long partida, String tipoProducto, String operacion,
			Integer sucursal, Double monto) {
		super();
		this.fecha = fecha;
		this.canal = canal;
		this.partida = partida;
		this.tipoProducto = tipoProducto;
		this.operacion = operacion;
		this.sucursal = sucursal;
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public Long getPartida() {
		return partida;
	}

	public void setPartida(Long partida) {
		this.partida = partida;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	

	@Override
	public int compareTo(ReportePagosLibresDTO o) {
		return 0;
	}

}
