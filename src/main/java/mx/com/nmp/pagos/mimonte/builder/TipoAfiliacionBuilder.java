package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.TipoAfiliacionDTO;
import mx.com.nmp.pagos.mimonte.model.TipoAfiliacion;

/**
 * @description Builder para la entidad Tipo de afiliacion
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 31/01/2019 16:49 hrs.
 * @version 0.1
 */
public class TipoAfiliacionBuilder {

	private TipoAfiliacionBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Construye un objeto de tipo TipoAfiliacionDTO de una entidad de tipo
	 * TipoAfiliacion
	 * 
	 * @param tipoAfiliacion
	 * @return
	 */
	public static TipoAfiliacionDTO buildTipoAfilaicionDTOFromTipoAfiliacion(TipoAfiliacion tipoAfiliacion) {
		TipoAfiliacionDTO tipoAfiliacionDTO = null;
		if (null != tipoAfiliacion) {
			tipoAfiliacionDTO = new TipoAfiliacionDTO();
			tipoAfiliacionDTO.setDescripcionCorta(tipoAfiliacion.getDescripcionCorta());
			tipoAfiliacionDTO.setId(tipoAfiliacion.getId());
		}
		return tipoAfiliacionDTO;
	}
}
