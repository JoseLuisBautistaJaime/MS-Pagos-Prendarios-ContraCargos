package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

public class ComisionesTransaccionesDTO implements Comparable<ComisionesTransaccionesDTO>{
	
	private Date fechaDesde;
	private Date fechaHasta;
	private BigDecimal comision;
	private ComisionesTransProyeccionDTO proyeccion;
	private ComisionesTransRealDTO real;
	
	public ComisionesTransaccionesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComisionesTransaccionesDTO(Date fechaDesde, Date fechaHasta, BigDecimal comision,
			ComisionesTransProyeccionDTO proyeccion, ComisionesTransRealDTO real) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.comision = comision;
		this.proyeccion = proyeccion;
		this.real = real;
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

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public ComisionesTransProyeccionDTO getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(ComisionesTransProyeccionDTO proyeccion) {
		this.proyeccion = proyeccion;
	}

	public ComisionesTransRealDTO getReal() {
		return real;
	}

	public void setReal(ComisionesTransRealDTO real) {
		this.real = real;
	}

	@Override
	public String toString() {
		return "ComisionesTransaccionesDTO [fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", comision="
				+ comision + ", proyeccion=" + proyeccion + ", real=" + real + "]";
	}

	@Override
	public int compareTo(ComisionesTransaccionesDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
