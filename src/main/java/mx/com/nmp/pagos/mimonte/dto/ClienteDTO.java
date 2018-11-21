package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class ClienteDTO {

	private Integer id;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private Date fechaAlta;
	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Integer id, String nombre, String apPaterno, String apMaterno, Date fechaAlta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.fechaAlta = fechaAlta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno
				+ ", fechaAlta=" + fechaAlta + "]";
	}
	
}
