/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @name Afiliacion
 * @description Entidad que representa un tipo de afiliacion dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:59 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_afiliacion")
public class Afiliacion extends AbstractCatalogoAdm implements Comparable<Afiliacion>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "numero")
	private String numero;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "afiliaciones")
	private Set<Cuenta> cuentas;

	@OneToMany(mappedBy = "afiliacion", fetch = FetchType.LAZY)
	private Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet;

	public Afiliacion() {
		super();
	}

	public Afiliacion(Long id) {
		super();
		this.id = id;
	}

	public Afiliacion(Set<Cuenta> cuentas) {
		super();
		this.cuentas = cuentas;
	}

	public Afiliacion(String numero, Set<Cuenta> cuentas) {
		super();
		this.numero = numero;
		this.cuentas = cuentas;
	}

	public Afiliacion(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, String numero, Set<Cuenta> cuentas) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numero = numero;
		this.cuentas = cuentas;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<EntidadCuentaAfiliacion> getEntidadCuentaAfiliacionSet() {
		return entidadCuentaAfiliacionSet;
	}

	public void setEntidadCuentaAfiliacionSet(Set<EntidadCuentaAfiliacion> entidadCuentaAfiliacionSet) {
		this.entidadCuentaAfiliacionSet = entidadCuentaAfiliacionSet;
	}

	@Override
	public String toString() {
		return "Afiliacion [numero=" + numero + ", cuentas=" + (cuentas != null ? cuentas.size() : 0) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Afiliacion))
			return false;

		final Afiliacion other = (Afiliacion) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(Afiliacion o) {
		return o.id.compareTo(this.id);
	}

}
