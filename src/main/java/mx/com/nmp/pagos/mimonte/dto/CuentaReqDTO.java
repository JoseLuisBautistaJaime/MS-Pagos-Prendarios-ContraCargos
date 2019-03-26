/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaReqDTO
 * @description Clase que encapsula la informacion informacion sobre una cuenta
 *              en el requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/03/2019 14:30 hrs.
 * @version 0.1
 */
public class CuentaReqDTO implements Comparable<CuentaReqDTO> {

	private Long id;
	private String numero;
	private Boolean estatus;
	private List<AfiliacionReqDTO> afiliaciones;

	public CuentaReqDTO() {
		super();
	}

	public CuentaReqDTO(Long id, String numero, Boolean estatus, List<AfiliacionReqDTO> afiliaciones) {
		super();
		this.id = id;
		this.numero = numero;
		this.estatus = estatus;
		this.afiliaciones = afiliaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<AfiliacionReqDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(List<AfiliacionReqDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public String toString() {
		return "CuentaReqDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + ", afiliaicones="
				+ afiliaciones + "]";
	}

	@Override
	public int compareTo(CuentaReqDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
