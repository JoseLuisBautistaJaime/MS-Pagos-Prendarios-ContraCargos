/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @name ConciliacionRequestDTO
 * @description Clase que encapsula la información de una conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 16:50 hrs.
 * @version 0.1
 */
public class ConciliacionRequestDTO implements Comparable<ConciliacionRequestDTO> {

	private Long idEntidad;
	private Long idCuenta;
	private String idCorresponsal;

	public ConciliacionRequestDTO() {
		super();
	}

	public ConciliacionRequestDTO(Long idEntidad, Long idCuenta) {
		super();
		this.idEntidad = idEntidad;
		this.idCuenta = idCuenta;
	}

	public ConciliacionRequestDTO(Long idEntidad, Long idCuenta, String idCorresponsal) {
		super();
		this.idEntidad = idEntidad;
		this.idCuenta = idCuenta;
		this.idCorresponsal = idCorresponsal;
	}

	public String getIdCorresponsal() {
		return idCorresponsal;
	}

	public void setIdCorresponsal(String idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	@Override
	public String toString() {
		return "ConciliacionRequestDTO [idEntidad=" + idEntidad + ", idCuenta=" + idCuenta + ", idCorresponsal="
				+ idCorresponsal + "]";
	}

	@Override
	public int compareTo(ConciliacionRequestDTO arg0) {
		return 0;
	}

}
