/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class CodigoEstadoCuenta extends AbstractCatalogoAdm implements Comparable<CodigoEstadoCuenta> {

	@Column(name = "codigo", nullable = false)
	private String codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
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
	public int compareTo(CodigoEstadoCuenta o) {
		return o.codigo.compareTo(this.codigo);
	}

}
