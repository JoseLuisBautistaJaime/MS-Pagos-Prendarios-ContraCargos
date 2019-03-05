package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: CodigoEstadoCuentaDTO Descripcion: Clase que encapsula la informacion
 * de un catalogo de codigo de estado de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaDTO extends AbstractCatalogoDTO implements Comparable<CodigoEstadoCuentaDTO> {

	private String leyenda;
	private BaseEntidadDTO baseEntidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaDTO() {
		super();
	}

	public CodigoEstadoCuentaDTO(String leyenda, BaseEntidadDTO baseEntidad, CategoriaDTO categoria) {
		super();
		this.leyenda = leyenda;
		this.baseEntidad = baseEntidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuentaDTO(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion,
			Long usuarioCreador, String leyenda, BaseEntidadDTO baseEntidad, CategoriaDTO categoria) {
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

	public BaseEntidadDTO getBaseEntidad() {
		return baseEntidad;
	}

	public void setBaseEntidad(BaseEntidadDTO baseEntidad) {
		this.baseEntidad = baseEntidad;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuenta [leyenda=" + leyenda + ", baseEntidad=" + baseEntidad + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaDTO o) {
		return o.getLeyenda().compareTo(this.leyenda);
	}

}
