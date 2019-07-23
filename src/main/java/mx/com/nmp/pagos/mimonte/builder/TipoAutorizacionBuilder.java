/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.model.TipoAutorizacion;

/**
 * @name TipoAutorizacionBuilder
 * @description Builder para la entidad Tipo de afiliacion
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 31/01/2019 16:49 hrs.
 * @version 0.1
 */
public class TipoAutorizacionBuilder {

	private TipoAutorizacionBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Construye un entity de tipo TipoAutorizacion a partir de un objeto de tipo
	 * TipoAutorizacionDTO
	 * 
	 * @param tipoAfiliacionDTO
	 * @return
	 */
	public static TipoAutorizacion buildTipoAutorizacionFromTipoAutorizacionDTO(TipoAutorizacionDTO tipoAfiliacionDTO) {
		TipoAutorizacion tipoAfiliacion = null;
		if (null != tipoAfiliacionDTO) {
			tipoAfiliacion = new TipoAutorizacion();
			tipoAfiliacion.setId(tipoAfiliacionDTO.getId());
			tipoAfiliacion.setDescripcion(tipoAfiliacionDTO.getDescripcion());
		}
		return tipoAfiliacion;
	}

	/**
	 * Construye un Set de objetos de tipo TipoAutorizacionDTO a partir de un Set de
	 * entities de tipo TipoAutorizacion
	 * 
	 * @param tipoAutorizacionSet
	 * @return
	 */
	public static Set<TipoAutorizacionDTO> buildTipoAutorizacionDTOSetFromTipoAutorizacionSet(
			Set<TipoAutorizacion> tipoAutorizacionSet) {
		Set<TipoAutorizacionDTO> tipoAutorizacionDTOSet = null;
		if (null != tipoAutorizacionSet) {
			tipoAutorizacionDTOSet = new TreeSet<>();
			for (TipoAutorizacion tipoAutorizacion : tipoAutorizacionSet) {
				tipoAutorizacionDTOSet.add(buildTipoAutorizacionDTOFromTipoAutorizacion(tipoAutorizacion));
			}
		}
		return tipoAutorizacionDTOSet;
	}

	/**
	 * Construye un objeto de tipo TipoAutorizacionDTO a partir de un entity de tipo
	 * TipoAutorizacion
	 * 
	 * @param tipoAutorizacion
	 * @return
	 */
	public static TipoAutorizacionDTO buildTipoAutorizacionDTOFromTipoAutorizacion(TipoAutorizacion tipoAutorizacion) {
		TipoAutorizacionDTO tipoAutorizacionDTO = null;
		if (null != tipoAutorizacion) {
			tipoAutorizacionDTO = new TipoAutorizacionDTO();
			tipoAutorizacionDTO.setId(tipoAutorizacion.getId());
			tipoAutorizacionDTO.setDescripcion(tipoAutorizacion.getDescripcion());
		}
		return tipoAutorizacionDTO;
	}

}
