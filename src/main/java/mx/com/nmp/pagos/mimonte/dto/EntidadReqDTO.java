/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

public class EntidadReqDTO implements Comparable<EntidadReqDTO> {

	private Long id;

	public EntidadReqDTO() {
		super();
	}

	public EntidadReqDTO(Long id) {
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
		return "EntidadReqDTO [id=" + id + "]";
	}

	@Override
	public int compareTo(EntidadReqDTO o) {
		return o.id.compareTo(this.id);
	}

}
