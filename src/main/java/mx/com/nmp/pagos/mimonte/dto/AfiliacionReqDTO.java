/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: AfiliacionReqDTO Descripcion: Clase que encapsula la informacion
 * informacion sobre una afiliacion en el requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/03/2019 14:30 hrs.
 * @version 0.1
 */
public class AfiliacionReqDTO implements Comparable<AfiliacionReqDTO> {

	private Long id;
	private Long numero;
	private Boolean estatus;

	public AfiliacionReqDTO() {
		super();
	}

	public AfiliacionReqDTO(Long id, Long numero, Boolean estatus) {
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
	public String toString() {
		return "AfiliacionReqDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(AfiliacionReqDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
