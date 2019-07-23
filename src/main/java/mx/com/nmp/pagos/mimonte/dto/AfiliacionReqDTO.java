/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Objects;

/**
 * @name AfiliacionReqDTO
 * @description Clase que encapsula la informacion sobre una afiliacion en el
 *              requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/03/2019 14:30 hrs.
 * @version 0.1
 */
public class AfiliacionReqDTO implements Comparable<AfiliacionReqDTO> {

	private Long id;
	private String numero;

	public AfiliacionReqDTO() {
		super();
	}

	public AfiliacionReqDTO(Long id, String numero) {
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
		return "AfiliacionReqDTO [id=" + id + ", numero=" + numero + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof AfiliacionReqDTO))
			return false;

		final AfiliacionReqDTO other = (AfiliacionReqDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(AfiliacionReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
