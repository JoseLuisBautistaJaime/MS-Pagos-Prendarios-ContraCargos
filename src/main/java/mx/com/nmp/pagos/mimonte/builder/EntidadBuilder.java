package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadReqDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * Nombre: EntidadBuilder Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con el
 * catalogo Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
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
			entidadDTO.setContactos(null);
			entidadDTO.setCreatedBy(entidad.getCreatedBy());
			entidadDTO.setCreatedDate(entidad.getCreatedDate());
			entidadDTO.setCuentas(null);
			entidadDTO.setDescription(entidad.getDescription());
			entidadDTO.setShortDescription(entidad.getShortDescription());
			entidadDTO.setId(entidad.getId());
			entidadDTO.setLastModifiedDate(entidad.getLastModifiedDate());
			entidadDTO.setNombre(entidad.getNombre());
			entidadDTO.setEstatus(entidad.getEstatus());
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
			entidad.setContactos(null);
			entidad.setCreatedBy(entidadDTO.getCreatedBy());
			entidad.setCreatedDate(entidadDTO.getCreatedDate());
			entidad.setCuentas(null);
			entidad.setDescription(entidadDTO.getDescription());
			entidad.setShortDescription(entidadDTO.getShortDescription());
			entidad.setId(entidadDTO.getId());
			entidad.setLastModifiedDate(entidadDTO.getLastModifiedDate());
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
			entidadResponseDTO.setContactos(null);
			entidadResponseDTO.setCuentas(null);
			entidadResponseDTO.setDescripcion(entidad.getDescription());
			entidadResponseDTO.setId(entidad.getId());
			entidadResponseDTO.setNombre(entidad.getNombre());
			entidadResponseDTO.setEstatus(entidad.getEstatus());
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
			entidadResponseDTO.setContactos(null);
			entidadResponseDTO.setCuentas(null);
			entidadResponseDTO.setDescripcion(entidadDTO.getDescription());
			entidadResponseDTO.setId(entidadDTO.getId());
			entidadResponseDTO.setNombre(entidadDTO.getNombre());
			entidadResponseDTO.setEstatus(entidadDTO.getEstatus());
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
	public static EntidadDTO buildEntidadDTOFromEntidadBaseDTO(EntidadBaseDTO entidadBaseDTO) {
		EntidadDTO entidadDTO = null;
		if (null != entidadBaseDTO) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setContactos(entidadBaseDTO.getContactos());
			entidadDTO.setCreatedBy(null);
			entidadDTO.setCreatedDate(null);
			entidadDTO.setCuentas(entidadBaseDTO.getCuentas());
			entidadDTO.setDescription(entidadBaseDTO.getDescripcion());
			entidadDTO.setEstatus(null);
			entidadDTO.setId(entidadBaseDTO.getId());
			entidadDTO.setLastModifiedBy(null);
			entidadDTO.setLastModifiedDate(null);
			entidadDTO.setNombre(entidadBaseDTO.getNombre());
			entidadDTO.setShortDescription(null);
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

}
