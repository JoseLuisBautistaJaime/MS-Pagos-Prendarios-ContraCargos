/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Objects;

/**
 * @name EstatusEjecucionConciliacionDTO
 * @description Clase que encapsula la información del estatus de la
 *              ejecución del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 03/11/2021 17:22 hrs.
 * @version 0.1
 */


public class EstatusEjecucionConciliacionDTO implements Comparable<EstatusEjecucionConciliacionDTO> {

	private Integer id;
	private String descripcionCorta;
	private String descripcion;
	private Integer orderNumber;

	public EstatusEjecucionConciliacionDTO() {
		super();
	}

	public EstatusEjecucionConciliacionDTO(Integer id) {
		super();
		this.id = id;
	}

	public EstatusEjecucionConciliacionDTO(Integer id, String descripcionCorta, String descripcion, Integer orderNumber) {
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
		return "EstatusEjecucionConciliacionDTO [id=" + id + ", descripcionCorta=" + descripcionCorta + ", descripcion=" + descripcion + ", orderNumber=" + orderNumber +"]";
	}

	@Override
	public int compareTo(EstatusEjecucionConciliacionDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descripcionCorta, descripcion,orderNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EstatusEjecucionConciliacionDTO)) {
			return false;
		}
		final EstatusEjecucionConciliacionDTO other = (EstatusEjecucionConciliacionDTO) obj;
		return (this.hashCode() == other.hashCode());
	}

}
