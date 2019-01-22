package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: ReglaNegocioBuilder Descripcion: Builder que se encaraga de fabricar
 * objetos de entities y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 19:23 hrs.
 * @version 0.1
 */
public class ReglaNegocioBuilder {

	private ReglaNegocioBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * 
	 * Metodo que construye un objeto de tipo ReglaNegocioDTO desde un entity de
	 * tipo ReglaNegocio
	 * 
	 * @param ReglaNegocio reglaNegocio
	 * @return ReglaNegocioDTO
	 */
	public static ReglaNegocioDTO buildReglaNegocioDTOFromReglaNegocio(ReglaNegocio reglaNegocio) {
		ReglaNegocioDTO rnDTO = null;
		if (null != reglaNegocio) {
			rnDTO = new ReglaNegocioDTO();
			rnDTO.setAfliacion(AfiliacionBuilder.buildAfiliacionDTOFromAfilaicion(reglaNegocio.getAfiliacion()));
			rnDTO.setConsulta(reglaNegocio.getConsulta());
			rnDTO.setDescripcion(reglaNegocio.getDescripcion());
			rnDTO.setId(reglaNegocio.getId());
			rnDTO.setNombre(reglaNegocio.getNombre());
			rnDTO.setVariables(VariableBuilder.buildVariableDTOFromVariableSet(reglaNegocio.getVariables()));
		}
		return rnDTO;
	}

	/**
	 * 
	 * Metodo que construye una lista de objetos de tipo ReglaNegocioDTO desde una
	 * lista de entities de tipo ReglaNegocio
	 * 
	 * @param reglasNegocio
	 * @return
	 */
	public static List<ReglaNegocioDTO> buildReglaNegocioDTOFromReglaNegocioList(List<ReglaNegocio> reglasNegocioList) {
		List<ReglaNegocioDTO> lstReglaNegocioDTO = null;
		if (null != reglasNegocioList && !reglasNegocioList.isEmpty()) {
			lstReglaNegocioDTO = new ArrayList<>();
			for (ReglaNegocio reglaNegocio : reglasNegocioList) {
				lstReglaNegocioDTO.add(buildReglaNegocioDTOFromReglaNegocio(reglaNegocio));
			}
		}
		return lstReglaNegocioDTO;
	}

	/**
	 * 
	 * Metodo que construye un objeto de tipo ReglaNegocioResumenDTO desde un Array
	 * de tipo Object NOTA: Aqui se toma siempre una longitud de 3 ya que es la
	 * establecida como resultado de la evaluacion de una regla de negocios
	 * 
	 * @param obj
	 * @return
	 */
	public static ReglaNegocioResumenDTO buildReglaNegocioResumenDTOFromObjArr(Object[] obj) {
		ReglaNegocioResumenDTO reglaNegocioResumenDTO = null;
		if (null != obj && obj.length == 3) {
			reglaNegocioResumenDTO = new ReglaNegocioResumenDTO(
					Integer.parseInt(null != obj[0] ? obj[0].toString() : null),
					Integer.parseInt(null != obj[1] ? obj[1].toString() : null),
					Integer.parseInt(null != obj[2] ? obj[2].toString() : null) == 1);
		}
		return reglaNegocioResumenDTO;
	}

}
