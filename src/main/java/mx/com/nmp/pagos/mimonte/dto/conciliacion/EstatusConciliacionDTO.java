/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusConciliacionDTO
 * @description Clase que encapsula la informacion del estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 17:22 hrs.
 * @version 0.1
 */
public class EstatusConciliacionDTO extends AbstractConciliacionBasicaDTO
		implements Comparable<EstatusConciliacionDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 6207923110827146624L;

	public EstatusConciliacionDTO() {
		super();
	}

	public EstatusConciliacionDTO(Long id, String descripcion, Boolean estatus) {
		super(id, descripcion, estatus);
	}

	@Override
	public int compareTo(EstatusConciliacionDTO o) {
		return 0;
	}

}
