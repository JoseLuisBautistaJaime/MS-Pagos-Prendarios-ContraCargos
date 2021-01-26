/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @name BonificacionDTO
 * @description Clase que encapsula la informacion de un objeto bonificacion
 *
 * @author Jorge Galvez Quintero
 * @creationDate 03/04/2019 12:06 hrs.
 * @version 0.1
 */
public class BonificacionDTO implements Serializable {

	private static final long serialVersionUID = -7611838730669432090L;

	private Long id;
	private String asignacion;
	private String numDoc;
	private Date fechaDoc;
	private String tienda;
	private String plaza;
	private BigDecimal importeML;
	private String folioBonificacion;
	private EstatusBonificacionDTO estatus;
	private Long folio;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	private Long sucursal;


	public BonificacionDTO() {		
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Date getFechaDoc() {
		return fechaDoc;
	}

	public void setFechaDoc(Date fechaDoc) {
		this.fechaDoc = fechaDoc;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	public BigDecimal getImporteML() {
		return importeML;
	}

	public void setImporteML(BigDecimal importeML) {
		this.importeML = importeML;
	}

	public EstatusBonificacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusBonificacionDTO estatus) {
		this.estatus = estatus;
	}

	public String getFolioBonificacion() {
		return folioBonificacion;
	}

	public void setFolioBonificacion(String folioBonificacion) {
		this.folioBonificacion = folioBonificacion;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Long getFolio() {
		return folio;
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

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "BonificacionDTO [id=" + id + ", asignacion=" + asignacion + ", numDoc=" + numDoc + ", fechaDoc="
				+ fechaDoc + ", tienda=" + tienda + ", plaza=" + plaza + ", importeML=" + importeML + ", sucursal=" + sucursal
				+ ", folioBonificacion=" + folioBonificacion + ", estatus=" + estatus + ", folio=" + folio
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
