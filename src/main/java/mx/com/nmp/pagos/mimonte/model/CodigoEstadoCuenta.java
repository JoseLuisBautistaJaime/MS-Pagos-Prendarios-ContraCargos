package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

/**
 * Nombre: CodigoEstadoCuenta Descripcion: Clase que encapsula la informacion de
 * una entidad de catalogo de tipo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:10 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuenta extends AbstractCatalogoAdm implements Comparable<CodigoEstadoCuenta> {

	private String leyenda;
	private BaseEntidad baseEntidad;
	private Categoria categoria;

	public CodigoEstadoCuenta() {
		super();
	}

	public CodigoEstadoCuenta(String leyenda, BaseEntidad baseEntidad, Categoria categoria) {
		super();
		this.leyenda = leyenda;
		this.baseEntidad = baseEntidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuenta(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion,
			Long usuarioCreador, String leyenda, BaseEntidad baseEntidad, Categoria categoria) {
		super(id, estatus, fechaCreacion, fechaModificacion, usuarioCreador);
		this.leyenda = leyenda;
		this.baseEntidad = baseEntidad;
		this.categoria = categoria;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public BaseEntidad getBaseEntidad() {
		return baseEntidad;
	}

	public void setBaseEntidad(BaseEntidad baseEntidad) {
		this.baseEntidad = baseEntidad;
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
