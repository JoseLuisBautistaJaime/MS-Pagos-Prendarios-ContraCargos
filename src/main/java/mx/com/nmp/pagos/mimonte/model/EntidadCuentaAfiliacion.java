/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @name Entidad
 * @description Clase que encapsula la informacion de una entidad de catalogo de
 *              tipo EntidadCuentaAfiliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:28 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tr_entidad_cuenta_afiliacion")
@IdClass(ECACompositeKey.class)
@NamedQueries(value = @NamedQuery(name = "EntidadCuentaAfiliacion.dropEntidadCuentaAfiliacioneRelationship", query = "DELETE FROM EntidadCuentaAfiliacion eca WHERE eca.entidad.id = :idEntidad AND eca.cuenta.id = :idCuenta AND eca.afiliacion.id = :idAfiliacion"))
public class EntidadCuentaAfiliacion implements Comparable<EntidadCuentaAfiliacion>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_afiliacion")
	private Afiliacion afiliacion;

	public EntidadCuentaAfiliacion() {
		super();
	}

	public EntidadCuentaAfiliacion(Entidad entidad, Cuenta cuenta, Afiliacion afiliacion) {
		super();
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.afiliacion = afiliacion;
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

	public Afiliacion getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}

	@Override
	public String toString() {
		return "EntidadCuentaAfiliacion [entidad=" + entidad + ", cuenta=" + cuenta + ", afiliacion=" + afiliacion
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(entidad, cuenta, afiliacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EntidadCuentaAfiliacion))
			return false;

		final EntidadCuentaAfiliacion other = (EntidadCuentaAfiliacion) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(EntidadCuentaAfiliacion arg0) {
		return arg0.getEntidad().getId().compareTo(this.getEntidad().getId());
	}

}
