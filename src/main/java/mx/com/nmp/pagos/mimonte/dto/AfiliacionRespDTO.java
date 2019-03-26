/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name AfiliacionRespDTO
 * @description Clase que encapsula la informacion perteneciente a un tipo de
 *              Afiliacion.
 *
 * @author Victor Manuel Moran
 * @creationDate 12/12/2018 16:39 hrs.
 * @version 0.1
 */
public class AfiliacionRespDTO implements Comparable<AfiliacionRespDTO> {

	private Long id;
	private Long numero;

	public AfiliacionRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AfiliacionRespDTO(Long id, Long numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "AfiliacionRespDTO [id=" + id + ", numero=" + "]";
	}

	@Override
	public int compareTo(AfiliacionRespDTO o) {
		return o.id.compareTo(this.id);
	}

}
