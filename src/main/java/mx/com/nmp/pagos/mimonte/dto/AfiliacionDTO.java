/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * @name AfiliacionDTO
 * @description Clase que encapsula la informacion perteneciente a un tipo de
 *              Afiliacion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:39 hrs.
 * @version 0.1
 */
public class AfiliacionDTO extends AbstractCatalogoDTO implements Comparable<AfiliacionDTO> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 3894335366665620579L;

	private Long numero;
	private TipoAutorizacionDTO tipo;

	public AfiliacionDTO() {
		super();
	}

	public AfiliacionDTO(Long numero, TipoAutorizacionDTO tipo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
	}

	public AfiliacionDTO(Long id, Long numero, TipoAutorizacionDTO tipo) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public AfiliacionDTO(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, Long numero, TipoAutorizacionDTO tipo) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numero = numero;
		this.tipo = tipo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public TipoAutorizacionDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoAutorizacionDTO tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "AfiliacionDTO [numero=" + numero + ", tipo=" + tipo + "]";
	}

	@Override
	public int compareTo(AfiliacionDTO o) {
		return o.id.compareTo(this.id);
	}

}
