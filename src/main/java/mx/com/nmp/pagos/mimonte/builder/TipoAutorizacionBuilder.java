package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.model.TipoAutorizacion;

/**
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
	 * Construye un objeto de tipo TipoAutorizacionDTO de una entidad de tipo
	 * TipoAutorizacion
	 * 
	 * @param tipoAfiliacion
	 * @return
	 */
	public static TipoAutorizacionDTO buildTipoAfilaicionDTOFromTipoAfiliacion(TipoAutorizacion tipoAfiliacion) {
		TipoAutorizacionDTO tipoAfiliacionDTO = null;
		if (null != tipoAfiliacion) {
			tipoAfiliacionDTO = new TipoAutorizacionDTO();
			tipoAfiliacionDTO.setId(tipoAfiliacion.getId());
			tipoAfiliacionDTO.setDescripcion(tipoAfiliacion.getDescripcionCorta());
		}
		return tipoAfiliacionDTO;
	}
}
