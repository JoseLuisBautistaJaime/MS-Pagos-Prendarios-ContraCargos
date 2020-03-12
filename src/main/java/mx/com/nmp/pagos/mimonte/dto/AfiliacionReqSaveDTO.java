/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name AfiliacionReqSaveDTO
 * @description Clase que encapsula la informacion sobre una afiliacion en el
 *              requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 11:50 hrs.
 * @version 0.1
 */
public class AfiliacionReqSaveDTO implements Comparable<AfiliacionReqSaveDTO> {

	private String numero;

	public AfiliacionReqSaveDTO() {
		super();
	}

	public AfiliacionReqSaveDTO(String numero) {
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
		return "AfiliacionReqDTO [numero=" + numero + "]";
	}

	@Override
	public int compareTo(AfiliacionReqSaveDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
