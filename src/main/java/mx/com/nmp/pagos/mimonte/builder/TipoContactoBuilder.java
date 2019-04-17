/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.TipoContactoDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoContactoReqDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoContactoRespDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoContactoResponseDTO;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;

/**
 * Nombre: TipoContactoBuilder Descripcion: clase de capa de builder que se
 * encarga de convertir difrentes tipos de objetos y entidades relacionadas con
 * el catalogo TipoContacto
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:16 Hrs.
 * @version 0.1
 */
public abstract class TipoContactoBuilder {

	/**
	 * 
	 */
	private TipoContactoBuilder() {
		super();
	}

	/**
	 * Construye una entidad de tipo TipoContacto a partir de un objeto de tipo
	 * TipoContactoDTO
	 * 
	 * @param tipoContactoDTO
	 * @return tipoContacto
	 */
	public static TipoContacto builTipoContactoFromContactoBaseDTO(TipoContactoDTO tipoContactoDTO) {
		TipoContacto tipoContacto = null;
		if (tipoContactoDTO != null) {
			tipoContacto = new TipoContacto();
			tipoContacto.setId(tipoContactoDTO.getId());
			tipoContacto.setDescription(tipoContactoDTO.getDescription());
			tipoContacto.setShortDescription(tipoContactoDTO.getShortDescription());
			tipoContacto.setEstatus(tipoContactoDTO.getEstatus());
			tipoContacto.setCreatedBy(tipoContactoDTO.getCreatedBy());
			tipoContacto.setCreatedDate(tipoContactoDTO.getCreatedDate());
			tipoContacto.setLastModifiedBy(tipoContactoDTO.getLastModifiedBy());
		}
		return tipoContacto;
	}

	/**
	 * Construye un objeto de tipo TipoContactoDTO a partir de una entidad de tipo
	 * TipoContacto
	 * 
	 * @param tipoContacto
	 * @return tipoContactoDTO
	 */
	public static TipoContactoDTO builTipoContactoBaseDTOFromContacto(TipoContacto tipoContacto) {
		TipoContactoDTO tipoContactoDTO = null;
		if (tipoContacto != null) {
			tipoContactoDTO = new TipoContactoDTO();
			tipoContactoDTO.setId(tipoContacto.getId());
			tipoContactoDTO.setDescription(tipoContacto.getDescription());
			tipoContactoDTO.setShortDescription(tipoContacto.getShortDescription());
			tipoContactoDTO.setEstatus(tipoContacto.getEstatus());
			tipoContactoDTO.setCreatedBy(tipoContacto.getCreatedBy());
			tipoContactoDTO.setCreatedDate(tipoContacto.getCreatedDate());
			tipoContactoDTO.setLastModifiedBy(tipoContacto.getLastModifiedBy());
		}
		return tipoContactoDTO;
	}

	/**
	 * Construye un objeto de tipo TipoContactoDTO a partir de una entidad de tipo
	 * TipoContacto
	 * 
	 * @param tipoContacto
	 * @return tipoContactoResDTO
	 */
	public static TipoContactoDTO buildTipoContactoResDTOFromTipoContacto(TipoContacto tipoContacto) {
		TipoContactoDTO tipoContactoResDTO = null;
		if (tipoContacto != null) {
			tipoContactoResDTO = new TipoContactoDTO();
			tipoContactoResDTO.setId(tipoContacto.getId());
			tipoContactoResDTO.setDescription(tipoContacto.getDescription());
		}
		return tipoContactoResDTO;
	}

	/**
	 * Construye un objeto de tipo TipoContactoRespDTO a partir de un objeto de tipo
	 * TipoContactoDTO
	 * 
	 * @param tipoContactoDTO
	 * @return tipoContactoRespDTO
	 */
	public static TipoContactoRespDTO builTipoTipoContactoRespDTOFromTipoContactoDTO(TipoContactoDTO tipoContactoDTO) {
		TipoContactoRespDTO tipoContactoRespDTO = null;
		if (tipoContactoDTO != null) {
			tipoContactoRespDTO = new TipoContactoRespDTO();
			tipoContactoRespDTO.setId(tipoContactoDTO.getId());
			tipoContactoRespDTO.setDescription(tipoContactoDTO.getDescription());
		}
		return tipoContactoRespDTO;
	}

	/**
	 * Construye un objeto de tipo TipoContactoRespDTO a partir de una entidad de
	 * tipo TipoContacto
	 * 
	 * @param tipoContacto
	 * @return tipoContactoRespDTO
	 */
	public static TipoContactoRespDTO buildTipoContactoRespDTOFormTipoContacto(TipoContacto tipoContacto) {
		TipoContactoRespDTO tipoContactoRespDTO = null;
		if (tipoContacto != null) {
			tipoContactoRespDTO = new TipoContactoRespDTO();
			tipoContactoRespDTO.setId(tipoContacto.getId());
			tipoContactoRespDTO.setDescription(tipoContacto.getDescription());
		}
		return tipoContactoRespDTO;
	}

	/**
	 * Construye un objeto de tipo TipoContactoDTO a partir de un objeto de tipo
	 * TipoContactoRespDTO
	 * 
	 * @param tipoContactoRespDTO
	 * @return tipoContacto
	 */
	public static TipoContactoDTO buildTipoContactoDTOFromTipoContactoRespDTO(TipoContactoRespDTO tipoContactoRespDTO) {
		TipoContactoDTO tipoContacto = null;
		if (tipoContactoRespDTO != null) {
			tipoContacto = new TipoContactoDTO();
			tipoContacto.setId(tipoContactoRespDTO.getId());
			tipoContacto.setDescription(tipoContactoRespDTO.getDescription());
		}
		return tipoContacto;
	}

	/**
	 * Construye un objeto de tipo TipoContactoDTO a partir de un objeto de tipo
	 * TipoContactoReqDTO
	 * 
	 * @param tipoContactoReqDTO
	 * @return tipoContactoDTO
	 */
	public static TipoContactoDTO buildTipoContactoDTOFromTipoContactoReqDTO(TipoContactoReqDTO tipoContactoReqDTO) {
		TipoContactoDTO tipoContactoDTO = null;
		if (tipoContactoReqDTO != null) {
			tipoContactoDTO = new TipoContactoDTO();
			tipoContactoDTO.setId(tipoContactoReqDTO.getId());
		}
		return tipoContactoDTO;
	}
	
	/**
	 * Construye un objeto de tipo TipoContactoDTO a partir de un objeto de tipo
	 * TipoContactoReqDTO
	 * 
	 * @param tipoContactoReqDTO
	 * @return tipoContactoDTO
	 */
	public static TipoContactoDTO buildTipoContactoDTOFromTipoContactoResponseDTO(TipoContactoDTO tipoContactoDTO2) {
		TipoContactoDTO tipoContactoDTO = null;
		if (tipoContactoDTO2 != null) {
			tipoContactoDTO = new TipoContactoDTO();
			tipoContactoDTO.setId(tipoContactoDTO2.getId());
			tipoContactoDTO.setDescription(tipoContactoDTO2.getDescription());
		}
		return tipoContactoDTO;
	}

}
