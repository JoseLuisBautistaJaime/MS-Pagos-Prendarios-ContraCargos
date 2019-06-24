package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import javax.persistence.*;

import mx.com.nmp.pagos.mimonte.model.Updatable;

import java.util.Date;


/**
 * The persistent class for the tc_layout_header database table.
 * 
 */
@Entity
@Table(name="tc_layout_header")
public class LayoutHeaderCatalog extends Updatable implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="cabecera")
	private String cabecera;

	@Column(name="campo1")
	private String campo1;

	@Column(name="campo2")
	private String campo2;

	@Column(name="codigo_origen")
	private String codigoOrigen;

	@Column(name="created_by")
	private String createdBy;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="id_layout")
	private Long idLayout;

	@Column(name="unidad_negocio")
	private String unidadNegocio;

	public LayoutHeaderCatalog() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabecera() {
		return this.cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getCampo1() {
		return this.campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return this.campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCodigoOrigen() {
		return this.codigoOrigen;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getUnidadNegocio() {
		return this.unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

}