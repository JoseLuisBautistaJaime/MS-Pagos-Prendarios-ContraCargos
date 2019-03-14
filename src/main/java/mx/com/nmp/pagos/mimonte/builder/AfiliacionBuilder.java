package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;

/**
 * Nombre: AfiliacionBuilder Descripcion: Builder que se encaraga de fabricar
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
	 * @param AfiliacionController afiliacion
	 * @return AfiliacionDTO
	 */
	public static AfiliacionDTO buildAfiliacionDTOFromAfiliacion(Afiliacion afiliacion) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacion) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setNumero(afiliacion.getNumero());
			afiliacionDTO.setId(afiliacion.getId());
			afiliacionDTO
					.setTipo(TipoAutorizacionBuilder.buildTipoAfilaicionDTOFromTipoAfiliacion(afiliacion.getTipo()));
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un entitie de tipo Afiliacion a partir de un objeto de tipo
	 * AfiliacionDTO
	 * 
	 * @param afiliacionDTO
	 * @return
	 */
	public static Afiliacion buildAfiliacionFromAfiliacionDTO(AfiliacionDTO afiliacionDTO) {
		Afiliacion afiliacion = null;
		if (null != afiliacionDTO) {
			afiliacion = new Afiliacion();
			afiliacion.setNumero(afiliacionDTO.getNumero());
			afiliacion.setId(afiliacionDTO.getId());
			afiliacion
					.setTipo(TipoAutorizacionBuilder.buildTipoAfilaicionFromTipoAfiliacionDTO(afiliacionDTO.getTipo()));
		}
		return afiliacion;
	}

	/**
	 * Construye un set de objetos de tipo AfiliacionDTO a partir de un set de
	 * entities de tipo Afiliacion
	 * 
	 * @param afiliacionSet
	 * @return
	 */
	public static Set<AfiliacionDTO> buildAfiliacionDTOSetFromAfiliacionSet(Set<Afiliacion> afiliacionSet) {
		Set<AfiliacionDTO> afiliacionDTOSet = null;
		if (null != afiliacionSet && !afiliacionSet.isEmpty()) {
			afiliacionDTOSet = new TreeSet<>();
			for (Afiliacion afiliacion : afiliacionSet) {
				afiliacionDTOSet.add(buildAfiliacionDTOFromAfiliacion(afiliacion));
			}
		}
		return afiliacionDTOSet;
	}

	/**
	 * Construye un Set de entities de tipo Afiliacion a partir de un set de objetos
	 * de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOSet
	 * @return
	 */
	public static Set<Afiliacion> buildAfiliacionSetFromAfiliacionDTOSet(Set<AfiliacionDTO> afiliacionDTOSet) {
		Set<Afiliacion> afiliacionSet = null;
		if (null != afiliacionDTOSet && !afiliacionDTOSet.isEmpty()) {
			afiliacionSet = new TreeSet<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet) {
				afiliacionSet.add(buildAfiliacionFromAfiliacionDTO(afiliacionDTO));
			}
		}
		return afiliacionSet;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionDTO a partir de un set de
	 * entities de tipo Afiliacion
	 * 
	 * @param afiliacionSet
	 * @return
	 */
	public static List<AfiliacionDTO> buildAfiliacionDTOListFromAfiliacionSet(Set<Afiliacion> afiliacionSet) {
		List<AfiliacionDTO> afiliacionDTOList = null;
		if (null != afiliacionSet) {
			afiliacionDTOList = new ArrayList<>(buildAfiliacionDTOListFromAfiliacionSet(afiliacionSet));
		}
		return afiliacionDTOList;
	}

}
