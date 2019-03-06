package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Nombre: AbstractCatalogoAdm Descripcion: Entity abstracta que que encapsula
 * la informacion basica de una catalogo administrable y sirve para
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 19:51 hrs.
 * @version 0.1
 */
@MappedSuperclass
public abstract class AbstractCatalogoAdm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "status")
	protected Boolean status;

	@Column(name = "creation_date")
	protected Date creationDate;

	@Column(name = "modification_date")
	protected Date modificationDate;

	@Column(name = "created_by")
	protected String createdBy;

	@Column(name = "modified_by")
	protected String modifiedBy;

	public AbstractCatalogoAdm() {
		super();
	}

	public AbstractCatalogoAdm(Long id, Boolean status, Date creationDate, Date modificationDate, String createdBy,
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

	@Override
	public String toString() {
		return "AbstractCatalogoAdm [id=" + id + ", status=" + status + ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}

}
