/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTOList;
import mx.com.nmp.pagos.mimonte.dto.EntidadCuentaAfiliacionDTO;
import mx.com.nmp.pagos.mimonte.model.EntidadCuentaAfiliacion;

/**
 * @name ContactosBuilder
 * @description Clase de capa de builder que se encarga de convertir difrentes
 *              tipos de objetos y entidades relacionadas con el objeto
 *              EntidadCuentaAfiliacion
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 18/04/2019 00:28 hrs.
 * @version 0.1
 */
public abstract class EntidadCuentaAfiliacionBuilder {

	private EntidadCuentaAfiliacionBuilder() {
		super();
	}

	/**
	 * Construye un Set de objetos de tipo CuentaReqDTO a partir de una lista de
	 * objetos de tipo EntidadCuentaAfiliacion
	 * 
	 * @param entidadCuentaAfiliacionList
	 * @return
	 */
	public static Set<CuentaReqDTO> buildCuentaReqDTOSetFromEntidadCuentaAfiliacionList(
			List<EntidadCuentaAfiliacion> entidadCuentaAfiliacionList) {
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		CuentaReqDTO cuentaReqDTO = null;
		Map<CuentaReqDTO, List<AfiliacionReqDTO>> map = null;
		List<AfiliacionReqDTO> afiliacionReqDTOList = null;
		if (null != entidadCuentaAfiliacionList && !entidadCuentaAfiliacionList.isEmpty()) {
			map = new HashMap<>();
			for (EntidadCuentaAfiliacion entidadCuentaAfiliacion : entidadCuentaAfiliacionList) {
				if (!map.containsKey(CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacion.getCuenta()))) {
					afiliacionReqDTOList = new ArrayList<>();
					afiliacionReqDTOList.add(AfiliacionBuilder
							.buildAfiliacionReqDTOFromAfiliacion(entidadCuentaAfiliacion.getAfiliacion()));
					map.put(CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacion.getCuenta()),
							afiliacionReqDTOList);
				} else if (map
						.containsKey(CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacion.getCuenta()))) {
					if (!map.get(CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacion.getCuenta()))
							.contains(AfiliacionBuilder
									.buildAfiliacionReqDTOFromAfiliacion(entidadCuentaAfiliacion.getAfiliacion()))) {
						map.get(CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacion.getCuenta()))
								.add(AfiliacionBuilder
										.buildAfiliacionReqDTOFromAfiliacion(entidadCuentaAfiliacion.getAfiliacion()));
					}
				}
			}
			if (null != map && !map.isEmpty()) {
				cuentaReqDTOSet = new TreeSet<>();
				for (Map.Entry<CuentaReqDTO, List<AfiliacionReqDTO>> entry : map.entrySet()) {
					cuentaReqDTO = entry.getKey();
					if (null != cuentaReqDTO) {
						cuentaReqDTO.setAfiliaciones(entry.getValue());
					}
				}
			}
		}
		return cuentaReqDTOSet;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaReqDTO a partir de una lista de
	 * objetos de tipo EntidadCuentaAfiliacionDTO
	 * 
	 * @param entidadCuentaAfiliacionDTOList
	 * @return
	 */
	public static Set<CuentaReqDTO> buildCuentaReqDTOSetFromEntidadCuentaAfiliacionDTOList(
			List<EntidadCuentaAfiliacionDTO> entidadCuentaAfiliacionDTOList) {
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		CuentaReqDTO cuentaReqDTO = null;
		Map<Long, CuentaReqDTOList> map = null;
		CuentaReqDTOList innerObj = null;
		List<AfiliacionReqDTO> afiliacionReqDTOList = null;
		if (null != entidadCuentaAfiliacionDTOList && !entidadCuentaAfiliacionDTOList.isEmpty()) {
			map = new HashMap<>();
			for (EntidadCuentaAfiliacionDTO entidadCuentaAfiliacionDTO : entidadCuentaAfiliacionDTOList) {
				if (!map.containsKey(entidadCuentaAfiliacionDTO.getCuenta().getId())) {
					innerObj = new CuentaReqDTOList();
					afiliacionReqDTOList = new ArrayList<>();
					afiliacionReqDTOList.add(AfiliacionBuilder
							.buildAfiliacionReqDTOFromAfiliacion(entidadCuentaAfiliacionDTO.getAfiliacion()));
					innerObj.setCuentaReqDTO(
							CuentaBuilder.buildCuentaReqDTOFromCuenta(entidadCuentaAfiliacionDTO.getCuenta()));
					innerObj.setAfiliacionReqDTOList(afiliacionReqDTOList);
					map.put(entidadCuentaAfiliacionDTO.getCuenta().getId(), innerObj);
				} else if (map.containsKey(entidadCuentaAfiliacionDTO.getCuenta().getId())) {
					map.get(entidadCuentaAfiliacionDTO.getCuenta().getId()).getAfiliacionReqDTOList()
							.add(AfiliacionBuilder
									.buildAfiliacionReqDTOFromAfiliacion(entidadCuentaAfiliacionDTO.getAfiliacion()));
				}
			}
			if (null != map && !map.isEmpty()) {
				cuentaReqDTOSet = new TreeSet<>();
				for (Map.Entry<Long, CuentaReqDTOList> entry : map.entrySet()) {
					cuentaReqDTO = entry.getValue().getCuentaReqDTO();
					cuentaReqDTO.setAfiliaciones(entry.getValue().getAfiliacionReqDTOList());
					cuentaReqDTOSet.add(cuentaReqDTO);
				}
			}
		}
		return cuentaReqDTOSet;
	}

	/**
	 * Construye un objeto de tipo CuentaEntDTO a partir de un objeto de tipo
	 * EntidadCuentaAfiliacion
	 * 
	 * @param entidadCuentaAfiliacion
	 * @return
	 */
	public static CuentaEntDTO buildCuentaEntDTOFromEntidadCuentaAfiliacion(
			EntidadCuentaAfiliacion entidadCuentaAfiliacion) {
		CuentaEntDTO cuentaEntDTO = null;
		if (null != entidadCuentaAfiliacion) {
			cuentaEntDTO = new CuentaEntDTO();
			cuentaEntDTO.setNumero(entidadCuentaAfiliacion.getCuenta().getNumeroCuenta());
			cuentaEntDTO.setId(entidadCuentaAfiliacion.getCuenta().getId());
			cuentaEntDTO.setEstatus(entidadCuentaAfiliacion.getCuenta().getEstatus());
			cuentaEntDTO.setAfiliaciones(AfiliacionBuilder
					.buildAfiliacionEntDTOListFromAfiliacionSet(entidadCuentaAfiliacion.getCuenta().getAfiliaciones()));
		}
		return cuentaEntDTO;
	}

	/**
	 * Construye un Set de objetos de tipo CuentaEntDTO a partir de una lista de
	 * objetos de tipo EntidadCuentaAfiliacion
	 * 
	 * @param entidadCuentaAfiliacionList
	 * @return
	 */
	public static Set<CuentaEntDTO> buildCuentaEntDTOSetFromEntidadCuentaAfiliacionList(
			List<EntidadCuentaAfiliacion> entidadCuentaAfiliacionList) {
		Set<CuentaEntDTO> cuentaEntDTO = null;
		if (null != entidadCuentaAfiliacionList && !entidadCuentaAfiliacionList.isEmpty()) {
			cuentaEntDTO = new TreeSet<>();
			for (EntidadCuentaAfiliacion entidadCuentaAfiliacion : entidadCuentaAfiliacionList) {
				cuentaEntDTO.add(buildCuentaEntDTOFromEntidadCuentaAfiliacion(entidadCuentaAfiliacion));
			}
		}
		return cuentaEntDTO;
	}

	/**
	 * Construye un objeto de tipo EntidadCuentaAfiliacionDTO a partir de un objeto
	 * de tipo EntidadCuentaAfiliacion
	 * 
	 * @param entidadCuentaAfiliacion
	 * @return
	 */
	public static EntidadCuentaAfiliacionDTO buildEntidadCuentaAfiliacionDTOFromEntidadCuentaAfiliacion(
			EntidadCuentaAfiliacion entidadCuentaAfiliacion) {
		EntidadCuentaAfiliacionDTO entidadCuentaAfiliacionDTO = null;
		if (null != entidadCuentaAfiliacion) {
			entidadCuentaAfiliacionDTO = new EntidadCuentaAfiliacionDTO();
			entidadCuentaAfiliacionDTO.setEntidad(entidadCuentaAfiliacion.getEntidad());
			entidadCuentaAfiliacionDTO.setCuenta(entidadCuentaAfiliacion.getCuenta());
			entidadCuentaAfiliacionDTO.setAfiliacion(entidadCuentaAfiliacion.getAfiliacion());
		}
		return entidadCuentaAfiliacionDTO;
	}

	/**
	 * Construye una lista de objetos de tipo EntidadCuentaAfiliacionDTO a partir de
	 * una lista de objetos de tipo EntidadCuentaAfiliacion
	 * 
	 * @param entidadCuentaAfiliacionList
	 * @return
	 */
	public static List<EntidadCuentaAfiliacionDTO> buildEntidadCuentaAfiliacionDTOListFromEntidadCuentaAfiliacionList(
			List<EntidadCuentaAfiliacion> entidadCuentaAfiliacionList) {
		List<EntidadCuentaAfiliacionDTO> entidadCuentaAfiliacionDTO = null;
		if (null != entidadCuentaAfiliacionList) {
			entidadCuentaAfiliacionDTO = new ArrayList<>();
			for (EntidadCuentaAfiliacion entidadCuentaAfiliacion : entidadCuentaAfiliacionList) {
				entidadCuentaAfiliacionDTO
						.add(buildEntidadCuentaAfiliacionDTOFromEntidadCuentaAfiliacion(entidadCuentaAfiliacion));
			}
		}
		return entidadCuentaAfiliacionDTO;
	}
}
