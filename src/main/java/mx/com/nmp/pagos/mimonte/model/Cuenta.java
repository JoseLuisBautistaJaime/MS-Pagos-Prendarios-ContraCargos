/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
public class Cuenta extends AbstractCatalogoAdm implements Comparable<Cuenta>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 2945422432743246862L;

	@Column(name = "numero_cuenta", nullable = false)
	private String numeroCuenta;

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "tr_cuenta_afiliacion", joinColumns = {
			@JoinColumn(name = "id_cuenta", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_afiliacion", referencedColumnName = "id") })
	private Set<Afiliacion> afiliaciones;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cuentas")
	private Set<Entidad> entidades;

	public Cuenta() {
		super();
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

	public Set<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(Set<Entidad> entidades) {
		this.entidades = entidades;
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

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", afiliaciones=" + afiliaciones + ", entidades=" + entidades
				+ "]";
	}

	@Override
	public int compareTo(Cuenta arg0) {
		return arg0.id.compareTo(this.id);
	}

}
