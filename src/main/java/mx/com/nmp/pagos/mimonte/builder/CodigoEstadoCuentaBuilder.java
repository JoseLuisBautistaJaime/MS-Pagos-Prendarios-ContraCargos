package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * @name CodigoEstadoCuentaBuilder
 * @description Clase de la capa builder que se encarag de construir diversos
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
			codigoEstadoCuentaDTO.setCategoria(null);
			codigoEstadoCuentaDTO.setEntidades(null);
			codigoEstadoCuentaDTO.setId(codigoEstadoCuenta.getId());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuenta.getCodigo());
			codigoEstadoCuentaDTO.setEstatus(codigoEstadoCuenta.getEstatus());
		}
		return codigoEstadoCuentaDTO;
	}

	/**
	 * Construye una entidad de tipo CodigoEstadoCuenta a partir de un objeto de
	 * tipo CodigoEstadoCuentaDTO
	 * 
	 * @param codigoEstadoCuentaDTO
	 * @return
	 */
	public static CodigoEstadoCuenta buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO) {
		CodigoEstadoCuenta codigoEstadoCuenta = null;
		if (null != codigoEstadoCuentaDTO) {
			codigoEstadoCuenta = new CodigoEstadoCuenta();
			codigoEstadoCuenta.setCategoria(null);
			codigoEstadoCuenta.setEntidades(null);
			codigoEstadoCuenta.setId(codigoEstadoCuentaDTO.getId());
			codigoEstadoCuenta.setCodigo(codigoEstadoCuentaDTO.getCodigo());
			codigoEstadoCuenta.setEstatus(codigoEstadoCuentaDTO.getEstatus());
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
	 * Construye un objeto de tipo CodigoEstadoCuentaDTO a partir de un pbjeto de
	 * tipo CodigoEstadoCuentaDTO
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqDTO(
			CodigoEstadoCuentaReqDTO codigoEstadoCuentaReqDTO) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		if (null != codigoEstadoCuentaReqDTO) {
			codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO();
			codigoEstadoCuentaDTO.setCategoria(
					CategoriaBuilder.buildCategoriaDTOFromCategoriaReqDTO(codigoEstadoCuentaReqDTO.getCategoria()));
			Set<EntidadDTO> entidades = new TreeSet<>();
			entidades.add(EntidadBuilder.buildEntidadDTOFromEntidadReqDTO(codigoEstadoCuentaReqDTO.getEntidad()));
			codigoEstadoCuentaDTO.setEntidades(entidades);
			codigoEstadoCuentaDTO.setEstatus(codigoEstadoCuentaReqDTO.getEstatus());
			codigoEstadoCuentaDTO.setId(codigoEstadoCuentaReqDTO.getId());
			codigoEstadoCuentaDTO.setCodigo(codigoEstadoCuentaReqDTO.getCodigo());
			codigoEstadoCuentaDTO.setEstatus(codigoEstadoCuentaReqDTO.getEstatus());
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
			Iterator<EntidadDTO> it = null;
			EntidadDTO entidad = null;
			codigoEstadoCuentaUpdtDTO.setCategoria(codigoEstadoCuentaDTO.getCategoria());
			codigoEstadoCuentaUpdtDTO.setCodigo(codigoEstadoCuentaDTO.getCodigo());
			if (null != codigoEstadoCuentaDTO.getEntidades() && !codigoEstadoCuentaDTO.getEntidades().isEmpty()) {
				it = codigoEstadoCuentaDTO.getEntidades().iterator();
				entidad = it.next();
			}
			codigoEstadoCuentaUpdtDTO.setEntidad(EntidadBuilder.buildBaseentidadFromEntidadDTO(entidad));
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
			Set<EntidadDTO> entidades = new TreeSet<>();
			entidades.add(EntidadBuilder.buildEntidadDTOFromBaseEntidadDTO(codigoEstadoCuentaUpdtDTO.getEntidad()));
			codigoEstadoCuentaDTO.setEntidades(entidades);
		}
		return codigoEstadoCuentaDTO;
	}

}
