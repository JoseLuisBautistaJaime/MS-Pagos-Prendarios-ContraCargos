package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

public class LayoutCabeceraDTO2 {

	private Integer id;
	private String	cabecera;
	private	String unidadNegocio;
	private	String descripcion;
	private	String codigoOrigen;
	private	Date fecha;
	
	public LayoutCabeceraDTO2() {
		super();
	}
	
	public LayoutCabeceraDTO2(Integer id, String cabecera, String unidadNegocio, String descripcion,
			String codigoOrigen, Date fecha) {
		super();
		this.id = id;
		this.cabecera = cabecera;
		this.unidadNegocio = unidadNegocio;
		this.descripcion = descripcion;
		this.codigoOrigen = codigoOrigen;
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
