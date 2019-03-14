package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.BaseCodigoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaGenDTO;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * Nombre: CodigoEstadoCuentaBuilder Descripcion: Clase de la capa de builder
 * que se encaraga de construir diversos objetos y entidades relacionados con el
 * catalogo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 06/03/2019 12:43 hrs.
 * @version 0.1
 */
public class CodigoEstadoCuentaBuilder {

	private CodigoEstadoCuentaBuilder() {
		super();
	}

	/**
	 * Cosntruye un objeto de tipo CodigoEstadoCuentaDTO a partir de una entidad de
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
			codigoEstadoCuentaDTO.setEntidad(null);
			codigoEstadoCuentaDTO.setId(codigoEstadoCuenta.getId());
			codigoEstadoCuentaDTO.setLeyenda(codigoEstadoCuenta.getLeyenda());
			codigoEstadoCuentaDTO.setStatus(codigoEstadoCuenta.getEstatus());
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
			codigoEstadoCuenta.setLeyenda(codigoEstadoCuentaDTO.getLeyenda());
			codigoEstadoCuenta.setEstatus(codigoEstadoCuentaDTO.getStatus());
		}
		return codigoEstadoCuenta;
	}

	/**
	 * Cosntruye un objeto de tipo BaseCodigoDTO a partir de un objeto de tipo
	 * CodigoEstadoCuentaGenDTO
	 * 
	 * @param codigoEstadoCuentaGenDTO
	 * @return
	 */
	public static BaseCodigoDTO buildBaseCodigoDTOFromCodigoEstadoCuentaGenDTO(
			CodigoEstadoCuentaGenDTO codigoEstadoCuentaGenDTO) {
		BaseCodigoDTO baseCodigoDTO = null;
		if (null != codigoEstadoCuentaGenDTO) {
			baseCodigoDTO = new BaseCodigoDTO();
			baseCodigoDTO.setEstatus(codigoEstadoCuentaGenDTO.getEstatus());
			baseCodigoDTO.setId(codigoEstadoCuentaGenDTO.getId());
			baseCodigoDTO.setLeyenda(codigoEstadoCuentaGenDTO.getLeyenda());
		}
		return baseCodigoDTO;
	}

	/**
	 * Construye una lista de objetods de tipo CodigoEstadoCuentaDTO a partir de una
	 * lista de entidades de tipo CodigoEstadoCuentaDTO
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

}
