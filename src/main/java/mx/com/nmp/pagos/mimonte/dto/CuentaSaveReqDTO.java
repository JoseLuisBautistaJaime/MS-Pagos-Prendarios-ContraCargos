/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaSaveReqDTO
 * @description Clase que encapsula la informacion informacion sobre una cuenta
 *              en el requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 13:01 hrs.
 * @version 0.1
 */
public class CuentaSaveReqDTO implements Comparable<CuentaSaveReqDTO> {

	private Long id;
	private List<AfiliacionSaveDTO> afiliaciones;

	public CuentaSaveReqDTO() {
		super();
	}

	public CuentaSaveReqDTO(Long id, List<AfiliacionSaveDTO> afiliaciones) {
		super();
		this.id = id;
		this.afiliaciones = afiliaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AfiliacionSaveDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(List<AfiliacionSaveDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public String toString() {
		return "CuentaReqDTO [id=" + id + ",afiliaicones=" + afiliaciones + "]";
	}

	@Override
	public int compareTo(CuentaSaveReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
