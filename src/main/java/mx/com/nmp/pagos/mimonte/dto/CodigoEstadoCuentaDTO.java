/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * @name CodigoEstadoCuentaDTO
 * @description Clase que encapsula la informacion de un catalogo de codigo de
 *              estado de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 14:07 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaDTO extends AbstractCatalogoDTO implements Comparable<CodigoEstadoCuentaDTO> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -32478678397212959L;

	private String codigo;
	private EntidadDTO entidad;
	private CategoriaDTO categoria;

	public CodigoEstadoCuentaDTO() {
		super();
	}

	public CodigoEstadoCuentaDTO(String codigo, EntidadDTO entidad, CategoriaDTO categoria) {
		super();
		this.codigo = codigo;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public CodigoEstadoCuentaDTO(String codigo, EntidadDTO entidad, CategoriaDTO categoria, Long id, Boolean estatus,
			Date createdDate, Date lastModifiedDate, String createdBy, String lastModifiedBy, String description,
			String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.codigo = codigo;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaDTO [codigo=" + codigo + ", entidad=" + entidad + ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaDTO o) {
		return o.codigo.compareTo(this.codigo);
	}

}
