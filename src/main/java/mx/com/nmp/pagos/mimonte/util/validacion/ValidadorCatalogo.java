/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqUpdtDTO;

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
	 * exista y sean los correctos ademas
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaSave(CodigoEstadoCuentaReqDTO codigoEstadoCuentaReqDTO) {
		return (null != codigoEstadoCuentaReqDTO) && (null != codigoEstadoCuentaReqDTO.getCodigo())
				&& (null != codigoEstadoCuentaReqDTO.getCategoria()
						&& null != codigoEstadoCuentaReqDTO.getCategoria().getId()
						&& (codigoEstadoCuentaReqDTO.getCategoria().getId() > 0))
				&& (null != codigoEstadoCuentaReqDTO.getEntidad()
						&& null != codigoEstadoCuentaReqDTO.getEntidad().getId()
						&& codigoEstadoCuentaReqDTO.getEntidad().getId() > 0);
	}

	/**
	 * Valida que el todos los atributos de el objeto de la peticion de
	 * actualizacion no sean nulos y sean los correctos ademas
	 * 
	 * @param codigoEstadoCuentaReqDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaUpdate(CodigoEstadoCuentaReqUpdtDTO codigoEstadoCuentaReqUpdtDTO) {
		return (null != codigoEstadoCuentaReqUpdtDTO)
				&& (null != codigoEstadoCuentaReqUpdtDTO.getCodigo() && null != codigoEstadoCuentaReqUpdtDTO.getId()
						&& (codigoEstadoCuentaReqUpdtDTO.getId() > 0))
				&& (null != codigoEstadoCuentaReqUpdtDTO.getCategoria()
						&& null != codigoEstadoCuentaReqUpdtDTO.getCategoria().getId()
						&& (codigoEstadoCuentaReqUpdtDTO.getCategoria().getId() > 0))
				&& (null != codigoEstadoCuentaReqUpdtDTO.getEntidad()
						&& null != codigoEstadoCuentaReqUpdtDTO.getEntidad().getId()
						&& (codigoEstadoCuentaReqUpdtDTO.getEntidad().getId() > 0));
	}

}
