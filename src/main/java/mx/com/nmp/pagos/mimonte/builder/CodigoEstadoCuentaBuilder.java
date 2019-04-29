/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaUpdtDTO;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * @name CodigoEstadoCuentaBuilder
 * @description Clase de la capa builder que se encarga de construir diversos
 *              objetos y entidades relacionados con el catalogo
 *              CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:43 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaBuilder {

	private CodigoEstadoCuentaBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de una entidad de
	 * tipo CodigoEstadoCuenta
	 * 
	 * @param codigoEstadoCuenta
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(
			CodigoEstadoCuenta codigoEstadoCuenta) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuenta) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO
					.setCategoria(CategoriaBuilder.buildCategoriaDTOFromCategoria(codigoEstadoCuenta.getCategoria()));
			codigoEstadoCuentaDTO
					.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(codigoEstadoCuenta.getEntidad()));
			codigoEstadoCuentaDTO.setId(codigoEstadoCuenta.getId());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuenta.getCodigo());
			codigoEstadoCuentaDTO
					.setEstatus(null != codigoEstadoCuenta.getEstatus() ? codigoEstadoCuenta.getEstatus() : true);
			codigoEstadoCuentaDTO.setCreatedBy(codigoEstadoCuenta.getCreatedBy());
			codigoEstadoCuentaDTO.setCreatedDate(codigoEstadoCuenta.getCreatedDate());
			codigoEstadoCuentaDTO.setDescription(codigoEstadoCuenta.getDescription());
			codigoEstadoCuentaDTO.setLastModifiedBy(codigoEstadoCuenta.getLastModifiedBy());
			codigoEstadoCuentaDTO.setShortDescription(codigoEstadoCuenta.getShortDescription());
			codigoEstadoCuentaDTO.setCreatedBy(codigoEstadoCuenta.getCreatedBy());
			codigoEstadoCuentaDTO.setLastModifiedDate(codigoEstadoCuenta.getLastModifiedDate());
		}
		return codigoEstadoCuentaDTO;
	}

	/**
	 * Construye una entidad de tipo CodigoEstadoCuenta a partir de un objeto de
	 * tipo CodigoEstadoCuentaDTO para realizar un alta
	 * 
	 * @param codigoEstadoCuentaDTO
	 * @return
	 */
	public static CodigoEstadoCuenta buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO) {
		CodigoEstadoCuenta codigoEstadoCuenta = null;
		if (null != codigoEstadoCuentaDTO) {
			codigoEstadoCuenta = new CodigoEstadoCuenta();
			codigoEstadoCuenta.setCategoria(
					CategoriaBuilder.buildCategoriaFromCategoriaDTO(codigoEstadoCuentaDTO.getCategoria()));
			codigoEstadoCuenta
					.setEntidad(EntidadBuilder.buildEntidadFromEntidadDTO(codigoEstadoCuentaDTO.getEntidad(), null));
			codigoEstadoCuenta.setId(codigoEstadoCuentaDTO.getId());
			codigoEstadoCuenta.setCodigo(codigoEstadoCuentaDTO.getCodigo());
			codigoEstadoCuenta
					.setEstatus(null != codigoEstadoCuentaDTO.getEstatus() ? codigoEstadoCuentaDTO.getEstatus() : true);
			codigoEstadoCuenta.setCreatedBy(codigoEstadoCuentaDTO.getCreatedBy());
			codigoEstadoCuenta.setCreatedDate(codigoEstadoCuentaDTO.getCreatedDate());
		}
		return codigoEstadoCuenta;
	}

	/**
	 * Construye una entidad de tipo CodigoEstadoCuenta a partir de un objeto de
	 * tipo CodigoEstadoCuentaDTO para realizar una actualizacion
	 * 
	 * @param codigoEstadoCuentaDTO
	 * @return
	 */
	public static CodigoEstadoCuenta buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTOUpdt(
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO) {
		CodigoEstadoCuenta codigoEstadoCuenta = null;
		if (null != codigoEstadoCuentaDTO) {
			codigoEstadoCuenta = new CodigoEstadoCuenta();
			codigoEstadoCuenta.setCategoria(
					CategoriaBuilder.buildCategoriaFromCategoriaDTO(codigoEstadoCuentaDTO.getCategoria()));
			codigoEstadoCuenta
					.setEntidad(EntidadBuilder.buildEntidadFromEntidadDTO(codigoEstadoCuentaDTO.getEntidad(), null));
			codigoEstadoCuenta.setId(codigoEstadoCuentaDTO.getId());
			codigoEstadoCuenta.setCodigo(codigoEstadoCuentaDTO.getCodigo());
			codigoEstadoCuenta
					.setEstatus(null != codigoEstadoCuentaDTO.getEstatus() ? codigoEstadoCuentaDTO.getEstatus() : true);
			codigoEstadoCuenta.setLastModifiedBy(codigoEstadoCuentaDTO.getLastModifiedBy());
			codigoEstadoCuenta.setLastModifiedDate(codigoEstadoCuentaDTO.getLastModifiedDate());
		}
		return codigoEstadoCuenta;
	}

	/**
	 * Construye una lista de objetods de tipo CodigoEstadoCuentaDTO a partir de una
	 * lista de entidades de tipo CodigoEstadoCuenta
	 * 
	 * @param codigoEstadoCuentaList
	 * @return
	 */
	public static List<CodigoEstadoCuentaDTO> buildCodigoEstadoCuentaDTOListFromCodigoEstadoCuentaList(
			List<CodigoEstadoCuenta> codigoEstadoCuentaList) {
		List<CodigoEstadoCuentaDTO> codigoEstadoCuentaDTOList = null;
		if (null != codigoEstadoCuentaList && !codigoEstadoCuentaList.isEmpty()) {
			codigoEstadoCuentaDTOList = new ArrayList<>();
			for (CodigoEstadoCuenta codigoEstadoCuenta : codigoEstadoCuentaList) {
				codigoEstadoCuentaDTOList.add(buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuenta));
			}
		}
		return codigoEstadoCuentaDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo CodigoEstadoCuentaUpdtDTO a partir de
	 * una lista de objetos de tipo CodigoEstadoCuenta
	 * 
	 * @param codigoEstadoCuentaList
	 * @return
	 */
	public static List<CodigoEstadoCuentaUpdtDTO> buildCodigoEstadoCuentaUpdtDTOListFromCodigoEstadoCuentaList(
			List<CodigoEstadoCuenta> codigoEstadoCuentaList) {
		List<CodigoEstadoCuentaUpdtDTO> codigoEstadoCuentaUpdtDTO = null;
		if (null != codigoEstadoCuentaList && !codigoEstadoCuentaList.isEmpty()) {
			codigoEstadoCuentaUpdtDTO = new ArrayList<>();
			for (CodigoEstadoCuenta codigoEstadoCuenta : codigoEstadoCuentaList) {
				codigoEstadoCuentaUpdtDTO.add(buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuenta(codigoEstadoCuenta));
			}
		}
		return codigoEstadoCuentaUpdtDTO;
	}

	/**
	 * Construye una lista de objetos de tipo CodigoEstadoCuentaUpdtDTO a partir de
	 * una lista de objetos de tipo CodigoEstadoCuentaDTO
	 * 
	 * @param codigoEstadoCuentaDTOList
	 * @return
	 */
	public static List<CodigoEstadoCuentaUpdtDTO> buildCodigoEstadoCuentaUpdtDTOListFromCodigoEstadoCuentaDTOList(
			List<CodigoEstadoCuentaDTO> codigoEstadoCuentaDTOList) {
		List<CodigoEstadoCuentaUpdtDTO> codigoEstadoCuentaUpdtDTO = null;
		if (null != codigoEstadoCuentaDTOList && !codigoEstadoCuentaDTOList.isEmpty()) {
			codigoEstadoCuentaUpdtDTO = new ArrayList<>();
			for (CodigoEstadoCuentaDTO codigoEstadoCuentaDTO : codigoEstadoCuentaDTOList) {
				codigoEstadoCuentaUpdtDTO
						.add(buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuentaDTO(codigoEstadoCuentaDTO));
			}
		}
		return codigoEstadoCuentaUpdtDTO;
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de un objeto de
	 * tipo CodigoEstadoCuentaDTO
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqDTO(
			CodigoEstadoCuentaReqDTO codigoEstadoCuentaReqDTO, Date createdDate, Date lastModifiedDate) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuentaReqDTO) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO.setCreatedDate(createdDate);
			codigoEstadoCuentaDTO.setLastModifiedDate(lastModifiedDate);
			codigoEstadoCuentaDTO.setCategoria(
					CategoriaBuilder.buildCategoriaDTOFromCategoriaReqDTO(codigoEstadoCuentaReqDTO.getCategoria()));
			codigoEstadoCuentaDTO
					.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidadReqDTO(codigoEstadoCuentaReqDTO.getEntidad()));
			codigoEstadoCuentaDTO.setEstatus(codigoEstadoCuentaReqDTO.getEstatus());
			codigoEstadoCuentaDTO.setId(codigoEstadoCuentaReqDTO.getId());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuentaReqDTO.getCodigo());
		}
		return codigoEstadoCuentaDTO;
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de un objeto de
	 * tipo CodigoEstadoCuentaReqSaveDTO
	 * 
	 * @param codigoEstadoCuentaReqSaveDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqSaveDTO(
			CodigoEstadoCuentaReqSaveDTO codigoEstadoCuentaReqSaveDTO, Date createdDate, Date lastModifiedDate) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuentaReqSaveDTO) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO.setCreatedDate(createdDate);
			codigoEstadoCuentaDTO.setLastModifiedDate(lastModifiedDate);
			codigoEstadoCuentaDTO.setCategoria(
					CategoriaBuilder.buildCategoriaDTOFromCategoriaReqDTO(codigoEstadoCuentaReqSaveDTO.getCategoria()));
			codigoEstadoCuentaDTO.setEntidad(
					EntidadBuilder.buildEntidadDTOFromEntidadReqDTO(codigoEstadoCuentaReqSaveDTO.getEntidad()));
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuentaReqSaveDTO.getCodigo());
		}
		return codigoEstadoCuentaDTO;
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de un objeto de
	 * tipo CodigoEstadoCuentaReqUpdtDTO
	 * 
	 * @param codigoEstadoCuentaReqUpdtDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqUpdtDTO(
			CodigoEstadoCuentaReqUpdtDTO codigoEstadoCuentaReqUpdtDTO, Date createdDate, Date lastModifiedDate) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuentaReqUpdtDTO) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO.setCreatedDate(createdDate);
			codigoEstadoCuentaDTO.setLastModifiedDate(lastModifiedDate);
			codigoEstadoCuentaDTO.setCategoria(
					CategoriaBuilder.buildCategoriaDTOFromCategoriaReqDTO(codigoEstadoCuentaReqUpdtDTO.getCategoria()));
			codigoEstadoCuentaDTO.setEntidad(
					EntidadBuilder.buildEntidadDTOFromEntidadReqDTO(codigoEstadoCuentaReqUpdtDTO.getEntidad()));
			codigoEstadoCuentaDTO.setId(codigoEstadoCuentaReqUpdtDTO.getId());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuentaReqUpdtDTO.getCodigo());
		}
		return codigoEstadoCuentaDTO;
	}

	/**
	 * Construye un obketo de tipo CodigoEstadoCuentaUpdtDTO a partir de un objeto
	 * de tipo CodigoEstadoCuentaDTO
	 * 
	 * @param codigoEstadoCuentaDTO
	 * @return
	 */
	public static CodigoEstadoCuentaUpdtDTO buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuentaDTO(
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO) {
		CodigoEstadoCuentaUpdtDTO codigoEstadoCuentaUpdtDTO = null;
		if (null != codigoEstadoCuentaDTO) {
			codigoEstadoCuentaUpdtDTO = new CodigoEstadoCuentaUpdtDTO();
			codigoEstadoCuentaUpdtDTO.setCategoria(codigoEstadoCuentaDTO.getCategoria());
			codigoEstadoCuentaUpdtDTO.setCodigo(codigoEstadoCuentaDTO.getCodigo());
			codigoEstadoCuentaUpdtDTO
					.setEntidad(EntidadBuilder.buildBaseentidadFromEntidadDTO(codigoEstadoCuentaDTO.getEntidad()));
			codigoEstadoCuentaUpdtDTO.setId(codigoEstadoCuentaDTO.getId());
		}
		return codigoEstadoCuentaUpdtDTO;
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaUpdtDTO a partir de un objeto
	 * de tipo CodigoEstadoCuenta
	 * 
	 * @param codigoEstadoCuenta
	 * @return
	 */
	public static CodigoEstadoCuentaUpdtDTO buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuenta(
			CodigoEstadoCuenta codigoEstadoCuenta) {
		CodigoEstadoCuentaUpdtDTO codigoEstadoCuentaUpdtDTO = null;
		if (null != codigoEstadoCuenta) {
			codigoEstadoCuentaUpdtDTO = new CodigoEstadoCuentaUpdtDTO();
			codigoEstadoCuentaUpdtDTO
					.setCategoria(CategoriaBuilder.buildCategoriaDTOFromCategoria(codigoEstadoCuenta.getCategoria()));
			codigoEstadoCuentaUpdtDTO.setCodigo(codigoEstadoCuenta.getCodigo());
			codigoEstadoCuentaUpdtDTO
					.setEntidad(EntidadBuilder.buildBaseentidadFromEntidad(codigoEstadoCuenta.getEntidad()));
			codigoEstadoCuentaUpdtDTO.setId(codigoEstadoCuenta.getId());
		}
		return codigoEstadoCuentaUpdtDTO;
	}

	/**
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de un objeto de
	 * tipo CodigoEstadoCuentaUpdtDTO
	 * 
	 * @param codigoEstadoCuentaUpdtDTO
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaUpdtDTO(
			CodigoEstadoCuentaUpdtDTO codigoEstadoCuentaUpdtDTO) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuentaUpdtDTO) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO.setCategoria(codigoEstadoCuentaUpdtDTO.getCategoria());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuentaUpdtDTO.getCodigo());
			codigoEstadoCuentaDTO.setEntidad(
					EntidadBuilder.buildEntidadDTOFromBaseEntidadDTO(codigoEstadoCuentaUpdtDTO.getEntidad()));
		}
		return codigoEstadoCuentaDTO;
	}

}
