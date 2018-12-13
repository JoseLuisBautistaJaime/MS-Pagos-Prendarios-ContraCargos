package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: ReglaNegocioBuilder
 * Descripcion: Builder que se encaraga de fabricar
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
		ReglaNegocioDTO rnDTO = new ReglaNegocioDTO();
		rnDTO.setAfliacion(AfiliacionBuilder.buildAfiliacionDTOFromAfilaicion(reglaNegocio.getAfiliacion()));
		rnDTO.setConsulta(reglaNegocio.getConsulta());
		rnDTO.setDescripcion(reglaNegocio.getDescripcion());
		rnDTO.setId(reglaNegocio.getId());
		rnDTO.setNombre(reglaNegocio.getNombre());
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
	public static List<ReglaNegocioDTO> buildReglaNegocioDTOFromReglaNegocioList(List<ReglaNegocio> reglasNegocio) {
		List<ReglaNegocioDTO> lstReglaNegocioDTO = new ArrayList<>();
		for (ReglaNegocio reglaNegocio : reglasNegocio) {
			lstReglaNegocioDTO.add(buildReglaNegocioDTOFromReglaNegocio(reglaNegocio));
		}
		return lstReglaNegocioDTO;
	}

}
