/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @name ReportePagosEnLineaDTO
 * @description Clase que encapsula la informacion de una respuesta de reporte
 *              de pagos en linea
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 13:30 hrs.
 * @version 0.1
 */
public class ReportePagosEnLineaDTO {

	private SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	
	private Date fecha;
	private String canal;
	private Long partida;
	private String tipoProducto;
	private String operacion;
	private Integer sucursal;
	private BigDecimal monto;
	private Integer idTipoContrato;
	private Integer idOperacion;


	public ReportePagosEnLineaDTO() {
		super();
	}

	public ReportePagosEnLineaDTO(Date fecha, String canal, Long partida, String tipoProducto, String operacion,
			Integer sucursal, BigDecimal monto) {
		super();
		this.fecha = fecha;
		this.canal = canal;
		this.partida = partida;
		this.tipoProducto = tipoProducto;
		this.operacion = operacion;
		this.sucursal = sucursal;
		this.monto = monto;
	}

	public ReportePagosEnLineaDTO(Date fecha, String canal, Long partida, String tipoProducto, String operacion,
								  Integer sucursal, BigDecimal monto, Integer idTipoContrato, Integer idOperacion ) {
		super();
		this.fecha = fecha;
		this.canal = canal;
		this.partida = partida;
		this.tipoProducto = tipoProducto;
		this.operacion = operacion;
		this.sucursal = sucursal;
		this.monto = monto;
		this.idTipoContrato = idTipoContrato;
		this.idOperacion = idOperacion;
	}

	public String getFecha() {
		return sf.format(fecha);
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(Integer idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	@Override
	public String toString() {
		return "ReportePagosEnLineaDTO [fecha=" + fecha + ", canal=" + canal + ", partida=" + partida + ", tipoProducto="
				+ tipoProducto + ", operacion=" + operacion + ", sucursal=" + sucursal + ", monto=" + monto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, canal, partida, tipoProducto, operacion, sucursal, monto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ReportePagosEnLineaDTO))
			return false;

		final ReportePagosEnLineaDTO other = (ReportePagosEnLineaDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

}
