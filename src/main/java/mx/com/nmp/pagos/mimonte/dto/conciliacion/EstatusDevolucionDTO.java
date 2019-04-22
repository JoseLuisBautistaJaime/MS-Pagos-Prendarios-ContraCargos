/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusDevolucionDTO
 * @description Clase que encapsula la informacion de la devolición de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:12 hrs.
 * @version 0.1
 */
public class EstatusDevolucionDTO extends AbstractConciliacionBasicaDTO implements Comparable<EstatusDevolucionDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 3417477420340701274L;

	public EstatusDevolucionDTO() {
		super();
	}

	public EstatusDevolucionDTO(Integer id, String descripcion, Boolean estatus) {
		super(id, descripcion, estatus);
	}

	@Override
	public int compareTo(EstatusDevolucionDTO o) {
		return 0;
	}

}
