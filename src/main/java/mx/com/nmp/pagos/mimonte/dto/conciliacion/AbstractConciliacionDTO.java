/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;
import java.util.Date;

/**
 * @name AbstractConciliacionDTO
 * @descriptipon Clase abstracta que que encapsula la informacion basica de una conciliacion.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:38 hrs.
 * @version 0.1
 */
public abstract class AbstractConciliacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4507316878558234835L;
	
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	protected String lastModifiedBy;
	
	public AbstractConciliacionDTO() {
		super();
	}

	public AbstractConciliacionDTO(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super();
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "AbstractConciliacionDTO [createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
