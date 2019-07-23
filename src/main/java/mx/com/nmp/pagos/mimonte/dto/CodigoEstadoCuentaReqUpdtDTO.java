/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CodigoEstadoCuentaReqUpdtDTO
 * @description Clase que encapsula la informacion de un catalogo de codigo de
 *              estado de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/03/2019 14:54 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaReqUpdtDTO implements Comparable<CodigoEstadoCuentaReqUpdtDTO> {

	private String codigo;
	private Long id;
	private EntidadReqDTO entidad;
	private CategoriaReqDTO categoria;

	public CodigoEstadoCuentaReqUpdtDTO() {
		super();
	}

	public CodigoEstadoCuentaReqUpdtDTO(String codigo, Long id, EntidadReqDTO entidad, CategoriaReqDTO categoria) {
		super();
		this.codigo = codigo;
		this.id = id;
		this.entidad = entidad;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntidadReqDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadReqDTO entidad) {
		this.entidad = entidad;
	}

	public CategoriaReqDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaReqDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CodigoEstadoCuentaReqDTO [codigo=" + codigo + ", id=" + id + ", entidad=" + entidad + ", categoria="
				+ categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaReqUpdtDTO o) {
		return o.id.compareTo(this.id);
	}

}
