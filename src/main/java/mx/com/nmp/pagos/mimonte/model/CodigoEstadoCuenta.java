/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @name CodigoEstadoCuenta
 * @description Clase que encapsula la informacion de una entidad de catalogo de
 *              tipo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 14:10 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_codigo_estado_cuenta")
@NamedQueries(value = {
		@NamedQuery(name = "CodigoEstadoCuenta.setEstatusWhereId", query = "UPDATE CodigoEstadoCuenta cec SET cec.estatus = :estatus WHERE cec.id = :idCodigo") })
public class CodigoEstadoCuenta extends AbstractCatalogoAdm implements Comparable<CodigoEstadoCuenta> {

	@Column(name = "codigo", nullable = false)
	private String codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	public CodigoEstadoCuenta() {
		super();
	}

	public CodigoEstadoCuenta(String codigo, String primerRenglon, String segundoRenglon, String referencia,
			Entidad entidad, Categoria categoria) {
		super();
		this.codigo = codigo;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuenta(String codigo, String primerRenglon, String segundoRenglon, String referencia,
			Entidad entidad, Categoria categoria, Long id, Boolean estatus, Date createdDate, Date lastModifiedDate,
			String createdBy, String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.codigo = codigo;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuenta [codigo=" + codigo + ", entidad=" + entidad + ", categoria=" + categoria + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, entidad, categoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof CodigoEstadoCuenta))
			return false;

		final CodigoEstadoCuenta other = (CodigoEstadoCuenta) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(CodigoEstadoCuenta o) {
		return o.codigo.compareTo(this.codigo);
	}

}
