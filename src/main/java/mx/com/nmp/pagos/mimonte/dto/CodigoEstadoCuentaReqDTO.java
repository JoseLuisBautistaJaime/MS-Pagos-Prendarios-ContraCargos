package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: CodigoEstadoCuentaReqDTO Descripcion: Clase que encapsula la
 * unformacion de un catalogo de codigo de estado de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaReqDTO implements Comparable<CodigoEstadoCuentaReqDTO> {

	private String leyenda;
	private Long id;
	private Boolean status;
	private EntidadReqDTO entidad;
	private CategoriaReqDTO categoria;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	private Boolean estatus;
	private String description;
	private String shortDescription;

	public CodigoEstadoCuentaReqDTO() {
		super();
	}

	public CodigoEstadoCuentaReqDTO(String leyenda, Long id, Boolean status, EntidadReqDTO entidad,
			CategoriaReqDTO categoria, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy,
			Boolean estatus, String description, String shortDescription) {
		super();
		this.leyenda = leyenda;
		this.id = id;
		this.status = status;
		this.entidad = entidad;
		this.categoria = categoria;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.estatus = estatus;
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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
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

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
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

	public EntidadReqDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadReqDTO entidad) {
		this.entidad = entidad;
	}

	public CategoriaReqDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaReqDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaReqDTO [leyenda=" + leyenda + ", id=" + id + ", status=" + status + ", entidad="
				+ entidad + ", categoria=" + categoria + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + ", estatus="
				+ estatus + ", description=" + description + ", shortDescription=" + shortDescription + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
