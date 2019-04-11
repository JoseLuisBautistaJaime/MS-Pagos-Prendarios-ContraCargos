/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaDTO
 * @description Clase que encapsula la informacion de un objeto cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/03/2019 13:21 hrs.
 * @version 0.1
 */
public class CuentaDTO {

	private Long id;
	private String numero;
	private Boolean estatus;
	private List<AfiliacionRespDTO> afiliaciones;

	public CuentaDTO() {
		super();

	}

	public CuentaDTO(Long id, String numero, List<AfiliacionRespDTO> afiliaciones, Boolean estatus) {
		super();
		this.id = id;
		this.numero = numero;
		this.afiliaciones = afiliaciones;
		this.estatus = estatus;
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

	public List<AfiliacionRespDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(List<AfiliacionRespDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "CuentaDTO [id=" + id + ", numero=" + numero + ", afiliaciones=" + afiliaciones + ", estatus=" + estatus
				+ "]";
	}

}
