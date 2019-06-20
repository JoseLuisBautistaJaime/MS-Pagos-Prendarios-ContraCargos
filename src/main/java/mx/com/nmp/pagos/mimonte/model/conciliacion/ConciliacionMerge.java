/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @name ConciliacionMerge
 * @description Informacion general del estado del merge
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:54 PM
 */
@Entity
@Table(name = "to_merge_conciliacion")
public class ConciliacionMerge implements Serializable {

	private static final long serialVersionUID = -963389129480587074L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "midas_last_updated", nullable = false)
	private Date midasLastUpdated;

	@Temporal(TemporalType.DATE)
	@Column(name = "proveedor_last_updated", nullable = false)
	private Date proveedorLastUpdated;

	@Temporal(TemporalType.DATE)
	@Column(name = "estado_cuenta_last_updated", nullable = false)
	private Date estadoCuentaLastUpdated;


	public ConciliacionMerge() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMidasLastUpdated() {
		return midasLastUpdated;
	}

	public void setMidasLastUpdated(Date midasLastUpdated) {
		this.midasLastUpdated = midasLastUpdated;
	}

	public Date getProveedorLastUpdated() {
		return proveedorLastUpdated;
	}

	public void setProveedorLastUpdated(Date proveedorLastUpdated) {
		this.proveedorLastUpdated = proveedorLastUpdated;
	}

	public Date getEstadoCuentaLastUpdated() {
		return estadoCuentaLastUpdated;
	}

	public void setEstadoCuentaLastUpdated(Date estadoCuentaLastUpdated) {
		this.estadoCuentaLastUpdated = estadoCuentaLastUpdated;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, midasLastUpdated, proveedorLastUpdated, estadoCuentaLastUpdated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ConciliacionMerge))
			return false;

		final ConciliacionMerge other = (ConciliacionMerge) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "ConciliacionMerge [id=" + id + ", midasLastUpdated=" + midasLastUpdated
				+ ", proveedorLastUpdated=" + proveedorLastUpdated + ", estadoCuentaLastUpdated=" + estadoCuentaLastUpdated + "]";
	}


}