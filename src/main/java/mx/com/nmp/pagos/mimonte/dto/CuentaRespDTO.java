/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CuentaRespDTO
 * @description Clase que encapsula la informacion de un objeto cuenta de
 *              respuesta en alta de cuentas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/03/2019 13:21 hrs.
 * @version 0.1
 */
public class CuentaRespDTO implements Comparable<CuentaRespDTO> {

	private Long id;
	private String numero;
	private Boolean estatus;

	public CuentaRespDTO() {
		super();
	}

	public CuentaRespDTO(Long id, String numero, Boolean estatus) {
		super();
		this.id = id;
		this.numero = numero;
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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "CuentaRespDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(CuentaRespDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
