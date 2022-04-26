package mx.com.nmp.pagos.mimonte.dto;

import java.io.Serializable;

public class BayonetReglasDTO implements Serializable{

	public BayonetReglasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private Long id;
	private String status;
	private Integer reglaAutorizacion;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer  getReglaAutorizacion() {
		return reglaAutorizacion;
	}
	public void setReglaAutorizacion(Integer reglaAutorizacion) {
		this.reglaAutorizacion = reglaAutorizacion;
	}

	public BayonetReglasDTO(Long id, String status, Integer reglaAutorizacion) {
		super();
		this.id = id;
		this.status = status;
		this.reglaAutorizacion = reglaAutorizacion;
	}
}
