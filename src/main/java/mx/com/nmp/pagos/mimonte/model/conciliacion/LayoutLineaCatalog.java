package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import javax.persistence.*;

import mx.com.nmp.pagos.mimonte.model.Updatable;

import java.util.Date;


/**
 * The persistent class for the tc_layout_linea database table.
 * 
 */
@Entity
@Table(name="tc_layout_linea")
public class LayoutLineaCatalog extends Updatable implements Serializable {
	private static final long serialVersionUID = -5776981826554339715L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="cuenta")
	private String cuenta;

	@Column(name="dep_id")
	private String depId;

	@Column(name="id_layout")
	private Long idLayout;

	@Column(name="linea")
	private String linea;

	@Column(name="monto")
	private Double monto;

	@Column(name="negocio")
	private String negocio;

	@Column(name="proyecto_nmp")
	private String proyectoNmp;

	@Column(name="unidad_operativa")
	private String unidadOperativa;

	public LayoutLineaCatalog() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public Long getIdLayout() {
		return this.idLayout;
	}

	public void setIdLayout(Long idLayout) {
		this.idLayout = idLayout;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getNegocio() {
		return this.negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public String getProyectoNmp() {
		return this.proyectoNmp;
	}

	public void setProyectoNmp(String proyectoNmp) {
		this.proyectoNmp = proyectoNmp;
	}

	public String getUnidadOperativa() {
		return this.unidadOperativa;
	}

	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LayoutLineaCatalog other = (LayoutLineaCatalog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}