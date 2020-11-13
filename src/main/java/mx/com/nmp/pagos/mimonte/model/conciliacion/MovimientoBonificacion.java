/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_estatus_bonificacion")
	private EstatusBonificacion estatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "id_conciliacion")
	private Long idConciliacion;


	public MovimientoBonificacion() {
		super();
	}

	public MovimientoBonificacion(Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy) {
		super(createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	

	public MovimientoBonificacion(Long id, EstatusBonificacion estatus, Date fecha, BigDecimal monto) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.fecha = fecha;
		this.monto = monto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatusBonificacion getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusBonificacion estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, estatus, fecha, monto, idConciliacion, super.createdBy, super.createdDate, super.lastModifiedBy, super.lastModifiedDate);
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
		return "MovimientoBonificacion [id=" + id + ", estatus=" + estatus + ", fecha=" + fecha + ", monto=" + monto
				+ ", idConciliacion=" + idConciliacion + "]";
	}

}