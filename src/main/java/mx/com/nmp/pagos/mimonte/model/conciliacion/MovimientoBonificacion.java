/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mx.com.nmp.pagos.mimonte.model.EstatusBonificacion;
import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @name MovimientoBonificacion
 * 
 * @description encapsula informacion de un movimiento bonificacion
 * @author Quarksoft
 * @version 1.0
 * @created 11/11/2020
 */
@Entity
@Table(name = "to_movimiento_bonificacion")
public class MovimientoBonificacion extends Updatable implements Serializable {

	private static final long serialVersionUID = 8987562812338981175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "asignacion")
	private String asignacion;

	@Column(name = "num_doc")
	private String numDoc;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_doc")
	private Date fechaDoc;

	@Column(name = "tienda")
	private String tienda;

	@Column(name = "plaza")
	private String plaza;

	@Column(name = "sucursal")
	private Long sucursal;

	@Column(name = "importe_ml")
	private BigDecimal importeML;

	@Column(name = "folio_bonificacion")
	private String folioBonificacion;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_estatus_bonificacion")
	private EstatusBonificacion estatus;

	@Column(name = "id_conciliacion")
	private Long idConciliacion;

	@OneToMany(mappedBy = "movimientoBonificacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<MovimientoBonificacionReferencia> referencias;


	public MovimientoBonificacion() {
		super();
	}

	public MovimientoBonificacion(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
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

	public String getFolioBonificacion() {
		return folioBonificacion;
	}

	public void setFolioBonificacion(String folioBonificacion) {
		this.folioBonificacion = folioBonificacion;
	}

	public EstatusBonificacion getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusBonificacion estatus) {
		this.estatus = estatus;
	}

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public List<MovimientoBonificacionReferencia> getReferencias() {
		return referencias;
	}

	public void setReferencias(List<MovimientoBonificacionReferencia> referencias) {
		this.referencias = referencias;
	}

	
	@Transient
	public void addReferencia(MovimientoBonificacionReferencia referencia) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, asignacion, fechaDoc, tienda, plaza, importeML, folioBonificacion, sucursal,
				estatus, idConciliacion, super.createdBy, super.createdDate, super.lastModifiedBy, super.lastModifiedDate);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Conciliacion))
			return false;

		final Conciliacion other = (Conciliacion) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "MovimientoBonificacion [id=" + id + ", asignacion=" + asignacion + ", numDoc=" + numDoc + ", fechaDoc="
				+ fechaDoc + ", tienda=" + tienda + ", plaza=" + plaza + ", importeML=" + importeML + ", sucursal=" + sucursal +  ", folioBonificacion=" + folioBonificacion
				+ ", estatus=" + estatus + ", idConciliacion=" + idConciliacion + "]";
	}

}