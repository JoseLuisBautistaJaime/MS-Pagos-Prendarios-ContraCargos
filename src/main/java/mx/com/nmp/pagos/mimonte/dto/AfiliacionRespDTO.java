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
	private String numero;

	public AfiliacionRespDTO() {
		super();
	}

	public AfiliacionRespDTO(Long id, String numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public AfiliacionRespDTO(Long id) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "AfiliacionRespDTO [id=" + id + ", numero=" + "]";
	}

	@Override
	public int compareTo(AfiliacionRespDTO o) {
		return o.id.compareTo(this.id);
	}

}
