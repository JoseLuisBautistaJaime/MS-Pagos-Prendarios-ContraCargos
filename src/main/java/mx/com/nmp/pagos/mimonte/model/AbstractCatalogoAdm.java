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

	@Column(name = "estatus")
	protected Boolean estatus;

	@Column(name = "created_date")
	protected Date createdDate;

	@Column(name = "last_modified_date")
	protected Date lastModifiedDate;

	@Column(name = "created_by")
	protected String createdBy;

	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

	public AbstractCatalogoAdm() {
		super();
	}

	public AbstractCatalogoAdm(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
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
		return "AbstractCatalogoAdm [id=" + id + ", status=" + estatus + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy="
				+ lastModifiedBy + "]";
	}

}
