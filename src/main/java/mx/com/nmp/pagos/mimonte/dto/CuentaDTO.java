package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.List;

public class CuentaDTO {
	
	private Integer id;
	private String numero;
	private List<AfiliacionDTO> afiliaciones;
	private List<CodigoEstadoCuentaDTO> codigos;
	private Boolean estatus;
	private Date created_date;
	private Date last_modified_date; 
	private String created_by; 
	private String last_modified_by;
	
	
	public CuentaDTO() {
		super();
	
	}
	public CuentaDTO(Integer id, String numero, List<AfiliacionDTO> afiliaciones, List<CodigoEstadoCuentaDTO> codigos,
			Boolean estatus, Date created_date, Date last_modified_date, String created_by, String last_modified_by) {
		super();
		this.id = id;
		this.numero = numero;
		this.afiliaciones = afiliaciones;
		this.codigos = codigos;
		this.estatus = estatus;
		this.created_date = created_date;
		this.last_modified_date = last_modified_date;
		this.created_by = created_by;
		this.last_modified_by = last_modified_by;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<AfiliacionDTO> getAfiliaciones() {
		return afiliaciones;
	}
	public void setAfiliaciones(List<AfiliacionDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}
	public List<CodigoEstadoCuentaDTO> getCodigos() {
		return codigos;
	}
	public void setCodigos(List<CodigoEstadoCuentaDTO> codigos) {
		this.codigos = codigos;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLast_modified_by() {
		return last_modified_by;
	}
	public void setLast_modified_by(String last_modified_by) {
		this.last_modified_by = last_modified_by;
	}
	@Override
	public String toString() {
		return "CuentaDTO [id=" + id + ", numero=" + numero + ", afiliaciones=" + afiliaciones + ", codigos=" + codigos
				+ ", estatus=" + estatus + ", created_date=" + created_date + ", last_modified_date="
				+ last_modified_date + ", created_by=" + created_by + ", last_modified_by=" + last_modified_by + "]";
	}
	
	
	
	

}
