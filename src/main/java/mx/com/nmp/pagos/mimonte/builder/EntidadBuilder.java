/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadReqDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * @name EntidadBuilder
 * @description Clase de capa builder que se encarga de convertir difrentes
 *              tipos de objetos y entidades relacionadas con el catalogo
 *              Entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:36 hrs.
 * @version 0.1
 */
public abstract class EntidadBuilder {

	private EntidadBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo EntidadDTO a aprtir de una entida de tipo Entidad
	 * 
	 * @param entidad
	 * @return
	 */
	public static EntidadDTO buildEntidadDTOFromEntidad(Entidad entidad) {
		EntidadDTO entidadDTO = null;
		if (null != entidad) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setContactos(ContactosBuilder.buildContactoReqDTOSetFromContactoSet(entidad.getContactos()));
			entidadDTO.setCreatedBy(entidad.getCreatedBy());
			entidadDTO.setCreatedDate(entidad.getCreatedDate());
			entidadDTO.setCuentas(CuentaBuilder.buildCuentaReqDTOSetFromCuentaSet(entidad.getCuentas()));
			entidadDTO.setDescription(entidad.getDescription());
			entidadDTO.setShortDescription(entidad.getShortDescription());
			entidadDTO.setId(entidad.getId());
			entidadDTO.setLastModifiedDate(entidad.getLastModifiedDate());
			entidadDTO.setNombre(entidad.getNombre());
			entidadDTO.setEstatus(entidad.getEstatus());
			entidadDTO.setLastModifiedBy(entidad.getLastModifiedBy());
			entidadDTO.setLastModifiedDate(entidad.getLastModifiedDate());
		}
		return entidadDTO;
	}

	/**
	 * Construye un a entidad de tipo Entidad a partir de un objeto de tipo
	 * EntidadDTO
	 * 
	 * @param entidadDTO
	 * @return
	 */
	public static Entidad buildEntidadFromEntidadDTO(EntidadDTO entidadDTO) {
		Entidad entidad = null;
		if (null != entidadDTO) {
			entidad = new Entidad();
			entidad.setContactos(ContactosBuilder.buildContactosSetFromContactoReqDTOSet(entidadDTO.getContactos(), entidadDTO.getLastModifiedBy(), entidadDTO.getLastModifiedDate()));
			entidad.setCreatedBy(entidadDTO.getCreatedBy());
			entidad.setCreatedDate(entidadDTO.getCreatedDate());
			entidad.setCuentas(CuentaBuilder.buildCuentaSetFromCuentaReqDTOSet(entidadDTO.getCuentas(), entidadDTO.getLastModifiedBy(), entidadDTO.getLastModifiedDate()));
			entidad.setDescription(entidadDTO.getDescription());
			entidad.setShortDescription(entidadDTO.getShortDescription());
			entidad.setId(entidadDTO.getId());
			entidad.setLastModifiedDate(entidadDTO.getLastModifiedDate());
			entidad.setLastModifiedBy(entidadDTO.getLastModifiedBy());
			entidad.setNombre(entidadDTO.getNombre());
			entidad.setEstatus(entidadDTO.getEstatus());
		}
		return entidad;
	}

	/**
	 * Construye un objeto de tipo EntidadResponseDTO a partir de una entidad de
	 * tipo Entidad
	 * 
	 * @param entidad
	 * @return
	 */
	public static EntidadResponseDTO buildEntidadResponseDTOFromEntidad(Entidad entidad) {
		EntidadResponseDTO entidadResponseDTO = null;
		if (null != entidad) {
			entidadResponseDTO = new EntidadResponseDTO();
			entidadResponseDTO
					.setContactos(ContactosBuilder.buildContactoEntDTOSetFromContactoSet(entidad.getContactos()));
			entidadResponseDTO.setCuentas(CuentaBuilder.buildCuentaEntDTOSetFromCuentaSet(entidad.getCuentas()));
			entidadResponseDTO.setDescripcion(entidad.getDescription());
			entidadResponseDTO.setId(entidad.getId());
			entidadResponseDTO.setNombre(entidad.getNombre());
			entidadResponseDTO.setEstatus(entidad.getEstatus());
			entidadResponseDTO.setCreadoPor(entidad.getCreatedBy());
			entidadResponseDTO.setFechaCreacion(entidad.getCreatedDate());
		}
		return entidadResponseDTO;
	}

	/**
	 * Construye un objeto de tipo EntidadResponseDTO a partir de un objeto de tipo
	 * EntidadDTO
	 * 
	 * @param entidadDTO
	 * @return
	 */
	public static EntidadResponseDTO buildEntidadResponseDTOFromEntidadDTO(EntidadDTO entidadDTO) {
		EntidadResponseDTO entidadResponseDTO = null;
		if (null != entidadDTO) {
			entidadResponseDTO = new EntidadResponseDTO();
			entidadResponseDTO.setContactos(null != entidadDTO.getContactos() && !entidadDTO.getContactos().isEmpty()
					? ContactosBuilder.buildContactoEntDTOSetFromContactoReqDTOSet(entidadDTO.getContactos())
					: new TreeSet<>());
			entidadResponseDTO.setCuentas(null != entidadDTO.getCuentas() && !entidadDTO.getCuentas().isEmpty()
					? CuentaBuilder.buildCuentaEntDTOSetFromCuentaReqDTOSet(entidadDTO.getCuentas())
					: new TreeSet<>());
			entidadResponseDTO.setDescripcion(entidadDTO.getDescription());
			entidadResponseDTO.setId(entidadDTO.getId());
			entidadResponseDTO.setNombre(entidadDTO.getNombre());
			entidadResponseDTO.setEstatus(entidadDTO.getEstatus());
			entidadResponseDTO.setFechaCreacion(entidadDTO.getCreatedDate());
			entidadResponseDTO.setCreadoPor(entidadDTO.getCreatedBy());
		}
		return entidadResponseDTO;
	}

	/**
	 * Construye un objeto de tipo EntidadDTO a partir de un objeto de tipo
	 * EntidadBaseDTO
	 * 
	 * @param entidadBaseDTO
	 * @return
	 */
	public static EntidadDTO buildEntidadDTOFromEntidadBaseDTO(EntidadBaseDTO entidadBaseDTO, Date createdDate,
			Date lastModifiedDate) {
		EntidadDTO entidadDTO = null;
		if (null != entidadBaseDTO) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setContactos(entidadBaseDTO.getContactos());
			entidadDTO.setCreatedDate(createdDate);
			entidadDTO.setCuentas(entidadBaseDTO.getCuentas());
			entidadDTO.setDescription(entidadBaseDTO.getDescripcion());
			entidadDTO.setEstatus(true);
			entidadDTO.setId(entidadBaseDTO.getId());
			entidadDTO.setLastModifiedDate(lastModifiedDate);
			entidadDTO.setNombre(entidadBaseDTO.getNombre());
		}
		return entidadDTO;
	}

	/**
	 * Construye un objeto de tipo EntidadDTO a partir de un objeto de tipo
	 * BaseEntidadDTO
	 * 
	 * @param baseEntidadDTO
	 * @return
	 */
	public static EntidadDTO buildEntidadDTOFromBaseEntidadDTO(BaseEntidadDTO baseEntidadDTO) {
		EntidadDTO entidadDTO = null;
		if (null != baseEntidadDTO) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setContactos(null);
			entidadDTO.setCreatedBy(null);
			entidadDTO.setCreatedDate(null);
			entidadDTO.setCuentas(null);
			entidadDTO.setDescription(baseEntidadDTO.getDescripcion());
			entidadDTO.setEstatus(null);
			entidadDTO.setId(baseEntidadDTO.getId());
			entidadDTO.setLastModifiedBy(null);
			entidadDTO.setLastModifiedDate(null);
			entidadDTO.setNombre(baseEntidadDTO.getNombre());
			entidadDTO.setShortDescription(null);
		}
		return entidadDTO;
	}

	/**
	 * Construye un objeto de tipo EntidadDTO a partir de un objeto de tipo
	 * EntidadReqDTO
	 * 
	 * @param entidadReqDTO
	 * @return
	 */
	public static EntidadDTO buildEntidadDTOFromEntidadReqDTO(EntidadReqDTO entidadReqDTO) {
		EntidadDTO entidadDTO = null;
		if (null != entidadReqDTO) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setId(entidadReqDTO.getId());
		}
		return entidadDTO;
	}

	/**
	 * Construye un objeto de tipo BaseEntidadDTO a partir de un objeto de tipo
	 * EntidadDTO
	 * 
	 * @param entidadDTO
	 * @return
	 */
	public static BaseEntidadDTO buildBaseentidadFromEntidadDTO(EntidadDTO entidadDTO) {
		BaseEntidadDTO baseEntidadDTO = null;
		if (null != entidadDTO) {
			baseEntidadDTO = new BaseEntidadDTO();
			baseEntidadDTO.setDescripcion(entidadDTO.getDescription());
			baseEntidadDTO.setId(entidadDTO.getId());
			baseEntidadDTO.setNombre(entidadDTO.getNombre());
		}
		return baseEntidadDTO;
	}

	/**
	 * Construye un objeto de tipo BaseEntidadDTO a partir de un objeto de tipo
	 * Entidad
	 * 
	 * @param entidad
	 * @return
	 */
	public static BaseEntidadDTO buildBaseentidadFromEntidad(Entidad entidad) {
		BaseEntidadDTO baseEntidadDTO = null;
		if (null != entidad) {
			baseEntidadDTO = new BaseEntidadDTO();
			baseEntidadDTO.setDescripcion(entidad.getDescription());
			baseEntidadDTO.setId(entidad.getId());
			baseEntidadDTO.setNombre(entidad.getNombre());
		}
		return baseEntidadDTO;
	}

	/**
	 * Construye una lista de objetos de tipo EntidadDTO a partir de una lista de
	 * entities de tipo Entidad
	 * 
	 * @param entidadList
	 * @return
	 */
	public static List<EntidadDTO> buildEntidadDTOListFromEntidadList(List<Entidad> entidadList) {
		List<EntidadDTO> enitdadDTOList = null;
		if (null != entidadList) {
			enitdadDTOList = new ArrayList<>();
			for (Entidad entidad : entidadList) {
				enitdadDTOList.add(buildEntidadDTOFromEntidad(entidad));
			}
		}
		return enitdadDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo EntidadResponseDTO a partir de una
	 * lista de objetos de tipo Entidad
	 * 
	 * @param entidadList
	 * @return
	 */
	public static List<EntidadResponseDTO> buildEntidadResponseDTOListFromEntidadList(List<Entidad> entidadList) {
		List<EntidadResponseDTO> enitdadResponseDTOList = null;
		if (null != entidadList) {
			enitdadResponseDTOList = new ArrayList<>();
			for (Entidad entidad : entidadList) {
				enitdadResponseDTOList.add(buildEntidadResponseDTOFromEntidad(entidad));
			}
		}
		return enitdadResponseDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo EntidadResponseDTO a partir de una
	 * lista de objetos de tipo EntidadDTO
	 * 
	 * @param entidadDTOList
	 * @return
	 */
	public static List<EntidadResponseDTO> buildEntidadResponseDTOListFromEntidadDTOList(
			List<EntidadDTO> entidadDTOList) {
		List<EntidadResponseDTO> entidadResponseDTOList = null;
		if (null != entidadDTOList) {
			entidadResponseDTOList = new ArrayList<>();
			for (EntidadDTO entidadDTO : entidadDTOList) {
				entidadResponseDTOList.add(buildEntidadResponseDTOFromEntidadDTO(entidadDTO));
			}
		}
		return entidadResponseDTOList;
	}

}
