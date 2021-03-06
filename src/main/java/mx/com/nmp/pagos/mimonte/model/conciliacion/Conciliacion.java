/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * Registro de conciliacion.
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:43 PM
 */
@Entity
@Table(name = "to_conciliacion")
public class Conciliacion extends Updatable implements Serializable  {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -3886314835601436753L;

	@TableGenerator(name = "gen_folio_conciliacion", table = "seq_conciliacion", pkColumnName = "seq_name", valueColumnName = "seq_value", allocationSize = 1, pkColumnValue = "folio_conciliacion")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_folio_conciliacion")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estatus_conciliacion")
	private EstatusConciliacion estatus;

	@Column(name = "folio")
	private Long folio;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
	@Column(name = "sub_estatus_descripcion")
	private String subEstatusDescripcion;

	@Column(name = "id_poliza_tesoreria")
	private String idPolizaTesoreria;
	
	@Column(name = "id_asiento_contable")
	private String idAsientoContable;
	
	@Column(name = "completed_date")
	private Date completedDate;

	@OneToOne(mappedBy = "conciliacion", cascade = CascadeType.ALL)
    private Global global;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_sub_estatus_conciliacion")
	private SubEstatusConciliacion subEstatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_merge")
	private ConciliacionMerge merge;
	
	@ManyToOne
	@JoinColumn(name = "proveedor")
	private Proveedor proveedor;
	
	public Conciliacion() {
		super();
	}

	public Conciliacion(Long id, Long folio, EstatusConciliacion estatus, Entidad entidad, Cuenta cuenta,
			String subEstatusDescripcion, String idPolizaTesoreria, String idAsientoContable, Date completedDate,
			Global global, SubEstatusConciliacion subEstatus) {
		super();
		this.id = id;
		this.folio = folio;
		this.estatus = estatus;
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.subEstatusDescripcion = subEstatusDescripcion;
		this.idPolizaTesoreria = idPolizaTesoreria;
		this.idAsientoContable = idAsientoContable;
		this.completedDate = completedDate;
		this.global = global;
		this.subEstatus = subEstatus;
	}

	public Conciliacion(Long folio) {
		this.folio = folio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusConciliacion getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusConciliacion estatus) {
		this.estatus = estatus;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getSubEstatusDescripcion() {
		return subEstatusDescripcion;
	}

	public void setSubEstatusDescripcion(String subEstatusDescripcion) {
		this.subEstatusDescripcion = subEstatusDescripcion;
	}

	public String getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(String idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public String getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(String idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Global getGlobal() {
		return global;
	}

	public void setGlobal(Global global) {
		this.global = global;
	}

	public SubEstatusConciliacion getSubEstatus() {
		return subEstatus;
	}

	public void setSubEstatus(SubEstatusConciliacion subEstatus) {
		this.subEstatus = subEstatus;
	}

	public ConciliacionMerge getMerge() {
		return merge;
	}

	public void setMerge(ConciliacionMerge merge) {
		this.merge = merge;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((folio == null) ? 0 : folio.hashCode());
		result = prime * result + ((completedDate == null) ? 0 : completedDate.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idAsientoContable == null) ? 0 : idAsientoContable.hashCode());
		result = prime * result + ((idPolizaTesoreria == null) ? 0 : idPolizaTesoreria.hashCode());
		result = prime * result + ((subEstatus == null) ? 0 : subEstatus.hashCode());
		result = prime * result + ((subEstatusDescripcion == null) ? 0 : subEstatusDescripcion.hashCode());
		result = prime * result + ((merge == null) ? 0 : merge.hashCode());
		return result;
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
		return "Conciliacion [id=" + id + ", folio=" + folio + ", estatus=" + estatus + ", entidad=" + entidad + ", cuenta=" + cuenta
				+ ", subEstatusDescripcion=" + subEstatusDescripcion + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + ", completedDate=" + completedDate + ", global=" + global
				+ ", subEstatus=" + subEstatus + ", merge=" + merge + ", proveedor=" + proveedor + "]";
	}

}