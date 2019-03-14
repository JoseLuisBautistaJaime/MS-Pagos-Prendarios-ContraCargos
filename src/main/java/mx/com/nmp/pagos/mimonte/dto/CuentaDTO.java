package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.List;

public class CuentaDTO {

	private Long id;
	private Long numero;
	private List<AfiliacionDTO> afiliaciones;
	private List<CodigoEstadoCuentaDTO> codigos;
	private Boolean estatus;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;

	public CuentaDTO() {
		super();

	}

	public CuentaDTO(Long id, Long numero, List<AfiliacionDTO> afiliaciones, List<CodigoEstadoCuentaDTO> codigos,
			Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super();
		this.id = id;
		this.numero = numero;
		this.afiliaciones = afiliaciones;
		this.codigos = codigos;
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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
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
		return "CuentaDTO [id=" + id + ", numero=" + numero + ", afiliaciones=" + afiliaciones + ", codigos=" + codigos
				+ ", estatus=" + estatus + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
