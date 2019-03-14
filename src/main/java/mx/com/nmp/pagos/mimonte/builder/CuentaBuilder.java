package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
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
			cuentaDTO.setAfiliaciones(new ArrayList<>(cuentaBaseDTO.getAfiliaciones()));
			cuentaDTO.setCreatedBy(cuentaBaseDTO.getCreatedBy());
			cuentaDTO.setCreatedDate(cuentaBaseDTO.getCreatedDate());
			cuentaDTO.setEstatus(cuentaBaseDTO.getEstatus());
			cuentaDTO.setId(cuentaBaseDTO.getId());
			cuentaDTO.setLastModifiedBy(cuentaBaseDTO.getLastModifiedBy());
			cuentaDTO.setLastModifiedDate(cuentaBaseDTO.getLastModifiedDate());
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
			Set<AfiliacionDTO> targetSet = new TreeSet<>();
			org.apache.commons.collections4.CollectionUtils.addAll(targetSet, cuentaDTO.getAfiliaciones());
			cuentaBaseDTO.setAfiliaciones(targetSet);
			cuentaBaseDTO.setCreatedBy(cuentaDTO.getCreatedBy());
			cuentaBaseDTO.setCreatedDate(cuentaDTO.getCreatedDate());
			cuentaBaseDTO.setEstatus(cuentaDTO.getEstatus());
			cuentaBaseDTO.setId(cuentaDTO.getId());
			cuentaBaseDTO.setLastModifiedBy(cuentaDTO.getLastModifiedBy());
			cuentaBaseDTO.setLastModifiedDate(cuentaDTO.getLastModifiedDate());
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
					AfiliacionBuilder.buildAfiliacionDTOListFromAfiliacionSet(cuenta.getAfiliaciones()));
			cuentaDTO.setCreatedBy(cuenta.getCreatedBy());
			cuentaDTO.setCreatedDate(cuenta.getCreatedDate());
			cuentaDTO.setEstatus(cuenta.getEstatus());
			cuentaDTO.setId(cuenta.getId());
			cuentaDTO.setLastModifiedBy(cuenta.getLastModifiedBy());
			cuentaDTO.setLastModifiedDate(cuenta.getLastModifiedDate());
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

}
