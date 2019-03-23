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
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespPostDTO;
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
	public static Afiliacion buildAfiliacionFromAfiliacionDTO(AfiliacionDTO afiliacionDTO) {
		Afiliacion afiliacion = null;
		if (null != afiliacionDTO) {
			afiliacion = new Afiliacion();
			afiliacion.setNumero(afiliacionDTO.getNumero());
			afiliacion.setId(afiliacionDTO.getId());
			afiliacion
					.setTipo(TipoAutorizacionBuilder.buildTipoAfilaicionFromTipoAfiliacionDTO(afiliacionDTO.getTipo()));
			afiliacion.setCreatedBy(afiliacionDTO.getCreatedBy());
			afiliacion.setCreatedDate(afiliacionDTO.getCreatedDate());
			afiliacion.setLastModifiedBy(afiliacionDTO.getLastModifiedBy());
			afiliacion.setLastModifiedDate(afiliacionDTO.getLastModifiedDate());
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

	/**
	 * Construye una lista de objetos de tipo AfiliacionRespDTO a partir de un set
	 * de entities de tipo Afiliacion
	 * 
	 * @param afiliacionSet
	 * @return
	 */
	public static List<AfiliacionRespDTO> buildAfiliacionRespDTOListFromAfiliacionSet(Set<Afiliacion> afiliacionSet) {
		List<AfiliacionRespDTO> afiliacionRespDTOList = null;
		if (null != afiliacionSet) {
			afiliacionRespDTOList = new ArrayList<>();
			for (Afiliacion afiliacion : afiliacionSet)
				afiliacionRespDTOList.add(new AfiliacionRespDTO(afiliacion.getId(), afiliacion.getNumero()));
		}
		return afiliacionRespDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionRespDTO a partir de una
	 * lista de objetos de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOSet
	 * @return
	 */
	public static List<AfiliacionRespDTO> buildAfiliacionRespDTOListFromAfiliacionDTOSet(
			Set<AfiliacionDTO> afiliacionDTOSet) {
		List<AfiliacionRespDTO> afiliacionRespDTOList = null;
		if (null != afiliacionDTOSet) {
			afiliacionRespDTOList = new ArrayList<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet)
				afiliacionRespDTOList.add(new AfiliacionRespDTO(afiliacionDTO.getId(), afiliacionDTO.getNumero()));
		}
		return afiliacionRespDTOList;
	}

	/**
	 * Construye un set de objetos de tipo AfiliacionDTO a partir de una lista de
	 * objetos de tipo AfiliacionRespDTO
	 * 
	 * @param afiliacionDTOList
	 * @return
	 */
	public static Set<AfiliacionDTO> buildAfiliacionRespDTOListFromAfiliacionDTOSet(
			List<AfiliacionRespDTO> afiliacionDTOList) {
		Set<AfiliacionDTO> afiliacionRespDTOSet = null;
		if (null != afiliacionDTOList) {
			afiliacionRespDTOSet = new TreeSet<>();
			for (AfiliacionRespDTO AfiliacionRespDTO : afiliacionDTOList)
				afiliacionRespDTOSet.add(new AfiliacionDTO(AfiliacionRespDTO.getId(), null));
		}
		return afiliacionRespDTOSet;
	}

	/**
	 * Construye un objeto de tipo AfiliacionDTO a partir de un objeto de tipo
	 * AfiliacionReqDTO
	 * 
	 * @param afiliacionReqDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static AfiliacionDTO buildafiliacionDTOFromAfiliacionReqDTO(AfiliacionReqDTO afiliacionReqDTO,
			Date createdDate, Date lastModifiedDate) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacionReqDTO) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setEstatus(afiliacionReqDTO.getEstatus());
			afiliacionDTO.setId(afiliacionReqDTO.getId());
			afiliacionDTO.setNumero(afiliacionReqDTO.getNumero());
			afiliacionDTO.setCreatedDate(createdDate);
			afiliacionDTO.setLastModifiedDate(lastModifiedDate);
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionRespPostDTO a partir de un objeto de
	 * tipo AfiliacionDTO
	 * 
	 * @param afiliacionRespPostDTO
	 * @return
	 */
	public static AfiliacionRespPostDTO buildAfiliacionRespPostDTOfromAfiliacionDTO(AfiliacionDTO afiliacionDTO) {
		AfiliacionRespPostDTO afiliacionRespPostDTO = null;
		if (null != afiliacionDTO) {
			afiliacionRespPostDTO = new AfiliacionRespPostDTO();
			afiliacionRespPostDTO.setEstatus(afiliacionDTO.getEstatus());
			afiliacionRespPostDTO.setId(afiliacionDTO.getId());
			afiliacionRespPostDTO.setNumero(afiliacionDTO.getNumero());
		}
		return afiliacionRespPostDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionRespPostDTO a partir de una
	 * lista de objetos de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOList
	 * @return
	 */
	public static List<AfiliacionRespPostDTO> buildAfiliacionRespPostDTOListfromAfiliacionDTOList(
			List<AfiliacionDTO> afiliacionDTOList) {
		List<AfiliacionRespPostDTO> afiliacionRespPostDTOList = null;
		if (null != afiliacionDTOList && !afiliacionDTOList.isEmpty()) {
			afiliacionRespPostDTOList = new ArrayList<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOList) {
				afiliacionRespPostDTOList.add(buildAfiliacionRespPostDTOfromAfiliacionDTO(afiliacionDTO));
			}
		}
		return afiliacionRespPostDTOList;
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
