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
import mx.com.nmp.pagos.mimonte.dto.AfiliacionEntDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqNN;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespPostDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionSaveDTO;
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
	 * Construye una lista de objetos de tipo AfiliacionRespDTO a partir de una
	 * lista de objetos de tipo AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTOList
	 * @return
	 */
	public static List<AfiliacionRespDTO> buildAfiliacionRespDTOListFromAfiliacionSaveDTOList(
			List<AfiliacionSaveDTO> afiliacionSaveDTOList) {
		List<AfiliacionRespDTO> afiliacionRespDTOList = null;
		if (null != afiliacionSaveDTOList) {
			afiliacionRespDTOList = new ArrayList<>();
			for (AfiliacionSaveDTO afiliacionSaveDTO : afiliacionSaveDTOList)
				afiliacionRespDTOList.add(new AfiliacionRespDTO(afiliacionSaveDTO.getId()));
		}
		return afiliacionRespDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionRespDTO a partir de una
	 * lista de objetos de tipo AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTOSet
	 * @return
	 */
	public static List<AfiliacionRespDTO> buildAfiliacionRespDTOListFromAfiliacionSaveDTOSet(
			Set<AfiliacionSaveDTO> afiliacionSaveDTOSet) {
		List<AfiliacionRespDTO> afiliacionRespDTOList = null;
		if (null != afiliacionSaveDTOSet) {
			afiliacionRespDTOList = new ArrayList<>();
			for (AfiliacionSaveDTO afiliacionSaveDTO : afiliacionSaveDTOSet)
				afiliacionRespDTOList.add(new AfiliacionRespDTO(afiliacionSaveDTO.getId()));
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
			for (AfiliacionRespDTO afiliacionRespDTO : afiliacionDTOList) {
				AfiliacionDTO afiliacionDTO = new AfiliacionDTO();
				afiliacionDTO.setId(afiliacionRespDTO.getId());
				afiliacionDTO.setNumero(afiliacionRespDTO.getNumero());
				afiliacionRespDTOSet.add(afiliacionDTO);
			}
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
	public static AfiliacionDTO buildAfiliacionDTOFromAfiliacionReqDTO(AfiliacionReqDTO afiliacionReqDTO,
			Date createdDate, Date lastModifiedDate) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacionReqDTO) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setEstatus(true);
			afiliacionDTO.setId(afiliacionReqDTO.getId());
			afiliacionDTO.setNumero(afiliacionReqDTO.getNumero());
			afiliacionDTO.setCreatedDate(createdDate);
			afiliacionDTO.setLastModifiedDate(lastModifiedDate);
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionDTO a partir de un objeto de tipo
	 * AfiliacionReqSaveDTO
	 * 
	 * @param afiliacionReqSaveDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static AfiliacionDTO buildAfiliacionDTOFromAfiliacionSaveReqDTO(AfiliacionReqSaveDTO afiliacionReqSaveDTO,
			Date createdDate, Date lastModifiedDate) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacionReqSaveDTO) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setNumero(afiliacionReqSaveDTO.getNumero());
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

	/**
	 * Construye un objeto de tipo AfiliacionEntDTO a partir de un entity de tipo
	 * Afiliacion
	 * 
	 * @param afiliacion
	 * @return
	 */
	public static AfiliacionEntDTO buildAfiliacionEntDTOFromAfiliacion(Afiliacion afiliacion) {
		AfiliacionEntDTO afiliacionEntDTO = null;
		if (null != afiliacion) {
			afiliacionEntDTO = new AfiliacionEntDTO();
			afiliacionEntDTO.setId(afiliacion.getId());
			afiliacionEntDTO.setNumero(afiliacion.getNumero());
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye un Set de objetos de tipo AfiliacionEntDTO a partir de un Set de
	 * entities de tipo Afiliacion
	 * 
	 * @param afiliacionSet
	 * @return
	 */
	public static List<AfiliacionEntDTO> buildAfiliacionEntDTOListFromAfiliacionSet(Set<Afiliacion> afiliacionSet) {
		List<AfiliacionEntDTO> afiliacionEntDTO = null;
		if (null != afiliacionSet) {
			afiliacionEntDTO = new ArrayList<>();
			for (Afiliacion afiliacion : afiliacionSet) {
				afiliacionEntDTO.add(buildAfiliacionEntDTOFromAfiliacion(afiliacion));
			}
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionEntDTOa partir de un objeto de tipo
	 * AfiliacionReqDTO
	 * 
	 * @param afiliacionReqDTO
	 * @return
	 */
	public static AfiliacionEntDTO buildAfiliacionEntDTOFromAfiliacionReqDTO(AfiliacionReqDTO afiliacionReqDTO) {
		AfiliacionEntDTO afiliacionEntDTO = null;
		if (null != afiliacionReqDTO) {
			afiliacionEntDTO = new AfiliacionEntDTO();
			afiliacionEntDTO.setId(afiliacionReqDTO.getId());
			afiliacionEntDTO.setNumero(afiliacionReqDTO.getNumero());
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionEntDTO a partir de una lista
	 * de objetos de tipo AfiliacionReqDTO
	 * 
	 * @param afiliacionReqDTOList
	 * @return
	 */
	public static List<AfiliacionEntDTO> buildAfiliacionEntDTOListFromAfiliacionReqDTOList(
			List<AfiliacionReqDTO> afiliacionReqDTOList) {
		List<AfiliacionEntDTO> afiliacionEntDTOList = null;
		if (null != afiliacionReqDTOList) {
			afiliacionEntDTOList = new ArrayList<>();
			for (AfiliacionReqDTO afiliacionReqDTO : afiliacionReqDTOList) {
				afiliacionEntDTOList.add(buildAfiliacionEntDTOFromAfiliacionReqDTO(afiliacionReqDTO));
			}
		}
		return afiliacionEntDTOList;
	}

	/**
	 * Construye un objeto de tipo AfiliacionReqDTO a partir de un entity de tipo
	 * Afiliacion
	 * 
	 * @param afiliacion
	 * @return
	 */
	public static AfiliacionReqDTO buildAfiliacionReqDTOFromAfiliacion(Afiliacion afiliacion) {
		AfiliacionReqDTO afiliacionReqDTO = null;
		if (null != afiliacion) {
			afiliacionReqDTO = new AfiliacionReqDTO();
			afiliacionReqDTO.setId(afiliacion.getId());
			afiliacionReqDTO.setNumero(afiliacion.getNumero());
		}
		return afiliacionReqDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionReqDTO a partir de un Set de
	 * entities de tipo Afiliacion
	 * 
	 * @param afiliacionList
	 * @return
	 */
	public static List<AfiliacionReqDTO> buildAfiliacionReqDTOListFromAfiliacionSet(Set<Afiliacion> afiliacionList) {
		List<AfiliacionReqDTO> afiliacionReqDTO = null;
		if (null != afiliacionList) {
			afiliacionReqDTO = new ArrayList<>();
			for (Afiliacion afiliacion : afiliacionList) {
				afiliacionReqDTO.add(buildAfiliacionReqDTOFromAfiliacion(afiliacion));
			}
		}
		return afiliacionReqDTO;
	}

	/**
	 * Construye un entity de tipo Afiliacion a partir de un objeto de tipo
	 * AfiliacionReqDTO
	 * 
	 * @param afiliacionReqDTO
	 * @return
	 */
	public static Afiliacion buildAfiliacionFromAfiliacionReqDTO(AfiliacionReqDTO afiliacionReqDTO,
			String lastModifiedBy, Date lastModifiedDate) {
		Afiliacion afiliacion = null;
		if (null != afiliacionReqDTO) {
			afiliacion = new Afiliacion();
			afiliacion.setEstatus(true);
			afiliacion.setId(afiliacionReqDTO.getId());
			afiliacion.setNumero(afiliacionReqDTO.getNumero());
			afiliacion.setLastModifiedBy(lastModifiedBy);
			afiliacion.setLastModifiedDate(lastModifiedDate);
			// Aqui se agrega or default 1 que significa ningun tipo de autoirzacion
			TipoAutorizacion auth = new TipoAutorizacion();
			auth.setId(1);
			afiliacion.setTipo(auth);
		}
		return afiliacion;
	}

	/**
	 * Construye Set de entities de tipo Afiliacion a partir de una lista de objetos
	 * de tipo AfiliacionReqDTO
	 * 
	 * @param afiliacionReqDTOList
	 * @return
	 */
	public static Set<Afiliacion> buildAfiliacionSetFromAfiliacionReqDTOList(
			List<AfiliacionReqDTO> afiliacionReqDTOList, String lastModifiedBy, Date lastModifiedDate) {
		Set<Afiliacion> afiliacion = null;
		if (null != afiliacionReqDTOList && !afiliacionReqDTOList.isEmpty()) {
			afiliacion = new TreeSet<>();
			for (AfiliacionReqDTO afiliacionReqDTO : afiliacionReqDTOList) {
				afiliacion.add(buildAfiliacionFromAfiliacionReqDTO(afiliacionReqDTO, lastModifiedBy, lastModifiedDate));
			}
		}
		return afiliacion;
	}

	/**
	 * Construye un objeto de tipo AfiliacionEntDTO a partir de un pbjeto de tipo
	 * AfiliacionRespDTO
	 * 
	 * @param afiliacionRespDTO
	 * @return
	 */
	public static AfiliacionEntDTO buildAfiliacionEntDTOFromAfiliacionRespDTO(AfiliacionRespDTO afiliacionRespDTO) {
		AfiliacionEntDTO afiliacionEntDTO = null;
		if (null != afiliacionRespDTO) {
			afiliacionEntDTO = new AfiliacionEntDTO();
			afiliacionEntDTO.setId(afiliacionRespDTO.getId());
			afiliacionEntDTO.setNumero(afiliacionRespDTO.getNumero());
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionEntDTO a partir de una lista
	 * de objetos de tipo AfiliacionRespDTO
	 * 
	 * @param afiliacionRespDTOList
	 * @return
	 */
	public static List<AfiliacionEntDTO> buildAfiliacionEntDTOListFromAfiliacionRespDTOList(
			List<AfiliacionRespDTO> afiliacionRespDTOList) {
		List<AfiliacionEntDTO> afiliacionEntDTO = null;
		if (null != afiliacionRespDTOList) {
			afiliacionEntDTO = new ArrayList<>();
			for (AfiliacionRespDTO afiliacionRespDTO : afiliacionRespDTOList) {
				afiliacionEntDTO.add(buildAfiliacionEntDTOFromAfiliacionRespDTO(afiliacionRespDTO));
			}
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionEntDTO a partir de un objeto de tipo
	 * AfiliacionDTO
	 * 
	 * @param afiliacionDTO
	 * @return
	 */
	public static AfiliacionEntDTO buildAfiliacionEntDTOFromAfiliacionDTO(AfiliacionDTO afiliacionDTO) {
		AfiliacionEntDTO afiliacionEntDTO = null;
		if (null != afiliacionDTO) {
			afiliacionEntDTO = new AfiliacionEntDTO();
			afiliacionEntDTO.setId(afiliacionDTO.getId());
			afiliacionEntDTO.setNumero(afiliacionDTO.getNumero());
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionEntDTO a partir de un Set de
	 * objetos de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOSet
	 * @return
	 */
	public static List<AfiliacionEntDTO> buildAfiliacionEntDTOListFromAfiliacionDTOSet(
			Set<AfiliacionDTO> afiliacionDTOSet) {
		List<AfiliacionEntDTO> afiliacionEntDTOSet = null;
		if (null != afiliacionDTOSet) {
			afiliacionEntDTOSet = new ArrayList<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet) {
				afiliacionEntDTOSet.add(buildAfiliacionEntDTOFromAfiliacionDTO(afiliacionDTO));
			}
		}
		return afiliacionEntDTOSet;
	}

	/**
	 * Construye un Set de objetos de tipo AfiliacionRespPostDTO a partir de un Set
	 * de objetos de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOSet
	 * @return
	 */
	public static Set<AfiliacionRespPostDTO> buildAfiliacionRespPostDTOSetfromAfiliacionDTOSet(
			Set<AfiliacionDTO> afiliacionDTOSet) {
		Set<AfiliacionRespPostDTO> afiliacionRespPostDTO = null;
		if (null != afiliacionDTOSet) {
			afiliacionRespPostDTO = new TreeSet<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet) {
				afiliacionRespPostDTO.add(buildAfiliacionRespPostDTOfromAfiliacionDTO(afiliacionDTO));
			}
		}
		return afiliacionRespPostDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionDTO a partir de un objeto de tipo
	 * AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTO
	 * @return
	 */
	public static AfiliacionDTO buildAfiliacionDTOFromAfiliacionSaveDTO(AfiliacionSaveDTO afiliacionSaveDTO) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacionSaveDTO) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setId(afiliacionSaveDTO.getId());
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un Set de objetos de tipo AfiliacionDTO a partir de una lista de
	 * objetos de tipo AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTOList
	 * @return
	 */
	public static Set<AfiliacionDTO> buildAfiliacionDTOSetFromAfiliacionSaveDTOList(
			List<AfiliacionSaveDTO> afiliacionSaveDTOList) {
		Set<AfiliacionDTO> afiliacionDTOSet = null;
		if (null != afiliacionSaveDTOList && !afiliacionSaveDTOList.isEmpty()) {
			afiliacionDTOSet = new TreeSet<>();
			for (AfiliacionSaveDTO afiliacionSaveDTO : afiliacionSaveDTOList) {
				afiliacionDTOSet.add(buildAfiliacionDTOFromAfiliacionSaveDTO(afiliacionSaveDTO));
			}
		}
		return afiliacionDTOSet;
	}

	/**
	 * Construye un obejeto de tipo AfiliacionReqDTO a partir de un objeto de tipo
	 * AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTO
	 * @return
	 */
	public static AfiliacionReqDTO buildAfiliacionReqDTOFromAfiliacionSaveDTO(AfiliacionSaveDTO afiliacionSaveDTO) {
		AfiliacionReqDTO afiliacionReqDTO = null;
		if (null != afiliacionSaveDTO) {
			afiliacionReqDTO = new AfiliacionReqDTO();
			afiliacionReqDTO.setId(afiliacionSaveDTO.getId());
		}
		return afiliacionReqDTO;
	}

	/**
	 * Construye un Set de objetos de tipo AfiliacionReqDTO a partir de un Set de
	 * objetos de tipo AfiliacionSaveDTO
	 * 
	 * @param afiliacionSaveDTOList
	 * @return
	 */
	public static List<AfiliacionReqDTO> buildAfiliacionReqDTOListFromAfiliacionSaveDTOList(
			List<AfiliacionSaveDTO> afiliacionSaveDTOList) {
		List<AfiliacionReqDTO> afiliacionReqDTOList = null;
		if (null != afiliacionSaveDTOList && !afiliacionSaveDTOList.isEmpty()) {
			afiliacionReqDTOList = new ArrayList<>();
			for (AfiliacionSaveDTO afiliacionSaveDTO : afiliacionSaveDTOList) {
				afiliacionReqDTOList.add(buildAfiliacionReqDTOFromAfiliacionSaveDTO(afiliacionSaveDTO));
			}
		}
		return afiliacionReqDTOList;
	}

	/**
	 * Construye un objeto de tipo AfiliacionDTO a partir de un objeto de tipo
	 * AfiliacionDTO
	 * 
	 * @param afiliacionDTO
	 * @return
	 */
	public static AfiliacionReqNN buildAfiliacionReqNNFromAfiliacionDTO(AfiliacionDTO afiliacionDTO) {
		AfiliacionReqNN afiliacionReqNN = null;
		if (null != afiliacionDTO) {
			afiliacionReqNN = new AfiliacionReqNN();
			afiliacionReqNN.setId(afiliacionReqNN.getId());
		}
		return afiliacionReqNN;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionDTO a partir de un Set de
	 * objetos de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOSet
	 * @return
	 */
	public static List<AfiliacionReqNN> buildAfiliacionReqNNListFromAfiliacionDTOSet(
			Set<AfiliacionDTO> afiliacionDTOSet) {
		List<AfiliacionReqNN> afiliacionReqNNList = null;
		if (null != afiliacionDTOSet) {
			afiliacionReqNNList = new ArrayList<>();
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOSet) {
				afiliacionReqNNList.add(buildAfiliacionReqNNFromAfiliacionDTO(afiliacionDTO));
			}
		}
		return afiliacionReqNNList;
	}

	/**
	 * Construye un objeto de tipo AfiliacionDTO a partir de un objeto de tipo
	 * AfiliacionReqNN
	 * 
	 * @param afiliacionReqNN
	 * @return
	 */
	public static AfiliacionDTO buildAfiliacionDTOFromAfiliacionReqNN(AfiliacionReqNN afiliacionReqNN) {
		AfiliacionDTO afiliacionDTO = null;
		if (null != afiliacionReqNN) {
			afiliacionDTO = new AfiliacionDTO();
			afiliacionDTO.setId(afiliacionReqNN.getId());
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un Set de objetos de tipo AfiliacionDTO a partir de una lista de
	 * objetos de tipo AfiliacionReqNN
	 * 
	 * @param afiliacionReqNNList
	 * @return
	 */
	public static Set<AfiliacionDTO> buildAfiliacionDTOSetFromAfiliacionReqNNList(
			List<AfiliacionReqNN> afiliacionReqNNList) {
		Set<AfiliacionDTO> afiliacionDTO = null;
		if (null != afiliacionReqNNList) {
			afiliacionDTO = new TreeSet<>();
			for (AfiliacionReqNN afiliacionReqNN : afiliacionReqNNList) {
				afiliacionDTO.add(buildAfiliacionDTOFromAfiliacionReqNN(afiliacionReqNN));
			}
		}
		return afiliacionDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionReqNN a partir de un objeto de tipo
	 * Afiliacion
	 * 
	 * @param afiliacion
	 * @return
	 */
	public static AfiliacionReqNN buildAfiliacionReqNNFromAfiliacion(Afiliacion afiliacion) {
		AfiliacionReqNN afiliacionReqNN = null;
		if (null != afiliacion) {
			afiliacionReqNN = new AfiliacionReqNN();
			afiliacionReqNN.setId(afiliacion.getId());
		}
		return afiliacionReqNN;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionReqNN a partir de un Set de
	 * objetos de tipo Afiliacion
	 * 
	 * @param afiliacionSet
	 * @return
	 */
	public static List<AfiliacionReqNN> buildAfiliacionReqNNListFromAfiliacionSet(Set<Afiliacion> afiliacionSet) {
		List<AfiliacionReqNN> afiliacionReqNNDTO = null;
		if (null != afiliacionSet) {
			afiliacionReqNNDTO = new ArrayList<>();
			for (Afiliacion afiliacion : afiliacionSet) {
				afiliacionReqNNDTO.add(buildAfiliacionReqNNFromAfiliacion(afiliacion));
			}
		}
		return afiliacionReqNNDTO;
	}

	/**
	 * Construye un objeto de tipo AfiliacionEntDTO a partir de un objeto de tipo
	 * AfiliacionReqNN
	 * 
	 * @param afiliacionReqNN
	 * @return
	 */
	public static AfiliacionEntDTO buildAfiliacionEntDTOFromAfiliacionReqNN(AfiliacionReqNN afiliacionReqNN) {
		AfiliacionEntDTO afiliacionEntDTO = null;
		if (null != afiliacionReqNN) {
			afiliacionEntDTO = new AfiliacionEntDTO();
			afiliacionEntDTO.setId(afiliacionReqNN.getId());
		}
		return afiliacionEntDTO;
	}

	/**
	 * Construye una lista de objetos de tipo AfiliacionEntDTO a partir de una lista
	 * de objetos de tipo AfiliacionReqNN
	 * 
	 * @param afiliacionReqNNList
	 * @return
	 */
	public static List<AfiliacionEntDTO> buildAfiliacionEntDTOListFromAfiliacionReqNNList(
			List<AfiliacionReqNN> afiliacionReqNNList) {
		List<AfiliacionEntDTO> afiliacionEntDTOList = null;
		if (null != afiliacionReqNNList) {
			afiliacionEntDTOList = new ArrayList<>();
			for (AfiliacionReqNN afiliacionReqNN : afiliacionReqNNList) {
				afiliacionEntDTOList.add(buildAfiliacionEntDTOFromAfiliacionReqNN(afiliacionReqNN));
			}
		}
		return afiliacionEntDTOList;
	}

}
