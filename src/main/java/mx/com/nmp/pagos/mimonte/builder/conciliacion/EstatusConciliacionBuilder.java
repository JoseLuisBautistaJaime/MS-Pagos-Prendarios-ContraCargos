/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;

/**
 * Nombre: EstatusConciliacionBuilder Descripcion: Clase de capa de builder que
 * se encarga de convertir difrentes tipos de objetos y entidades relacionadas
 * con el Estatus de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 06/05/2019 17:20 hrs.
 * @version 0.1
 */
public abstract class EstatusConciliacionBuilder {

	public EstatusConciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo EstatusConciliacionDTO a partir de la entidad
	 * EstatusConciliacion.
	 * 
	 * @param estatusConciliacion
	 * @return estatusConciliacion
	 */
	public static EstatusConciliacionDTO buildEstatusConciliacionDTOFromEstatusConciliacion(
			EstatusConciliacion estatusConciliacion) {
		EstatusConciliacionDTO estatusConciliacionDTO = null;
		if (estatusConciliacion != null) {
			estatusConciliacionDTO = new EstatusConciliacionDTO();
			estatusConciliacionDTO.setId(estatusConciliacion.getId());
			estatusConciliacionDTO.setEstatus(estatusConciliacion.getEstatus());
			estatusConciliacionDTO.setDescripcion(estatusConciliacion.getDescripcion());
		}
		return estatusConciliacionDTO;
	}

	/**
	 * Construye una entidad EstatusConciliacion a partir de un objeto de tipo
	 * EstatusConciliacionDTO.
	 * 
	 * @param estatusConciliacionDTO
	 * @return
	 */
	public static EstatusConciliacion buildEstatusConciliacionFromEstatusConciliacionDTO(
			EstatusConciliacionDTO estatusConciliacionDTO) {
		EstatusConciliacion estatusConciliacion = null;
		if (estatusConciliacionDTO != null) {
			estatusConciliacion = new EstatusConciliacion();
			estatusConciliacion.setId(estatusConciliacionDTO.getId());
			estatusConciliacion.setDescripcion(estatusConciliacionDTO.getDescripcion());
			estatusConciliacion.setEstatus(estatusConciliacionDTO.getEstatus());
			estatusConciliacion.setCreatedBy(null);
			estatusConciliacion.setCreatedDate(null);
			estatusConciliacion.setDescripcionCorta(null);
			estatusConciliacion.setLastModifiedBy(null);
			estatusConciliacion.setLastModifiedDate(null);
		}
		return estatusConciliacion;
	}

}
