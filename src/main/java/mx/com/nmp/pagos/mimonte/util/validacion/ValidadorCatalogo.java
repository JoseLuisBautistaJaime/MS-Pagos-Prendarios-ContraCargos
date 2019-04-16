/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseDTO;

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

	/**
	 * Valida que los atributos de la peticion de la de afiliacion no sean nulos y
	 * sean datos validos
	 * 
	 * @param afiliacionReqDTO
	 * @return
	 */
	public static boolean validateAfilacionSave(AfiliacionReqDTO afiliacionReqDTO) {
		return (null != afiliacionReqDTO && null != afiliacionReqDTO.getNumero() && afiliacionReqDTO.getNumero() > 0);
	}

	/**
	 * Valida que los atributos de la peticion de actualizacion de afiliacion no
	 * sean nulos y sean datos validos
	 * 
	 * @param afiliacionReqDTO
	 * @return
	 */
	public static boolean validateAfilacionUpdt(AfiliacionReqDTO afiliacionReqDTO) {
		return (null != afiliacionReqDTO && null != afiliacionReqDTO.getNumero() && afiliacionReqDTO.getNumero() > 0
				&& null != afiliacionReqDTO.getId() && afiliacionReqDTO.getId() > 0
				&& null != afiliacionReqDTO.getEstatus());
	}

	/**
	 * Valida que de tipo EntidadBaseDTO en el request para la alta tenga los
	 * valores correctos
	 * 
	 * @param entidadBaseDTO
	 * @return
	 */
	public static boolean validateEntidadBaseDTOSave(EntidadBaseDTO entidadBaseDTO) {
		if (null == entidadBaseDTO || null == entidadBaseDTO.getNombre() || null == entidadBaseDTO.getDescripcion())
			return false;
		if (null != entidadBaseDTO.getContactos() && !entidadBaseDTO.getContactos().isEmpty()) {
			for (ContactoReqDTO contactoReqDTO : entidadBaseDTO.getContactos()) {
				if (null == contactoReqDTO.getId() || contactoReqDTO.getId() <= 0) {
					return false;
				}
			}
		} else
			return false;
		if (null != entidadBaseDTO.getCuentas() && !entidadBaseDTO.getCuentas().isEmpty()) {
			for (CuentaReqDTO cuentaReqDTO : entidadBaseDTO.getCuentas()) {
				if (null == cuentaReqDTO.getId() || cuentaReqDTO.getId() <= 0) {
					return false;
				}
				if (null != cuentaReqDTO.getAfiliaciones() && !cuentaReqDTO.getAfiliaciones().isEmpty()) {
					for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
						if (null == afiliacionReqDTO.getId() || afiliacionReqDTO.getId() <= 0)
							return false;
					}
				} else
					return false;
			}
		} else
			return false;
		return true;
	}

	/**
	 * Valida que de tipo EntidadBaseDTO en el request para la alta tenga los
	 * valores correctos
	 * 
	 * @param entidadBaseDTO
	 * @return
	 */
	public static boolean validateEntidadBaseDTOUpdt(EntidadBaseDTO entidadBaseDTO) {
		if (null == entidadBaseDTO || null == entidadBaseDTO.getNombre() || null == entidadBaseDTO.getDescripcion()
				|| null == entidadBaseDTO.getCuentas() || null == entidadBaseDTO.getId() || entidadBaseDTO.getId() <= 0)
			return false;
		if (null != entidadBaseDTO.getContactos() && !entidadBaseDTO.getContactos().isEmpty()) {
			for (ContactoReqDTO contactoReqDTO : entidadBaseDTO.getContactos()) {
				if (null == contactoReqDTO.getId() || contactoReqDTO.getId() <= 0) {
					return false;
				}
			}
		} else
			return false;
		if (null != entidadBaseDTO.getCuentas() && !entidadBaseDTO.getCuentas().isEmpty()) {
			for (CuentaReqDTO cuentaReqDTO : entidadBaseDTO.getCuentas()) {
				if (null == cuentaReqDTO.getId() || cuentaReqDTO.getId() <= 0) {
					return false;
				}
				if (null != cuentaReqDTO.getAfiliaciones() && !cuentaReqDTO.getAfiliaciones().isEmpty()) {
					for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
						if (null == afiliacionReqDTO.getId() || afiliacionReqDTO.getId() <= 0)
							return false;
					}
				} else
					return false;
			}
		} else
			return false;
		return true;
	}

	/**
	 * Valida que un objeto de tipo ContactoRequestDTO no este nulo o vacio.
	 * 
	 * @param contactoRequestDTO
	 * @return
	 */
	public static boolean validaContactoReqSaveDTO(ContactoRequestDTO contactoRequestDTO) {
		if (contactoRequestDTO == null 
				|| contactoRequestDTO.getDescripcion() == null ||  contactoRequestDTO.getDescripcion().isEmpty()
				|| contactoRequestDTO.getEmail() == null || contactoRequestDTO.getEmail().isEmpty()
				|| contactoRequestDTO.getNombre() == null || contactoRequestDTO.getNombre().isEmpty() 
				|| contactoRequestDTO.getTipoContacto() == null
				|| contactoRequestDTO.getTipoContacto().getId() == null || contactoRequestDTO.getTipoContacto().getId() <= 0)
			return false;
		if (contactoRequestDTO.getTipoContacto() != null) {
			if (contactoRequestDTO.getTipoContacto() == null || contactoRequestDTO.getTipoContacto().getId() <= 0)
				return false;
		} else
			return false;
		return true;
	}

	/**
	 * Valida que un objeto de tipo ContactoReqUpdateDTO no este nulo o vacio.
	 * 
	 * @param contactoReqUpdateDTO
	 * @return
	 */
	public static boolean validaContactoReqUpdateDTO(ContactoReqUpdateDTO contactoReqUpdateDTO) {
		if (contactoReqUpdateDTO == null 
				|| contactoReqUpdateDTO.getDescripcion() == null || contactoReqUpdateDTO.getDescripcion().isEmpty()
				|| contactoReqUpdateDTO.getEmail() == null || contactoReqUpdateDTO.getEmail().isEmpty()
				|| contactoReqUpdateDTO.getId() == null || contactoReqUpdateDTO.getId() <= 0
				|| contactoReqUpdateDTO.getNombre() == null || contactoReqUpdateDTO.getNombre().isEmpty()
				|| contactoReqUpdateDTO.getTipoContacto() == null
				|| contactoReqUpdateDTO.getTipoContacto().getId() == null || contactoReqUpdateDTO.getTipoContacto().getId() <= 0)
			return false;
		if (contactoReqUpdateDTO.getTipoContacto() != null) {
			if (contactoReqUpdateDTO.getTipoContacto() == null || contactoReqUpdateDTO.getTipoContacto().getId() <= 0)
				return false;
		} else
			return false;
		return true;
	}

	/**
	 * Valida si los ids de un Set de objetos de tipo CuentaReqDTO estan contenidos
	 * en una lista de objetos de tipo CuentaBaseDTO
	 * 
	 * @param cuentasOrigen
	 * @param cuentasTarget
	 * @return
	 */
	public static boolean validateCuentasExists(Set<CuentaReqDTO> cuentasOrigen, List<CuentaBaseDTO> cuentasTarget) {
		List<Long> list = null;
		if (null == cuentasTarget || cuentasTarget.isEmpty())
			return true;
		else {
			list = new ArrayList<>();
			for (CuentaBaseDTO cuentaBaseDTO : cuentasTarget)
				list.add(cuentaBaseDTO.getId());
		}
		if (null != cuentasOrigen && !cuentasOrigen.isEmpty()) {
			for (CuentaReqDTO cuentaReqDTO : cuentasOrigen) {
				if (!list.contains(cuentaReqDTO.getId())) {
					return false;
				}
			}
		} else
			return true;
		return true;
	}

	/**
	 * Valida si los ids de una lista de objetos de tipo ContactoReqDTO estan
	 * contenidos en unalista de objetos de tipo ContactoBaseDTO
	 * 
	 * @param contactosOrigen
	 * @param contactosTarget
	 * @return
	 */
	public static boolean validateContactosExists(Set<ContactoReqDTO> contactosOrigen,
			List<ContactoBaseDTO> contactosTarget) {
		List<Long> list = null;
		if (null == contactosTarget || contactosTarget.isEmpty())
			return true;
		else {
			list = new ArrayList<>();
			for (ContactoBaseDTO contactoBaseDTO : contactosTarget)
				list.add(contactoBaseDTO.getId());
		}
		if (null != contactosTarget && !contactosTarget.isEmpty()) {
			for (ContactoReqDTO contactoReqDTO : contactosOrigen) {
				if (!list.contains(contactoReqDTO.getId())) {
					return false;
				}
			}
		} else
			return true;
		return true;
	}

	/**
	 * Valida si un objeto Cuenta contiene atributos validos y completos para ser
	 * guardado
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static boolean validateCuentaSave(CuentaDTO cuentaDTO) {
		return (null != cuentaDTO && null != cuentaDTO.getNumero() && !cuentaDTO.getNumero().isEmpty()
				&& null != cuentaDTO.getAfiliaciones() && !cuentaDTO.getAfiliaciones().isEmpty());
	}

	/**
	 * Valida si un objeto Cuenta contiene atributos validos y completos para ser
	 * actualizado
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static boolean validateCuentaUpdate(CuentaDTO cuentaDTO) {
		return (null != cuentaDTO && null != cuentaDTO.getNumero() && !cuentaDTO.getNumero().isEmpty()
				&& null != cuentaDTO.getId() && null != cuentaDTO.getAfiliaciones()
				&& !cuentaDTO.getAfiliaciones().isEmpty());
	}

	/**
	 * Regresa un valor booleano indicando si los ids de una lista de tipo
	 * AfiliaiconDTO estan contenidos en otra lista del mismo tipo
	 * 
	 * @param afiliacionTarget
	 * @param afiliacionOrigin
	 * @return
	 */
	public static boolean validateAfiliacionesExists(List<AfiliacionDTO> afiliacionTarget,
			Set<AfiliacionDTO> afiliacionOrigin) {
		if (null == afiliacionTarget || afiliacionTarget.isEmpty())
			return false;
		else {
			List<Long> lst = getIdsList(afiliacionTarget);
			for (AfiliacionDTO afiliacionDTO : afiliacionOrigin) {
				if (!lst.contains(afiliacionDTO.getId()))
					return false;
			}
		}
		return true;
	}

	/**
	 * Crea una lista de tipo Long con los ids de una lista de tipo AfiliacionDTO
	 * 
	 * @param afiliacionDTOList
	 * @return
	 */
	public static List<Long> getIdsList(List<AfiliacionDTO> afiliacionDTOList) {
		List<Long> lst = new ArrayList<>();
		for (AfiliacionDTO afiliacionDTO : afiliacionDTOList) {
			lst.add(afiliacionDTO.getId());
		}
		return lst;
	}

	/**
	 * Comprueba si lso ids de una lista de tipo AfiliacionDTO estan contenidos en
	 * una lista de ids de tipo Long
	 * 
	 * @param lst
	 * @param afiliacionDTOList
	 * @return
	 */
	public static boolean validateCuentasAfiliacionesExists(List<Long> lst, List<AfiliacionDTO> afiliacionDTOList) {
		List<Long> targetLst = new ArrayList<>();
		if (null != afiliacionDTOList && !afiliacionDTOList.isEmpty())
			for (AfiliacionDTO afiliacionDTO : afiliacionDTOList)
				targetLst.add(afiliacionDTO.getId());

		for (Long val : lst)
			if (!targetLst.contains(val))
				return false;

		return true;
	}

}
