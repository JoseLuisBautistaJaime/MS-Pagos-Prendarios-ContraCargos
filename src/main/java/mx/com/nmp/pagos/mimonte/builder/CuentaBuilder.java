/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaSaveReqDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;

/**
 * @name CuentaBuilder
 * @description Clase que construye objetos relacionados con Cuenta
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
					AfiliacionBuilder.buildAfiliacionSetFromAfiliacionDTOSet(cuentaBaseDTO.getAfiliaciones(),
							cuentaBaseDTO.getLastModifiedBy(), cuentaBaseDTO.getLastModifiedDate()));
			cuenta.setCreatedBy(cuentaBaseDTO.getCreatedBy());
			cuenta.setCreatedDate(cuentaBaseDTO.getCreatedDate());
			cuenta.setDescription(cuentaBaseDTO.getDescription());
			cuenta.setEstatus(null != cuentaBaseDTO.getEstatus() ? cuentaBaseDTO.getEstatus() : true);
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
			cuentaBaseDTO.setAfiliaciones(null != cuenta.getAfiliaciones()
					? AfiliacionBuilder.buildAfiliacionDTOSetFromAfiliacionSet(cuenta.getAfiliaciones())
					: new TreeSet<>());
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
					AfiliacionBuilder.buildAfiliacionReqNNListFromAfiliacionDTOSet(cuentaBaseDTO.getAfiliaciones()));
			cuentaDTO.setEstatus(cuentaBaseDTO.getEstatus());
			cuentaDTO.setId(cuentaBaseDTO.getId());
		}
		return cuentaDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaBaseDTO a partir de un objeto de tipo
	 * CuentaDTO
	 * 
	 * @param cuentaDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static CuentaBaseDTO buildCuentaBaseDTOFromCuentaDTO(CuentaDTO cuentaDTO, Date createdDate,
			Date lastModifiedDate) {
		CuentaBaseDTO cuentaBaseDTO = null;
		if (null != cuentaDTO) {
			cuentaBaseDTO = new CuentaBaseDTO();
			cuentaBaseDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionDTOSetFromAfiliacionReqNNList(cuentaDTO.getAfiliaciones()));
			cuentaBaseDTO.setEstatus(cuentaDTO.getEstatus());
			cuentaBaseDTO.setId(cuentaDTO.getId());
			cuentaBaseDTO.setNumeroCuenta(cuentaDTO.getNumero());
			cuentaBaseDTO.setCreatedDate(createdDate);
			cuentaBaseDTO.setLastModifiedDate(lastModifiedDate);
		}
		return cuentaBaseDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaBaseDTO a partir de un objeto de tipo
	 * CuentaSaveDTO
	 * 
	 * @param cuentaSaveDTO
	 * @param createdDate
	 * @param lastModifiedDate
	 * @return
	 */
	public static CuentaBaseDTO buildCuentaBaseDTOFromCuentaSaveDTO(CuentaSaveDTO cuentaSaveDTO, Date createdDate,
			Date lastModifiedDate) {
		CuentaBaseDTO cuentaBaseDTO = null;
		if (null != cuentaSaveDTO) {
			cuentaBaseDTO = new CuentaBaseDTO();
			cuentaBaseDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionDTOSetFromAfiliacionSaveDTOList(cuentaSaveDTO.getAfiliaciones()));
			cuentaBaseDTO.setNumeroCuenta(cuentaSaveDTO.getNumero());
			cuentaBaseDTO.setCreatedDate(createdDate);
			cuentaBaseDTO.setLastModifiedDate(lastModifiedDate);
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
					AfiliacionBuilder.buildAfiliacionReqNNListFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaDTO.setEstatus(cuenta.getEstatus());
			cuentaDTO.setId(cuenta.getId());
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

	/**
	 * Construye un objeto Cuenta a partir de un objeto de tipo CuentaReqDTO
	 * 
	 * @param cuentaReqDTO
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @return
	 */
	public static Cuenta buildCuentaFromCuentaReqDTO(CuentaReqDTO cuentaReqDTO, String lastModifiedBy,
			Date lastModifiedDate) {
		Cuenta cuenta = null;
		if (null != cuentaReqDTO) {
			cuenta = new Cuenta();
			cuenta.setAfiliaciones(AfiliacionBuilder.buildAfiliacionSetFromAfiliacionReqDTOList(
					cuentaReqDTO.getAfiliaciones(), lastModifiedBy, lastModifiedDate));
			cuenta.setId(cuentaReqDTO.getId());
			cuenta.setEstatus(cuentaReqDTO.getEstatus());
			cuenta.setNumeroCuenta(cuentaReqDTO.getNumero());
			cuenta.setLastModifiedBy(lastModifiedBy);
			cuenta.setLastModifiedDate(lastModifiedDate);
		}
		return cuenta;
	}

	/**
	 * Construye un Set de entities de tipo Cuenta a partir de un Set de objetos de
	 * tipo CuentaReqDTO
	 * 
	 * @param cuentaReqDTOSet
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @return
	 */
	public static Set<Cuenta> buildCuentaSetFromCuentaReqDTOSet(Set<CuentaReqDTO> cuentaReqDTOSet,
			String lastModifiedBy, Date lastModifiedDate) {
		Set<Cuenta> cuentaSet = null;
		if (null != cuentaReqDTOSet && !cuentaReqDTOSet.isEmpty()) {
			cuentaSet = new TreeSet<>();
			for (CuentaReqDTO cuentaReqDTO : cuentaReqDTOSet) {
				cuentaSet.add(buildCuentaFromCuentaReqDTO(cuentaReqDTO, lastModifiedBy, lastModifiedDate));
			}
		}
		return cuentaSet;
	}

	/**
	 * Construye una lista de objetos de tipo CuentaBaseDTO a partir de una lista de
	 * entities de tipo Cuenta
	 * 
	 * @param cuentaList
	 * @return
	 */
	public static List<CuentaBaseDTO> buildCuentaBaseDTOListFromCuentaList(List<Cuenta> cuentaList) {
		List<CuentaBaseDTO> cuentaBaseDTOList = null;
		if (null != cuentaList) {
			cuentaBaseDTOList = new ArrayList<>();
			for (Cuenta cuenta : cuentaList) {
				cuentaBaseDTOList.add(buildCuentaBaseDTOFromCuenta(cuenta));
			}
		}
		return cuentaBaseDTOList;
	}

	/**
	 * Construye un objeto de tipo CuentaEntDTO a partir de un objeto de tipo
	 * CuentaDTO
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static CuentaEntDTO buildCuentaEntDTOFromCuentaDTO(CuentaDTO cuentaDTO) {
		CuentaEntDTO cuentaEntDTO = null;
		if (null != cuentaDTO) {
			cuentaEntDTO = new CuentaEntDTO();
			cuentaEntDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionEntDTOListFromAfiliacionReqNNList(cuentaDTO.getAfiliaciones()));
			cuentaEntDTO.setEstatus(cuentaDTO.getEstatus());
			cuentaEntDTO.setId(cuentaDTO.getId());
		}
		return cuentaEntDTO;
	}

	/**
	 * Construye un objeto de tipo CuentaEntDTO a partir de un objeto de tipo
	 * CuentaBaseDTO
	 * 
	 * @param cuentaBaseDTO
	 * @return
	 */
	public static CuentaEntDTO buildCuentaEntDTOFromCuentaBaseDTO(CuentaBaseDTO cuentaBaseDTO) {
		CuentaEntDTO cuentaEntDTO = null;
		if (null != cuentaBaseDTO) {
			cuentaEntDTO = new CuentaEntDTO();
			cuentaEntDTO.setAfiliaciones(
					AfiliacionBuilder.buildAfiliacionEntDTOListFromAfiliacionDTOSet(cuentaBaseDTO.getAfiliaciones()));
			cuentaEntDTO.setEstatus(cuentaBaseDTO.getEstatus());
			cuentaEntDTO.setId(cuentaBaseDTO.getId());
			cuentaEntDTO.setNumero(cuentaBaseDTO.getNumeroCuenta());
		}
		return cuentaEntDTO;
	}

	/**
	 * Contruye una lista de objetos de tipo CuentaEntDTO a partir de una lista de
	 * entities de tipo Cuenta
	 * 
	 * @param cuentaList
	 * @return
	 */
	public static List<CuentaEntDTO> buildCuentaEntDTOListFromCuentaList(List<Cuenta> cuentaList) {
		List<CuentaEntDTO> cuentaEntDTOList = null;
		if (null != cuentaList) {
			cuentaEntDTOList = new ArrayList<>();
			for (Cuenta cuenta : cuentaList) {
				cuentaEntDTOList.add(buildCuentaEntDTOFromCuenta(cuenta));
			}
		}
		return cuentaEntDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo CuentaEntDTO a aprtir de una lista de
	 * objetos de tipo CuentaBaseDTO
	 * 
	 * @param cuentaBaseDTOList
	 * @return
	 */
	public static List<CuentaEntDTO> buildCuentaEntDTOListFromCuentaBaseDTOList(List<CuentaBaseDTO> cuentaBaseDTOList) {
		List<CuentaEntDTO> cuentaEntDTOList = null;
		if (null != cuentaBaseDTOList) {
			cuentaEntDTOList = new ArrayList<>();
			for (CuentaBaseDTO cuentaBaseDTO : cuentaBaseDTOList) {
				cuentaEntDTOList.add(buildCuentaEntDTOFromCuentaBaseDTO(cuentaBaseDTO));
			}
		}
		return cuentaEntDTOList;
	}

	/**
	 * Construye un objeto de tipo CuentaReqDTO a partir de un objeto de tipo
	 * CuentaSaveReqDTO
	 * 
	 * @param cuentaSaveReqDTO
	 * @return
	 */
	public static CuentaReqDTO buildCuentaReqDTOFromCuentaSaveReqDTO(CuentaSaveReqDTO cuentaSaveReqDTO) {
		CuentaReqDTO cuentaReqDTO = null;
		if (null != cuentaSaveReqDTO) {
			cuentaReqDTO = new CuentaReqDTO();
			cuentaReqDTO.setId(cuentaSaveReqDTO.getId());
			cuentaReqDTO.setAfiliaciones(AfiliacionBuilder
					.buildAfiliacionReqDTOListFromAfiliacionSaveDTOList(cuentaSaveReqDTO.getAfiliaciones()));
		}
		return cuentaReqDTO;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaReqDTO a partir de un Set de
	 * objetos de tipo CuentaSaveReqDTO
	 * 
	 * @param cuentaSaveReqDTOSet
	 * @return
	 */
	public static Set<CuentaReqDTO> buildCuentaReqDTOSetFromCuentaSaveReqDTOSet(
			Set<CuentaSaveReqDTO> cuentaSaveReqDTOSet) {
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		if (null != cuentaSaveReqDTOSet && !cuentaSaveReqDTOSet.isEmpty()) {
			cuentaReqDTOSet = new TreeSet<>();
			for (CuentaSaveReqDTO cuentaSaveReqDTO : cuentaSaveReqDTOSet) {
				cuentaReqDTOSet.add(buildCuentaReqDTOFromCuentaSaveReqDTO(cuentaSaveReqDTO));
			}
		}
		return cuentaReqDTOSet;
	}

}
