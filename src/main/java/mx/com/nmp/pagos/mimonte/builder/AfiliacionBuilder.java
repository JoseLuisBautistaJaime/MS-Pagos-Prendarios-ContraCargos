/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;
import mx.com.nmp.pagos.mimonte.model.TipoAutorizacion;

/**
 * @name AfiliacionBuilder
 * @description Builder que se encaraga de fabricar objetos a partir de entities
 *              y viceversa
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
			afiliacionDTO.setCreatedBy(afiliacion.getCreatedBy());
			afiliacionDTO.setCreatedDate(afiliacion.getCreatedDate());
			afiliacionDTO.setLastModifiedBy(afiliacion.getLastModifiedBy());
			afiliacionDTO.setLastModifiedDate(afiliacion.getLastModifiedDate());
			afiliacionDTO.setEstatus(afiliacion.getEstatus());
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
	public static Afiliacion buildAfiliacionFromAfiliacionDTO(AfiliacionDTO afiliacionDTO, String lastModifiedBy,
			Date lastModifiedDate) {
		Afiliacion afiliacion = null;
		if (null != afiliacionDTO) {
			afiliacion = new Afiliacion();
			afiliacion.setNumero(afiliacionDTO.getNumero());
			afiliacion.setId(afiliacionDTO.getId());
			afiliacion
					.setTipo(TipoAutorizacionBuilder.buildTipoAfilaicionFromTipoAfiliacionDTO(afiliacionDTO.getTipo()));
			afiliacion.setCreatedBy(afiliacionDTO.getCreatedBy());
			afiliacion.setCreatedDate(afiliacionDTO.getCreatedDate());
			afiliacion.setLastModifiedBy(
					null != afiliacionDTO.getLastModifiedBy() ? afiliacionDTO.getLastModifiedBy() : lastModifiedBy);
			afiliacion.setLastModifiedDate(
					null != afiliacionDTO.getLastModifiedDate() ? afiliacionDTO.getLastModifiedDate()
							: lastModifiedDate);
			afiliacion.setEstatus(null != afiliacionDTO.getEstatus() ? afiliacionDTO.getEstatus() : true);
			// Por default se agrega el tipo de autorizacion 1 ue es Ninguno
			TipoAutorizacion tipoAutorizacion = new TipoAutorizacion();
			tipoAutorizacion.setId(1);
			afiliacion.setTipo(tipoAutorizacion);
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
	public static Set<Afiliacion> buildAfiliacionSetFromAfiliacionDTOSet(Set<AfiliacionDTO> afiliacionDTOSet,
			String lastModifiedBy, Date lastModifiedDate) {
		Set<Afiliacion> afiliacionSet = null;
		if (null != afiliacionDTOSet && !afiliacionDTOSet.isEmpty()) {
			afiliacionSet = new TreeSet<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet) {
				afiliacionSet.add(buildAfiliacionFromAfiliacionDTO(afiliacionDTO, lastModifiedBy, lastModifiedDate));
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

	/**
	 * Construye una lista de objetos de tipo AfiliacionDTO a partir de una lista de
	 * objetos de tipo Afiliacion
	 * 
	 * @param afiliacionList
	 * @return
	 */
	public static List<AfiliacionDTO> buildAfiliacionDTOListFromAfiliacionList(List<Afiliacion> afiliacionList) {
		List<AfiliacionDTO> afiliacionDTOList = null;
		if (null != afiliacionList) {
			afiliacionDTOList = new ArrayList<>();
			for (Afiliacion afiliacion : afiliacionList) {
				afiliacionDTOList.add(buildAfiliacionDTOFromAfiliacion(afiliacion));
			}
		}
		return afiliacionDTOList;
	}

}
