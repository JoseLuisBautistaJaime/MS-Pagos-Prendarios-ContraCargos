/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name MovTransitoRequestDTO
 * @description Clase que encapsula el request de MovTransitoRequestDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 03/04/2019 16:05 hrs.
 * @version 0.1
 */
public class MovTransitoRequestDTO implements Comparable<MovTransitoRequestDTO> {

	private Long id;
	private String tipo;

	public MovTransitoRequestDTO() {
		super();
	}

	public MovTransitoRequestDTO(Long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "MovTransitoRequestDTO [id=" + id + ", tipo=" + tipo + "]";
	}

	@Override
	public int compareTo(MovTransitoRequestDTO o) {
		return 0;
	}

}
