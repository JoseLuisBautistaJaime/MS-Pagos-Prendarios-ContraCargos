/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.SubEstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * Nombre: SubEstatusConciliacionBuilder Descripcion: Clase de capa de builder
 * que se encarga de convertir difrentes tipos de objetos y entidades
 * relacionadas con el sub estatus de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 06/05/2019 13:21 hrs.
 * @version 0.1
 */
public abstract class SubEstatusConciliacionBuilder {

	public SubEstatusConciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo SubEstatusConciliacionDTO a partir de un entitie
	 * de tipo SubEstatusConciliacion.
	 * 
	 * @param subEstatusConciliacion
	 * @return subEstatusConciliacionDTO
	 */
	public static SubEstatusConciliacionDTO buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(
			SubEstatusConciliacion subEstatusConciliacion) {
		SubEstatusConciliacionDTO subEstatusConciliacionDTO = null;
		if (subEstatusConciliacion != null) {
			subEstatusConciliacionDTO = new SubEstatusConciliacionDTO();
			subEstatusConciliacionDTO.setId(subEstatusConciliacion.getId());
			subEstatusConciliacionDTO.setDescripcion(subEstatusConciliacion.getDescription());
		}
		return subEstatusConciliacionDTO;
	}

	/**
	 * Construye un entitie de tipo SubEstatusConciliacion a partir de un objeto de
	 * tipo SubEstatusConciliacionDTO.
	 * 
	 * @param subEstatusConciliacionDTO
	 * @return subEstatusConciliacion
	 */
	public static SubEstatusConciliacion buildSubEstatusConciliacionFromSubEstatusConciliacionDTO(
			SubEstatusConciliacionDTO subEstatusConciliacionDTO) {
		SubEstatusConciliacion subEstatusConciliacion = null;
		if (subEstatusConciliacionDTO != null) {
			subEstatusConciliacion = new SubEstatusConciliacion();
			subEstatusConciliacion.setId(subEstatusConciliacionDTO.getId());
			subEstatusConciliacion.setDescription(subEstatusConciliacionDTO.getDescripcion());
		}
		return subEstatusConciliacion;
	}

}
