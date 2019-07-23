/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name CuentaByNumeroDTO
 * @description Clase que encapsula la informacion de un objeto Cuenta para
 *              consulta por numero
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 16:33 hrs.
 * @version 0.1
 */
public class CuentaByNumeroDTO {

	private String numero;

	public CuentaByNumeroDTO() {
		super();
	}

	public CuentaByNumeroDTO(String numero) {
		super();
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "CuentaByNumeroDTO [numero=" + numero + "]";
	}

}
