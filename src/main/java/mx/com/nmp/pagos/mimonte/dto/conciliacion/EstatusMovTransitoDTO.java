/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name EstatusMovTransitoDTO
 * @description Clase que encapsula la informacion de la información de los
 *              movimientos de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 23:32 hrs.
 * @version 0.1
 */
public class EstatusMovTransitoDTO extends AbstractConciliacionBasicaDTO implements Comparable<EstatusMovTransitoDTO> {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 5886951973471741329L;

	public EstatusMovTransitoDTO() {
		super();
	}

	public EstatusMovTransitoDTO(Integer id, String descripcion, Boolean estatus) {
		super(id, descripcion, estatus);
	}

	@Override
	public int compareTo(EstatusMovTransitoDTO o) {
		return 0;
	}

}
