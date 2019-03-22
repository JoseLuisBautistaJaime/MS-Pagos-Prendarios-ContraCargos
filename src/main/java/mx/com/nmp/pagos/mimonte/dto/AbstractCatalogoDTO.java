package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * @name AbstractCatalogoDTO
 * @descriptipon Clase abstracta que que encapsula la informacion basica de una
 *               catalogo administrable
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 19:51 hrs.
 * @version 0.1
 */
public abstract class AbstractCatalogoDTO implements java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 6853393208200640526L;

	protected Long id;
	protected Boolean estatus;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	protected String lastModifiedBy;
	protected String description;
	protected String shortDescription;

	public AbstractCatalogoDTO() {
		super();
	}

	public AbstractCatalogoDTO(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.description = description;
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "AbstractCatalogoDTO [id=" + id + ", estatus=" + estatus + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy="
				+ lastModifiedBy + ", description=" + description + ", shortDescription=" + shortDescription + "]";
	}

}
