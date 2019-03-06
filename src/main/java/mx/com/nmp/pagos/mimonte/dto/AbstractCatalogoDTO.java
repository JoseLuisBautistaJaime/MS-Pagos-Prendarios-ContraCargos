package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: AbstractCatalogoDTO Descripcion: Clase abstracta que que encapsula la
 * informacion basica de una catalogo administrable y sirve para
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 19:51 hrs.
 * @version 0.1
 */
public abstract class AbstractCatalogoDTO {

	protected Long id;
	protected Boolean status;
	protected Date creationDate;
	protected Date modificationDate;
	protected String createdBy;
	protected String modifiedBy;

	public AbstractCatalogoDTO() {
		super();
	}

	public AbstractCatalogoDTO(Long id, Boolean status, Date creationDate, Date modificationDate, String createdBy,
			String modifiedBy) {
		super();
		this.id = id;
		this.status = status;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
