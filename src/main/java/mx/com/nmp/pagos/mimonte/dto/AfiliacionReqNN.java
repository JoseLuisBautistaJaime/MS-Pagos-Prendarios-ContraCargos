/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name AfiliacionReqNN
 * @description Clase que encapsula la informacion perteneciente a un tipo de
 *              Afiliacion para request de update.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/04/2019 13:35 hrs.
 * @version 0.1
 */
public class AfiliacionReqNN implements Comparable<AfiliacionReqNN> {

	private Long id;

	public AfiliacionReqNN() {
		super();
	}

	public AfiliacionReqNN(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AfiliacionRespDTO [id=" + id + "]";
	}

	@Override
	public int compareTo(AfiliacionReqNN o) {
		return o.id.compareTo(this.id);
	}

}
