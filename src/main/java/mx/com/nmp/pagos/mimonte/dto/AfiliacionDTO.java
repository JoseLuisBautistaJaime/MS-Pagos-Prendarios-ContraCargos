package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: AfiliacionDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a un tipo de Afiliacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:39 hrs.
 * @version 0.1
 */
public class AfiliacionDTO {

	private Integer id;
	private String numero;
	private TipoAutorizacionDTO tipo;
	private Boolean estatus;
	private Date created_date;
	private Date last_modified_date; 
	private String created_by; 
	private String last_modified_by;

	public AfiliacionDTO() {
		super();	
	}

	
	public AfiliacionDTO(Integer id, String numero, TipoAutorizacionDTO tipo, Boolean estatus, Date created_date,
			Date last_modified_date, String created_by, String last_modified_by) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
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

	public TipoAutorizacionDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoAutorizacionDTO tipo) {
		this.tipo = tipo;
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
		return "AfiliacionDTO [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", estatus=" + estatus
				+ ", created_date=" + created_date + ", last_modified_date=" + last_modified_date + ", created_by="
				+ created_by + ", last_modified_by=" + last_modified_by + "]";
	}
	
}

	