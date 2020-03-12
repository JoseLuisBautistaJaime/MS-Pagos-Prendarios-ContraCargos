/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CodigoEstadoCuentaReqSaveDTO
 * @description Clase que encapsula la informacion de un catalogo de codigo de
 *              estado de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 12:13 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaReqSaveDTO implements Comparable<CodigoEstadoCuentaReqSaveDTO> {

	private String codigo;
	private EntidadReqDTO entidad;
	private CategoriaReqDTO categoria;

	public CodigoEstadoCuentaReqSaveDTO() {
		super();
	}

	public CodigoEstadoCuentaReqSaveDTO(String codigo, EntidadReqDTO entidad, CategoriaReqDTO categoria) {
		super();
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
		return "CodigoEstadoCuentaReqDTO [codigo=" + codigo + ", entidad=" + entidad + ", categoria=" + categoria + "]";
	}

	@Override
	public int compareTo(CodigoEstadoCuentaReqSaveDTO o) {
		return o.codigo.compareTo(this.codigo);
	}

}
