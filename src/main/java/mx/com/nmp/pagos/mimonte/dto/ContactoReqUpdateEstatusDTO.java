package mx.com.nmp.pagos.mimonte.dto;
/**
 * Nombre: ContactoReqUpdateEstatusDTO Descripcion: Clase que encapsula la informacion perteneciente a los tipo de contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 06/03/2019 22:57 hrs.
 * @version 0.1
 */
public class ContactoReqUpdateEstatusDTO {
	
	private Integer id;
	
	private Boolean estatus;
	
	private String lastModifiedBy;

	public ContactoReqUpdateEstatusDTO() {
		super();
	}

	public ContactoReqUpdateEstatusDTO(Integer id, Boolean estatus, String lastModifiedBy) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "ContactoReqUpdateEstatusDTO [id=" + id + ", estatus=" + estatus + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
