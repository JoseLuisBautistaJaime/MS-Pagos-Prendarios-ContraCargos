package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;

/**
 * Nombre: CuentaBuilder Descripcion: Clase que construye objetos relacionados
 * con Cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 20:27 hrs.
 * @version 0.1
 */
public abstract class CuentaBuilder {

	private CuentaBuilder() {
		super();
	}

	/**
	 * Construye un entity de tipo Cuenta a partir de un objeto de tipo
	 * CuentaBaseDTO
	 * 
	 * @param cuentaBaseDTO
	 * @return
	 */
	public static Cuenta buildCuentaFromCuentaBaseDTO(CuentaBaseDTO cuentaBaseDTO) {
		Cuenta cuenta = null;
		if (null != cuentaBaseDTO) {
			cuenta = new Cuenta();
			cuenta.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionSetFromAfiliacionDTOSet(cuentaBaseDTO.getAfiliaciones()));
			cuenta.setCreatedBy(cuentaBaseDTO.getCreatedBy());
			cuenta.setCreatedDate(cuentaBaseDTO.getCreatedDate());
			cuenta.setDescription(cuentaBaseDTO.getDescription());
			cuenta.setEstatus(cuentaBaseDTO.getEstatus());
			cuenta.setId(cuentaBaseDTO.getId());
			cuenta.setLastModifiedBy(cuentaBaseDTO.getLastModifiedBy());
			cuenta.setLastModifiedDate(cuentaBaseDTO.getLastModifiedDate());
			cuenta.setNumeroCuenta(cuentaBaseDTO.getNumeroCuenta());
			cuenta.setShortDescription(cuentaBaseDTO.getShortDescription());
		}
		return cuenta;
	}

	/**
	 * Construye un objeto de tipo CuentaBaseDTO a partir de un entity de tipo
	 * Cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	public static CuentaBaseDTO buildCuentaBaseDTOFromCuenta(Cuenta cuenta) {
		CuentaBaseDTO cuentaBaseDTO = null;
		if (null != cuenta) {
			cuentaBaseDTO = new CuentaBaseDTO();
			cuentaBaseDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionDTOSetFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaBaseDTO.setCreatedBy(cuenta.getCreatedBy());
			cuentaBaseDTO.setCreatedDate(cuenta.getCreatedDate());
			cuentaBaseDTO.setDescription(cuenta.getDescription());
			cuentaBaseDTO.setEstatus(cuenta.getEstatus());
			cuentaBaseDTO.setId(cuenta.getId());
			cuentaBaseDTO.setLastModifiedBy(cuenta.getLastModifiedBy());
			cuentaBaseDTO.setLastModifiedDate(cuenta.getLastModifiedDate());
			cuentaBaseDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
			cuentaBaseDTO.setShortDescription(cuenta.getShortDescription());
		}
		return cuentaBaseDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaDTO a partir de un objeto de tipo
	 * CuentaBaseDTO
	 * 
	 * @param cuentaBaseDTO
	 * @return
	 */
	public static CuentaDTO buildCuentaDTOFromCuentaBaseDTO(CuentaBaseDTO cuentaBaseDTO) {
		CuentaDTO cuentaDTO = null;
		if (null != cuentaBaseDTO) {
			cuentaDTO = new CuentaDTO();
			cuentaDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionRespDTOListFromAfiliacionDTOSet(cuentaBaseDTO.getAfiliaciones()));
			cuentaDTO.setEstatus(cuentaBaseDTO.getEstatus());
			cuentaDTO.setId(cuentaBaseDTO.getId());
			cuentaDTO.setNumero(cuentaBaseDTO.getNumeroCuenta());

		}
		return cuentaDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaBaseDTO a partir de un objeto de tipo
	 * CuentaDTO
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static CuentaBaseDTO buildCuentaBaseDTOFromCuentaDTO(CuentaDTO cuentaDTO) {
		CuentaBaseDTO cuentaBaseDTO = null;
		if (null != cuentaDTO) {
			cuentaBaseDTO = new CuentaBaseDTO();
			cuentaBaseDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionRespDTOListFromAfiliacionDTOSet(cuentaDTO.getAfiliaciones()));
			cuentaBaseDTO.setEstatus(cuentaDTO.getEstatus());
			cuentaBaseDTO.setId(cuentaDTO.getId());
			cuentaBaseDTO.setNumeroCuenta(cuentaDTO.getNumero());

		}
		return cuentaBaseDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaDTO a partir de un entity de tipo Cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	public static CuentaDTO buildCuentaDTOFromCuenta(Cuenta cuenta) {
		CuentaDTO cuentaDTO = null;
		if (null != cuenta) {
			cuentaDTO = new CuentaDTO();
			cuentaDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionRespDTOListFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaDTO.setEstatus(cuenta.getEstatus());
			cuentaDTO.setId(cuenta.getId());
			cuentaDTO.setNumero(cuenta.getNumeroCuenta());
		}
		return cuentaDTO;
	}

	/**
	 * Construye una lista de objetos de tipo CuentaDTO a partir de una lista de
	 * entities de tipom Cuenta
	 * 
	 * @param cuentaList
	 * @return
	 */
	public static List<CuentaDTO> buildCuentaDTOListFromCuentaDTOList(List<Cuenta> cuentaList) {
		List<CuentaDTO> cuentaDTOList = null;
		if (null != cuentaList && !cuentaList.isEmpty()) {
			cuentaDTOList = new ArrayList<>();
			for (Cuenta cuenta : cuentaList) {
				cuentaDTOList.add(buildCuentaDTOFromCuenta(cuenta));
			}
		}
		return cuentaDTOList;
	}

	/**
	 * Construye un objeto de tipo CuentaEntDTO a partir de un entity de tipo Cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	public static CuentaEntDTO buildCuentaEntDTOFromCuenta(Cuenta cuenta) {
		CuentaEntDTO cuentaEntDTO = null;
		if (null != cuenta) {
			cuentaEntDTO = new CuentaEntDTO();
			cuentaEntDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionEntDTOListFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaEntDTO.setEstatus(cuenta.getEstatus());
			cuentaEntDTO.setId(cuenta.getId());
			cuentaEntDTO.setNumero(cuenta.getNumeroCuenta());
		}
		return cuentaEntDTO;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaEntDTO a partir de un Set de
	 * entities de tipo Cuenta
	 * 
	 * @param cuentaSet
	 * @return
	 */
	public static Set<CuentaEntDTO> buildCuentaEntDTOSetFromCuentaSet(Set<Cuenta> cuentaSet) {
		Set<CuentaEntDTO> cuentaEntDTOSet = null;
		if (null != cuentaSet) {
			cuentaEntDTOSet = new TreeSet<>();
			for (Cuenta cuenta : cuentaSet) {
				cuentaEntDTOSet.add(buildCuentaEntDTOFromCuenta(cuenta));
			}
		}
		return cuentaEntDTOSet;
	}

	/**
	 * Construye un objeto de tipo CuentaEntDTO a partir de un objeto de tipo
	 * CuentaReqDTO
	 * 
	 * @param cuentaReqDTO
	 * @return
	 */
	public static CuentaEntDTO buildCuentaEntDTOFromCuentaReqDTO(CuentaReqDTO cuentaReqDTO) {
		CuentaEntDTO cuentaEntDTO = null;
		if (null != cuentaReqDTO) {
			cuentaEntDTO = new CuentaEntDTO();
			cuentaEntDTO.setAfiliaciones(AfiliacionBuilder
					.buildAfiliacionEntDTOListFromAfiliacionReqDTOList(cuentaReqDTO.getAfiliaciones()));
			cuentaEntDTO.setEstatus(cuentaReqDTO.getEstatus());
			cuentaEntDTO.setId(cuentaReqDTO.getId());
			cuentaEntDTO.setNumero(cuentaReqDTO.getNumero());
		}
		return cuentaEntDTO;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaEntDTO a partir de un Set de
	 * objetos de tipo CuentaReqDTO
	 * 
	 * @param cuentaReqDTOSet
	 * @return
	 */
	public static Set<CuentaEntDTO> buildCuentaEntDTOSetFromCuentaReqDTOSet(Set<CuentaReqDTO> cuentaReqDTOSet) {
		Set<CuentaEntDTO> cuentaEntDTOSet = null;
		if (null != cuentaReqDTOSet && !cuentaReqDTOSet.isEmpty()) {
			cuentaEntDTOSet = new TreeSet<>();
			for (CuentaReqDTO cuentaReqDTO : cuentaReqDTOSet) {
				cuentaEntDTOSet.add(buildCuentaEntDTOFromCuentaReqDTO(cuentaReqDTO));
			}
		}
		return cuentaEntDTOSet;
	}

	/**
	 * Construye un objetos de tipo CuentaReqDTO a partir de un entity de tipo
	 * Cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	public static CuentaReqDTO buildCuentaReqDTOFromCuenta(Cuenta cuenta) {
		CuentaReqDTO cuentaReqDTO = null;
		if (null != cuenta) {
			cuentaReqDTO = new CuentaReqDTO();
			cuentaReqDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionReqDTOListFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaReqDTO.setEstatus(cuenta.getEstatus());
			cuentaReqDTO.setId(cuenta.getId());
			cuentaReqDTO.setNumero(cuenta.getNumeroCuenta());
		}
		return cuentaReqDTO;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaReqDTO a partir de un Set de
	 * entities de tipo Cuenta
	 * 
	 * @param cuentaSet
	 * @return
	 */
	public static Set<CuentaReqDTO> buildCuentaReqDTOSetFromCuentaSet(Set<Cuenta> cuentaSet) {
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		if (null != cuentaSet) {
			cuentaReqDTOSet = new TreeSet<>();
			for (Cuenta cuenta : cuentaSet) {
				cuentaReqDTOSet.add(buildCuentaReqDTOFromCuenta(cuenta));
			}
		}
		return cuentaReqDTOSet;
	}

	public static Cuenta buildCuentaFromCuentaReqDTO (CuentaReqDTO cuentaReqDTO) {
		Cuenta cuenta = null;
		if(null != cuentaReqDTO) {
			cuenta = new Cuenta();
			cuenta.setAfiliaciones(AfiliacionBuilder.buildAfiliacionSetFromAfiliacionReqDTOList(cuentaReqDTO.getAfiliaciones()));
			cuenta.setId(cuentaReqDTO.getId());
			cuenta.setEstatus(cuentaReqDTO.getEstatus());
			cuenta.setNumeroCuenta(cuentaReqDTO.getNumero());
		}
		return cuenta;
	}
	
	public static Set<Cuenta> buildCuentaSetFromCuentaReqDTOSet(Set<CuentaReqDTO> cuentaReqDTOSet) {
		Set<Cuenta> cuentaSet = null;
		if (null != cuentaReqDTOSet && !cuentaReqDTOSet.isEmpty()) {
			cuentaSet = new TreeSet<>();
			for (CuentaReqDTO cuentaReqDTO : cuentaReqDTOSet) {
				cuentaSet.add(buildCuentaFromCuentaReqDTO(cuentaReqDTO));
			}
		}
		return cuentaSet;
	}

}
