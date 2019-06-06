/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estatus_conciliacion")
	private EstatusConciliacion estatus;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
	@Column(name = "sub_estatus_descripcion")
	private String subEstatusDescripcion;

	@Column(name = "id_poliza_tesoreria")
	private Long idPolizaTesoreria;
	
	@Column(name = "id_asiento_contable")
	private Long idAsientoContable;
	
	@Column(name = "completed_date")
	private Date completedDate;
	
	@OneToMany(mappedBy = "conciliacion")
    private Set<Global> global;

	@OneToMany(mappedBy = "conciliacion")
	private Set<Reporte> reportes;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "id_conciliacion")
	private Set<MovimientoConciliacion> movimientoConciliacionSet;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_sub_estatus_conciliacion")
	private SubEstatusConciliacion subEstatus;
	
	@OneToMany(mappedBy = "conciliacion")
	private Set<ComisionTransaccion> comisionTransaccionSet;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_conciliacion")
	private ConciliacionMerge merge;
	
	public Conciliacion() {
		super();
	}

	public Conciliacion(Integer id, EstatusConciliacion estatus, Entidad entidad, Cuenta cuenta,
			String subEstatusDescripcion, Long idPolizaTesoreria, Long idAsientoContable, Date completedDate,
			Set<Global> global, Set<Reporte> reportes, Set<MovimientoConciliacion> movimientoConciliacionSet,
			SubEstatusConciliacion subEstatus) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.subEstatusDescripcion = subEstatusDescripcion;
		this.idPolizaTesoreria = idPolizaTesoreria;
		this.idAsientoContable = idAsientoContable;
		this.completedDate = completedDate;
		this.global = global;
		this.reportes = reportes;
		this.movimientoConciliacionSet = movimientoConciliacionSet;
		this.subEstatus = subEstatus;
	}

	public Conciliacion(long folio) {
		this.id = new Long(folio).intValue();
	}
	
	public Conciliacion(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public long getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(Long idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public Long getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(Long idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Set<Global> getGlobal() {
		return global;
	}

	public void setGlobal(Set<Global> global) {
		this.global = global;
	}

	public Set<Reporte> getReportes() {
		return reportes;
	}

	public void setReportes(Set<Reporte> reportes) {
		this.reportes = reportes;
	}

	public Set<MovimientoConciliacion> getMovimientoConciliacionSet() {
		return movimientoConciliacionSet;
	}

	public void setMovimientoConciliacionSet(Set<MovimientoConciliacion> movimientoConciliacionSet) {
		this.movimientoConciliacionSet = movimientoConciliacionSet;
	}

	public SubEstatusConciliacion getSubEstatus() {
		return subEstatus;
	}

	public void setSubEstatus(SubEstatusConciliacion subEstatus) {
		this.subEstatus = subEstatus;
	}
	
	public Set<ComisionTransaccion> getComisionTransaccionSet() {
		return comisionTransaccionSet;
	}

	public void setComisionTransaccionSet(Set<ComisionTransaccion> comisionTransaccionSet) {
		this.comisionTransaccionSet = comisionTransaccionSet;
	}

	public ConciliacionMerge getMerge() {
		return merge;
	}

	public void setMerge(ConciliacionMerge merge) {
		this.merge = merge;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((completedDate == null) ? 0 : completedDate.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idAsientoContable == null) ? 0 : idAsientoContable.hashCode());
		result = prime * result + ((idPolizaTesoreria == null) ? 0 : idPolizaTesoreria.hashCode());
		result = prime * result + ((movimientoConciliacionSet == null) ? 0 : movimientoConciliacionSet.hashCode());
		result = prime * result + ((reportes == null) ? 0 : reportes.hashCode());
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
		return "Conciliacion [id=" + id + ", estatus=" + estatus + ", entidad=" + entidad + ", cuenta=" + cuenta
				+ ", subEstatusDescripcion=" + subEstatusDescripcion + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + ", completedDate=" + completedDate + ", global=" + global
				+ ", reportes=" + reportes + ", movimientoConciliacionSet=" + movimientoConciliacionSet
				+ ", subEstatus=" + subEstatus + "]";
	}

}