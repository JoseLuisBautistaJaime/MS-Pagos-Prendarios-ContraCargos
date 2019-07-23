/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name MovimientoEstadoCuentaDBDTO
 * @description Clase que encapsula la informacion relacionada con la consulta
 *              de movimientos de estado cuenta
 *
 * @author Ismael Flores iagular@quarksoft.net
 * @creationDate 13/05/2019 16:24 hrs.
 * @version 0.1
 */
public class MovimientoEstadoCuentaDBDTO implements Comparable<MovimientoEstadoCuentaDBDTO> {

	private Long id;
	private Date fecha;
	private String descripcion;
	private Integer tipoMovimiento;
	private BigDecimal importe;
	private BigDecimal totalInicial;
	private BigDecimal totalFinal;

	public MovimientoEstadoCuentaDBDTO(Long id, Date fecha, String descripcion, Integer tipoMovimiento,
			BigDecimal importe, BigDecimal totalInicial, BigDecimal totalFinal) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipoMovimiento = tipoMovimiento;
		this.importe = importe;
		this.totalInicial = totalInicial;
		this.totalFinal = totalFinal;
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

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getTotalInicial() {
		return totalInicial;
	}

	public void setTotalInicial(BigDecimal totalInicial) {
		this.totalInicial = totalInicial;
	}

	public BigDecimal getTotalFinal() {
		return totalFinal;
	}

	public void setTotalFinal(BigDecimal totalFinal) {
		this.totalFinal = totalFinal;
	}

	@Override
	public String toString() {
		return "MovimientoEstadoCuentaDBDTO [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion
				+ ", tipoMovimiento=" + tipoMovimiento + ", importe=" + importe + ", totalInicial=" + totalInicial
				+ ", totalFinal=" + totalFinal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fecha, descripcion, tipoMovimiento, importe, totalInicial, totalFinal);
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
	public int compareTo(MovimientoEstadoCuentaDBDTO arg0) {
		return arg0.id.compareTo(this.id);
	}

}
