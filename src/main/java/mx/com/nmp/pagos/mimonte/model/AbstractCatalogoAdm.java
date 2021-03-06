/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @name AbstractCatalogoAdm
 * @description Entity abstracta que que encapsula la informacion basica de un
 *              catalogo administrable
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 19:51 hrs.
 * @version 0.1
 */
@MappedSuperclass
public abstract class AbstractCatalogoAdm extends Updatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "estatus")
	protected Boolean estatus;

	@Column(name = "description")
	protected String description;

	@Column(name = "short_description")
	protected String shortDescription;

	public AbstractCatalogoAdm() {
		super();
	}

	public AbstractCatalogoAdm(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super();
		this.id = id;
		this.estatus = estatus;
		super.createdDate = createdDate;
		super.lastModifiedDate = lastModifiedDate;
		super.createdBy = createdBy;
		super.lastModifiedBy = lastModifiedBy;
		this.description = description;
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

	@Override
	public String toString() {
		return "AbstractCatalogoAdm [id=" + id + ", estatus=" + estatus + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy="
				+ lastModifiedBy + ", description=" + description + ", shortDescription=" + shortDescription + "]";
	}

}
