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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: Afiliacion Descripcion: Entidad que representa un tipo de afiliacion
 * dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:59 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_afiliacion")
public class Afiliacion extends AbstractCatalogoAdm implements Comparable<Afiliacion> {

	@Column(name = "numero")
	private Long numero;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo")
	private TipoAutorizacion tipo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "afiliacion")
	private Set<ReglaNegocio> reglas;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliaciones")
	private Set<Cuenta> cuentas;

	public Afiliacion() {
		/**
		 * empty constructor
		 */
	}

	public Afiliacion(Set<ReglaNegocio> reglas, TipoAutorizacion tipo) {
		super();
		this.reglas = reglas;
		this.tipo = tipo;
	}

	public Afiliacion(TipoAutorizacion tipo, Set<ReglaNegocio> reglas, Set<Cuenta> cuentas) {
		super();
		this.tipo = tipo;
		this.reglas = reglas;
		this.cuentas = cuentas;
	}

	public Afiliacion(Long numero, TipoAutorizacion tipo, Set<ReglaNegocio> reglas, Set<Cuenta> cuentas) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.reglas = reglas;
		this.cuentas = cuentas;
	}

	public Afiliacion(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, Long numero, TipoAutorizacion tipo,
			Set<ReglaNegocio> reglas, Set<Cuenta> cuentas) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numero = numero;
		this.tipo = tipo;
		this.reglas = reglas;
		this.cuentas = cuentas;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Set<ReglaNegocio> getRegla() {
		return reglas;
	}

	public void setRegla(Set<ReglaNegocio> reglas) {
		this.reglas = reglas;
	}

	public TipoAutorizacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoAutorizacion tipo) {
		this.tipo = tipo;
	}

	public Set<ReglaNegocio> getReglas() {
		return reglas;
	}

	public void setReglas(Set<ReglaNegocio> reglas) {
		this.reglas = reglas;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public String toString() {
		return "Afiliacion [numero=" + numero + ", tipo=" + tipo + ", reglas=" + reglas + ", cuentas=" + cuentas + "]";
	}

	@Override
	public int compareTo(Afiliacion o) {
		return o.numero.compareTo(this.numero);
	}

}
