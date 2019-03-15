package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: AfiliacionRespPostDTO Descripcion: Clase que encapsula la informacion
 * perteneciente a una a filiaicon enviada como response en el alta de
 * afiliaciones.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/03/2018 12:58 hrs.
 * @version 0.1
 */
public class AfiliacionRespPostDTO implements Comparable<AfiliacionRespPostDTO> {

	private Long id;
	private Long numero;
	private Boolean estatus;

	public AfiliacionRespPostDTO() {
		super();
	}

	public AfiliacionRespPostDTO(Long id, Long numero, Boolean estatus) {
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
		return "AfiliacionRespPostDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + "]";
	}

	@Override
	public int compareTo(AfiliacionRespPostDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
