package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqDTO;

/**
 * @name ValidadorCatalogo
 * @description Clase que contiene validaciones para los objetos de catalogos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/03/2019 18:07 hrs.
 * @version 0.1
 */
public abstract class ValidadorCatalogo {

	private ValidadorCatalogo() {
		super();
	}

	/**
	 * Valida que los atributos que deben estar en el objeto de la peticion de alta
	 * exista
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaSave(CodigoEstadoCuentaReqDTO codigoEstadoCuentaReqDTO) {
		if (null != codigoEstadoCuentaReqDTO) {
			if (null == codigoEstadoCuentaReqDTO.getCodigo())
				return false;
			if (null == codigoEstadoCuentaReqDTO.getCategoria()
					|| null == codigoEstadoCuentaReqDTO.getCategoria().getId())
				return false;
			if (null == codigoEstadoCuentaReqDTO.getEntidad() || null == codigoEstadoCuentaReqDTO.getEntidad().getId())
				return false;
		} else
			return false;
		return true;
	}

	/**
	 * Valida que el todos los atributos de el objeto de la peticion de
	 * actualizacion no sean nulos
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaUpdate(CodigoEstadoCuentaReqDTO codigoEstadoCuentaReqDTO) {
		if (null != codigoEstadoCuentaReqDTO) {
			if (null == codigoEstadoCuentaReqDTO.getCodigo() || null == codigoEstadoCuentaReqDTO.getEstatus()
					|| null == codigoEstadoCuentaReqDTO.getId())
				return false;
			if (null == codigoEstadoCuentaReqDTO.getCategoria()
					|| null == codigoEstadoCuentaReqDTO.getCategoria().getId())
				return false;
			if (null == codigoEstadoCuentaReqDTO.getEntidad() || null == codigoEstadoCuentaReqDTO.getEntidad().getId())
				return false;
		} else
			return false;
		return true;
	}

}
