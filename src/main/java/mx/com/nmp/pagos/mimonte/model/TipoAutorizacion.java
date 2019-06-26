/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @name TipoAutorizacion
 * @description Entidad que representa un tipo de autorizacion dentro del
 *              sistema en el catalogo de afiliaciones.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 01/02/2019 11:57 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_autorizacion")
public class TipoAutorizacion extends AbstractCatalogo implements Comparable<TipoAutorizacion> {

	public TipoAutorizacion() {
		super();
	}

	public TipoAutorizacion(Set<ReglaNegocio> reglas) {
		super();
		this.reglas = reglas;
	}

	@ManyToMany
	@JoinTable(name = "tr_regla_negocio_tipo_autorizacion", joinColumns = {
			@JoinColumn(name = "id_tipo_autorizacion", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_regla_negocio", nullable = false) })
	private Set<ReglaNegocio> reglas;

	public Set<ReglaNegocio> getReglas() {
		return reglas;
	}

	public void setReglas(Set<ReglaNegocio> reglas) {
		this.reglas = reglas;
	}

	@Override
	public int compareTo(TipoAutorizacion o) {
		return o.id.compareTo(this.id);
	}

}
