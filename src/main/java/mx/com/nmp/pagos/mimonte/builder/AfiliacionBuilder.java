package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;

/**
 * Nombre: AfiliacionBuilder
 * Descripcion: Builder que se encaraga de fabricar
 * objetos de entities y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 19:28 hrs.
 * @version 0.1
 */
public class AfiliacionBuilder {

	private AfiliacionBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * 
	 * Metodo que convierte de un entity tipo Afiliacion a un objeto de tipo
	 * AfiliacionDTO
	 * 
	 * @param Afiliacion afiliacion
	 * @return AfiliacionDTO
	 */
	public static AfiliacionDTO buildAfiliacionDTOFromAfilaicion(Afiliacion afiliacion) {
		AfiliacionDTO afiliacionDTO = null;
		if(null != afiliacion) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setDescripcion(afiliacion.getDescripcion());
			afiliacionDTO.setId(afiliacion.getId());	
		}
		return afiliacionDTO;
	}

}
