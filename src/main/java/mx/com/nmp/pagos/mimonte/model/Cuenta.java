/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;

/**
 * @name Cuenta
 * @description Clase que encapsula la informacion de una Cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 20:22 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_cuenta")
@NamedQueries(value = {
		@NamedQuery(name = "Cuenta.qGetByEntidadId", query = "SELECT cta FROM Cuenta cta WHERE cta.id IN(SELECT eca.cuenta.id FROM EntidadCuentaAfiliacion eca WHERE eca.entidad.id = :idEntidad)") })
public class Cuenta extends AbstractCatalogoAdm implements Comparable<Cuenta>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 2945422432743246862L;

	@Column(name = "numero_cuenta", nullable = false)
	private String numeroCuenta;

	@ManyToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@JoinTable(name = "tr_cuenta_afiliacion", joinColumns = {
			@JoinColumn(name = "id_cuenta", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_afiliacion", referencedColumnName = "id") })
	private Set<Afiliacion> afiliaciones;

	@OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
	private Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet;

	@OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
	private Set<Conciliacion> conciliacionSet;

	public Cuenta() {
		super();
	}

	public Cuenta(Long id) {
		super();
		this.id = id;
	}

	public Cuenta(Long id, String numeroCuenta) {
		super();
		this.id = id;
		this.numeroCuenta = numeroCuenta;
	}

	public Cuenta(String numeroCuenta, Set<Afiliacion> afiliaciones) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public Cuenta(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, String numeroCuenta,
			Set<Afiliacion> afiliaciones) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<Afiliacion> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(Set<Afiliacion> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	public Set<EntidadCuentaAfiliacion> getEntidadCuentaAfiliacionSet() {
		return entidadCuentaAfiliacionSet;
	}

	public void setEntidadCuentaAfiliacionSet(Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet) {
		this.entidadCuentaAfiliacionSet = entidadCuentaAfiliacionSet;
	}

	public Set<Conciliacion> getConciliacionSet() {
		return conciliacionSet;
	}

	public void setConciliacionSet(Set<Conciliacion> conciliacionSet) {
		this.conciliacionSet = conciliacionSet;
	}

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", afiliaciones=" + (afiliaciones != null ? afiliaciones.size() : 0)
				+ ", entidadCuentaAfiliacionSet=" + (entidadCuentaAfiliacionSet != null ? entidadCuentaAfiliacionSet.size() : 0)
				+ ", conciliacionSet=" + (conciliacionSet != null ? conciliacionSet.size() : 0)
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroCuenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Cuenta))
			return false;

		final Cuenta other = (Cuenta) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(Cuenta arg0) {
		return arg0.id.compareTo(this.id);
	}

}
