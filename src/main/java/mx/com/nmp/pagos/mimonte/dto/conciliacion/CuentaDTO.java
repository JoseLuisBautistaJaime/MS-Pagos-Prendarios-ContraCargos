/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name CuentaDTO
 * @description Clase que encapsula la informacion de un objeto de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 21:10 hrs.
 * @version 0.1
 */
public class CuentaDTO {

	private Integer id;
	private String numero;
	private Boolean estatus;

	public CuentaDTO() {
		super();
	}

	public CuentaDTO(Integer id, String numero, Boolean estatus) {
		super();
		this.id = id;
		this.numero = numero;
		this.estatus = estatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "CuentaDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + "]";
	}

}
