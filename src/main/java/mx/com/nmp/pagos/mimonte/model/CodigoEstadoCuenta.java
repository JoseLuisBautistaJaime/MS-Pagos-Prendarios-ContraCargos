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

	@Column(name = "leyenda", nullable = false)
	private String leyenda;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entidad", nullable = false)
	private Entidad entidad;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	public CodigoEstadoCuenta() {
		super();
	}

	public CodigoEstadoCuenta(String leyenda, Entidad entidad, Categoria categoria) {
		super();
		this.leyenda = leyenda;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuenta(Long id, Boolean status, Date creationDate, Date modificationDate, String createdBy,
			String modifiedBy, String leyenda, Entidad entidad, Categoria categoria) {
		super(id, status, creationDate, modificationDate, createdBy, modifiedBy);
		this.leyenda = leyenda;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
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
	public int compareTo(CodigoEstadoCuenta o) {
		return o.getLeyenda().compareTo(this.leyenda);
	}

}
