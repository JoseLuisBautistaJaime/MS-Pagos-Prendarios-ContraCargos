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
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqNN;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqDTONE;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqSaveNewDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaSaveReqDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseSaveDTO;

/**
 * @name ValidadorCatalogo
 * @description Clase que contiene validaciones para los objetos de catalogos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/03/2019 18:07 hrs.
 * @version 0.1
 */
public interface ValidadorCatalogo {

	/**
	 * Valida que los atributos que deben estar en el objeto de la peticion de alta
	 * exista y sean los correctos ademas
	 * 
	 * @param codigoEstadoCuentaReqSaveDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaSave(CodigoEstadoCuentaReqSaveDTO codigoEstadoCuentaReqSaveDTO) {
		return (null != codigoEstadoCuentaReqSaveDTO) && (null != codigoEstadoCuentaReqSaveDTO.getCodigo())
				&& (!"".equals(codigoEstadoCuentaReqSaveDTO.getCodigo()))
				&& (null != codigoEstadoCuentaReqSaveDTO.getCategoria()
						&& null != codigoEstadoCuentaReqSaveDTO.getCategoria().getId()
						&& (codigoEstadoCuentaReqSaveDTO.getCategoria().getId() > 0))
				&& (null != codigoEstadoCuentaReqSaveDTO.getEntidad()
						&& null != codigoEstadoCuentaReqSaveDTO.getEntidad().getId()
						&& codigoEstadoCuentaReqSaveDTO.getEntidad().getId() > 0);
	}

	/**
	 * Valida que el todos los atributos de el objeto de la peticion de
	 * actualizacion no sean nulos y sean los correctos ademas
	 * 
	 * @param codigoEstadoCuentaReqUpdtDTO
	 * @return
	 */
	public static boolean validateCodigoEstadoCuentaUpdate(CodigoEstadoCuentaReqUpdtDTO codigoEstadoCuentaReqUpdtDTO) {
		return (null != codigoEstadoCuentaReqUpdtDTO)
				&& (null != codigoEstadoCuentaReqUpdtDTO.getCodigo() && null != codigoEstadoCuentaReqUpdtDTO.getId()
						&& (codigoEstadoCuentaReqUpdtDTO.getId() > 0))
				&& (!"".equals(codigoEstadoCuentaReqUpdtDTO.getCodigo()))
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
	 * @param afiliacionReqSaveDTO
	 * @return
	 */
	public static boolean validateAfilacionSave(AfiliacionReqSaveDTO afiliacionReqSaveDTO) {
		return (null != afiliacionReqSaveDTO && null != afiliacionReqSaveDTO.getNumero()
				&& !afiliacionReqSaveDTO.getNumero().equals("0") && !afiliacionReqSaveDTO.getNumero().equals(""));
	}

	/**
	 * Valida que los atributos de la peticion de actualizacion de afiliacion no
	 * sean nulos y sean datos validos
	 * 
	 * @param afiliacionReqDTO
	 * @return
	 */
	public static boolean validateAfilacionUpdt(AfiliacionReqDTO afiliacionReqDTO) {
		return (null != afiliacionReqDTO && null != afiliacionReqDTO.getNumero()
				&& !afiliacionReqDTO.getNumero().equals("0") && !afiliacionReqDTO.getNumero().equals("")
				&& null != afiliacionReqDTO.getId() && afiliacionReqDTO.getId() > 0);
	}

	/**
	 * Valida que de tipo EntidadBaseDTO en el request para la alta tenga los
	 * valores correctos
	 * 
	 * @param entidadBaseSaveDTO
	 * @return
	 */
	public static boolean validateEntidadBaseDTOSave(EntidadBaseSaveDTO entidadBaseSaveDTO) {
		if (null == entidadBaseSaveDTO || null == entidadBaseSaveDTO.getNombre()
				|| entidadBaseSaveDTO.getNombre().equals("") || null == entidadBaseSaveDTO.getDescripcion()
				|| entidadBaseSaveDTO.getDescripcion().equals(""))
			return false;
		if (null != entidadBaseSaveDTO.getContactos() && !entidadBaseSaveDTO.getContactos().isEmpty()) {
			for (ContactoReqSaveNewDTO contactoReqSaveNewDTO : entidadBaseSaveDTO.getContactos()) {
				if (null == contactoReqSaveNewDTO.getEmail() || contactoReqSaveNewDTO.getEmail().equals("")
						|| null == contactoReqSaveNewDTO.getNombre() || contactoReqSaveNewDTO.getNombre().equals("")) {
					return false;
				}
			}
		} else
			return false;
		if (null != entidadBaseSaveDTO.getCuentas() && !entidadBaseSaveDTO.getCuentas().isEmpty()) {
			for (CuentaSaveReqDTO cuentaSaveReqDTO : entidadBaseSaveDTO.getCuentas()) {
				if (null == cuentaSaveReqDTO.getId() || cuentaSaveReqDTO.getId() <= 0) {
					return false;
				}
				if (null != cuentaSaveReqDTO.getAfiliaciones() && !cuentaSaveReqDTO.getAfiliaciones().isEmpty()) {
					for (AfiliacionSaveDTO afiliacionSaveDTO : cuentaSaveReqDTO.getAfiliaciones()) {
						if (null == afiliacionSaveDTO.getId() || afiliacionSaveDTO.getId() <= 0)
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
		if (null == entidadBaseDTO || null == entidadBaseDTO.getNombre() || entidadBaseDTO.getNombre().equals("")
				|| null == entidadBaseDTO.getDescripcion() || entidadBaseDTO.getDescripcion().equals("")
				|| null == entidadBaseDTO.getCuentas() || null == entidadBaseDTO.getId() || entidadBaseDTO.getId() <= 0)
			return false;
		if (null != entidadBaseDTO.getContactos() && !entidadBaseDTO.getContactos().isEmpty()) {
			for (ContactoReqDTONE contactoReqDTO : entidadBaseDTO.getContactos()) {
				if (null == contactoReqDTO.getId() || null == contactoReqDTO.getEmail()
						|| contactoReqDTO.getEmail().equals("") || null == contactoReqDTO.getNombre()
						|| contactoReqDTO.getNombre().equals("")) {
					return false;
				}
			}
		} else
			return false;
		if (null != entidadBaseDTO.getCuentas() && !entidadBaseDTO.getCuentas().isEmpty()) {
			for (CuentaSaveReqDTO cuentaSaveReqDTO : entidadBaseDTO.getCuentas()) {
				if (null == cuentaSaveReqDTO.getId() || cuentaSaveReqDTO.getId() <= 0) {
					return false;
				}
				if (null != cuentaSaveReqDTO.getAfiliaciones() && !cuentaSaveReqDTO.getAfiliaciones().isEmpty()) {
					for (AfiliacionSaveDTO afiliacionSaveDTO : cuentaSaveReqDTO.getAfiliaciones()) {
						if (null == afiliacionSaveDTO.getId() || afiliacionSaveDTO.getId() <= 0)
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
		if (contactoRequestDTO == null || contactoRequestDTO.getDescripcion() == null
				|| contactoRequestDTO.getDescripcion().isEmpty() || contactoRequestDTO.getEmail() == null
				|| contactoRequestDTO.getEmail().isEmpty() || contactoRequestDTO.getNombre() == null
				|| contactoRequestDTO.getNombre().isEmpty() || contactoRequestDTO.getTipoContacto() == null
				|| contactoRequestDTO.getTipoContacto().getId() == null
				|| contactoRequestDTO.getTipoContacto().getId() <= 0)
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
		if (contactoReqUpdateDTO == null || contactoReqUpdateDTO.getDescripcion() == null
				|| contactoReqUpdateDTO.getDescripcion().isEmpty() || contactoReqUpdateDTO.getEmail() == null
				|| contactoReqUpdateDTO.getEmail().isEmpty() || contactoReqUpdateDTO.getId() == null
				|| contactoReqUpdateDTO.getId() <= 0 || contactoReqUpdateDTO.getNombre() == null
				|| contactoReqUpdateDTO.getNombre().isEmpty() || contactoReqUpdateDTO.getTipoContacto() == null
				|| contactoReqUpdateDTO.getTipoContacto().getId() == null
				|| contactoReqUpdateDTO.getTipoContacto().getId() <= 0)
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
	 * Regresa una lista con los contactos que son nuevos
	 * 
	 * @param contactosOrigen
	 * @param contactosTarget
	 * @return
	 */
	public static List<Long> validateContactosExists2(Set<ContactoReqDTO> contactosOrigen,
			List<ContactoBaseDTO> contactosTarget) {
		List<Long> contactoReqDTOList = null;
		List<Long> list = null;
		if (null == contactosTarget || contactosTarget.isEmpty())
			return null;
		else {
			list = new ArrayList<>();
			for (ContactoBaseDTO contactoBaseDTO : contactosTarget)
				list.add(contactoBaseDTO.getId());
		}
		if (null != contactosTarget && !contactosTarget.isEmpty()) {
			contactoReqDTOList = new ArrayList<>();
			for (ContactoReqDTO contactoReqDTO : contactosOrigen) {
				if (!list.contains(contactoReqDTO.getId())) {
					contactoReqDTOList.add(contactoReqDTO.getId());
				}
			}
		} else
			return null;
		return contactoReqDTOList;
	}

	/**
	 * Valida si un objeto Cuenta contiene atributos validos y completos para ser
	 * guardado
	 * 
	 * @param cuentaSaveDTO
	 * @return
	 */
	public static boolean validateCuentaSave(CuentaSaveDTO cuentaSaveDTO) {
		if (null != cuentaSaveDTO && null != cuentaSaveDTO.getNumero() && !cuentaSaveDTO.getNumero().isEmpty()
				&& null != cuentaSaveDTO.getAfiliaciones() && !cuentaSaveDTO.getAfiliaciones().isEmpty()) {
			for (AfiliacionSaveDTO afiliacionSaveDTO : cuentaSaveDTO.getAfiliaciones()) {
				if (null == afiliacionSaveDTO.getId()) {
					return false;
				}
			}
		} else
			return false;
		return true;
	}

	/**
	 * Valida si un objeto Cuenta contiene atributos validos y completos para ser
	 * actualizado
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static boolean validateCuentaUpdate(CuentaDTO cuentaDTO) {
		if (null != cuentaDTO && null != cuentaDTO.getId() && null != cuentaDTO.getAfiliaciones()
				&& !cuentaDTO.getAfiliaciones().isEmpty() && null != cuentaDTO.getNumero()
				&& !cuentaDTO.getNumero().equals("")) {
			for (AfiliacionReqNN afiliacionRespDTO : cuentaDTO.getAfiliaciones()) {
				if (null == afiliacionRespDTO.getId()) {
					return false;
				}
			}
		} else
			return false;
		return true;
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
