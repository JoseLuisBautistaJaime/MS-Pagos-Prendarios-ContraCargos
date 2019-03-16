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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Nombre: CodigoEstadoCuenta Descripcion: Clase que encapsula la informacion de
 * una entidad de catalogo de tipo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:10 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tc_codigo_estado_cuenta")
public class CodigoEstadoCuenta extends AbstractCatalogoAdm implements Comparable<CodigoEstadoCuenta> {

	@Column(name = "codigo", nullable = false)
	private String codigo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tr_codigo_estado_cuenta_entidad", joinColumns = {
			@JoinColumn(name = "id_codigo_estado_cuenta", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_entidad", nullable = false) })
	private Set<Entidad> entidades;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	public CodigoEstadoCuenta() {
		super();
	}

	public CodigoEstadoCuenta(String codigo, String primerRenglon, String segundoRenglon,
			String referencia, Set<Entidad> entidades, Categoria categoria) {
		super();
		this.codigo = codigo;
		this.entidades = entidades;
		this.categoria = categoria;
	}

	public CodigoEstadoCuenta(String codigo, String primerRenglon, String segundoRenglon,
			String referencia, Set<Entidad> entidades, Categoria categoria, Long id, Boolean estatus, Date createdDate,
			Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.codigo = codigo;
		this.entidades = entidades;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Set<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(Set<Entidad> entidades) {
		this.entidades = entidades;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuenta [codigo=" + codigo + ", entidad=" + entidades + ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuenta o) {
		return o.codigo.compareTo(this.codigo);
	}

}
