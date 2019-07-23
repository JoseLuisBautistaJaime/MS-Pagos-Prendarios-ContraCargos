/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name AfiliacionRespPostDTO
 * @description Clase que encapsula la informacion perteneciente a una a
 *              afiliacion enviada como response en el alta de afiliaciones.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/03/2018 12:58 hrs.
 * @version 0.1
 */
public class AfiliacionRespPostDTO implements Comparable<AfiliacionRespPostDTO> {

	private Long id;
	private String numero;

	public AfiliacionRespPostDTO() {
		super();
	}

	public AfiliacionRespPostDTO(Long id, String numero) {
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "AfiliacionRespPostDTO [id=" + id + ", numero=" + numero + "]";
	}

	@Override
	public int compareTo(AfiliacionRespPostDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
