/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusEjecucionPreconciliacionDTO
 * @description Clase que encapsula la información del estatus de la
 *              ejecución del proceso de pre-conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 05/11/2021 09:22 hrs.
 * @version 0.1
 */


public class EstatusEjecucionPreconciliacionDTO implements Comparable<EstatusEjecucionPreconciliacionDTO> {

	private Integer id;
	private String descripcionCorta;
	private String descripcion;
	private Integer orderNumber;

	public EstatusEjecucionPreconciliacionDTO() {
		super();
	}

	public EstatusEjecucionPreconciliacionDTO(Integer id) {
		super();
		this.id = id;
	}

	public EstatusEjecucionPreconciliacionDTO(Integer id, String descripcionCorta, String descripcion, Integer orderNumber) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcion = descripcion;
		this.orderNumber = orderNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "EstatusEjecucionPreconciliacionDTO [id=" + id + ", descripcionCorta=" + descripcionCorta + ", descripcion=" + descripcion + ", orderNumber=" + orderNumber +"]";
	}

	@Override
	public int compareTo(EstatusEjecucionPreconciliacionDTO o) {
		return 0;
	}
}
