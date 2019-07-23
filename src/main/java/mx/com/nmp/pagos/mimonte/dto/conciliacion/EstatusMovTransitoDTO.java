/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusMovTransitoDTO
 * @description Clase que encapsula la informacion de la información de los
 *              movimientos de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:32 hrs.
 * @version 0.1
 */
public class EstatusMovTransitoDTO implements Comparable<EstatusMovTransitoDTO> {

	private Integer id;
	private String descripcion;
	private Boolean estatus;

	public EstatusMovTransitoDTO() {
		super();
	}

	public EstatusMovTransitoDTO(Integer id, String descripcion, Boolean estatus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "EstatusMovTransitoDTO [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(EstatusMovTransitoDTO o) {
		return 0;
	}

}
