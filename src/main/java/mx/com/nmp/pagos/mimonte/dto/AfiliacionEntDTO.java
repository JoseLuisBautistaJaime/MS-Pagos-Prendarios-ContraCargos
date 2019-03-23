/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: AfiliacionEntDTO Descripcion: Clase que encapsula la informacion de
 * Afiliacion que es insertada dentro de el response de los servicios del
 * catalogo de entidades
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 07/03/2019 10:44 hrs.
 * @version 0.1
 */
public class AfiliacionEntDTO implements Comparable<AfiliacionEntDTO> {

	public AfiliacionEntDTO() {
		super();
	}

	public AfiliacionEntDTO(Long id, Long numero, Boolean estatus) {
		super();
		this.id = id;
		this.numero = numero;
		this.estatus = estatus;
	}

	private Long id;
	private Long numero;
	private Boolean estatus;

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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public int compareTo(AfiliacionEntDTO o) {
		return o.getNumero().compareTo(this.numero);
	}

	@Override
	public String toString() {
		return "AfiliacionEntDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + "]";
	}

}
