/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * Nombre: EntidadBuilder Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con la
 * entidad
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 06/05/2019 13:21 hrs.
 * @version 0.1
 */
public class EntidadBuilder {

	public EntidadBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**Se ocupa
	 * Construye un objeto EntidadDTO a partir de una entidad de tipo Entidad.
	 * @param entidad
	 * @return entidadDTO
	 */
	public static EntidadDTO buildEntidadDTOFromEntidad(Entidad entidad) {
		EntidadDTO entidadDTO = null;
		if(entidad != null) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setId(entidad.getId());
			entidadDTO.setDescripcion(entidad.getDescription());
			entidadDTO.setEstatus(entidad.getEstatus());
			entidadDTO.setNombre(entidad.getNombre());
		}
		return entidadDTO;
	}
	
	/**Se ocupa
	 * Construye una entidad Entidad a partir de un objeto de tipo EntidadDTO.
	 * @param entidadDTO
	 * @return entidad
	 */
	public static Entidad buildEntidadFromEntidadDTO(EntidadDTO entidadDTO) {
		Entidad entidad = null;
		if(entidadDTO != null) {
			entidad = new Entidad();
			entidad.setId(entidadDTO.getId());
			entidad.setDescription(entidadDTO.getDescripcion());
			entidad.setEstatus(entidadDTO.getEstatus());
			entidad.setNombre(entidadDTO.getNombre());
		}
		return entidad;
	}
	
}
