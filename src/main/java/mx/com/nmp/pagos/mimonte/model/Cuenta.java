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

@Entity
@Table(name = "tc_cuenta")
public class Cuenta extends AbstractCatalogoAdm implements Comparable<Cuenta>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 2945422432743246862L;

	@Column(name = "numero_cuenta", nullable = false)
	private Long numeroCuenta;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_cuenta_afiliacion", joinColumns = { @JoinColumn(name = "id_cuenta") }, inverseJoinColumns = {
			@JoinColumn(name = "id_afiliacion") })
	private Set<Afiliacion> afiliaciones;

	public Cuenta() {
		super();
	}

	public Cuenta(Long numeroCuenta, Set<Afiliacion> afiliaciones) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public Cuenta(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, Long numeroCuenta,
			Set<Afiliacion> afiliaciones) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
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
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", afiliaciones=" + afiliaciones + "]";
	}

	@Override
	public int compareTo(Cuenta arg0) {
		return arg0.getId().compareTo(this.getId());
	}

}
