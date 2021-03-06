/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

/**
 * Nombre: TipoContactoDTO Descripcion: Clase que encapsula la informacion
 * perteneciente al tipo de contacto.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 05/03/2019 14:48 hrs.
 * @version 0.1
 */
public class TipoContactoDTO extends AbstractCatalogoDTO implements Comparable<TipoContactoDTO> {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1432435815800353627L;

	public TipoContactoDTO() {
		super();

	}

	public TipoContactoDTO(Long id) {
		super.id = id;

	}

	public TipoContactoDTO(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
	}

	@Override
	public String toString() {
		return "TipoContactoDTO [id=" + id + ", estatus=" + estatus + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy="
				+ lastModifiedBy + "]";
	}

	@Override
	public int compareTo(TipoContactoDTO o) {
		return o.id.compareTo(this.id);
	}

}
