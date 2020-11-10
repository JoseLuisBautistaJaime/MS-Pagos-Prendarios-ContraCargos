/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestMultipleDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TotalConciliacionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TotalDevolucionesLiquidadasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TotalProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TotalResumenDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Proveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

/**
 * Nombre: ConciliacionBuilder Descripcion: Clase de capa de builder que se
 * encarga de convertir difrentes tipos de objetos y entidades relacionadas con
 * la conciliación
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 06/05/2019 13:21 hrs.
 * @version 0.1
 */
public abstract class ConciliacionBuilder {

	private ConciliacionBuilder() {
		super();
	}

	/**
	 * Construye una lista de datos tipo Long a partir de una lista de datos tipo Object
	 * @param objectList
	 * @return
	 */
	public static List<Long> buildLongListFromObjectList(List<Object> objectList){
		List<Long> longList = null;
		if(null != objectList && !objectList.isEmpty()) {
			longList = new ArrayList<>();
			for(Object obj : objectList) {
				longList.add(Long.valueOf(obj.toString()));
			}
		}
		return longList;
	}
	
	/**
	 * Construye un objeto de tipo ConciliacionResponseSaveDTO a partir de un objeto
	 * de tipo ConciliacionDTO.
	 * 
	 * @param conciliacionDTO
	 * @return conciliacionResponseSaveDTO
	 */
	public static ConciliacionResponseSaveDTO buildConciliacionResponseSaveDTOFromConciliacionDTO(
			ConciliacionDTO conciliacionDTO) {

		ConciliacionResponseSaveDTO conciliacionResponseSaveDTO = null;
		if (conciliacionDTO != null) {
			conciliacionResponseSaveDTO = new ConciliacionResponseSaveDTO();
			conciliacionResponseSaveDTO.setFolio(conciliacionDTO.getFolio());
			conciliacionResponseSaveDTO.setCreatedDate(conciliacionDTO.getCreatedDate());
			conciliacionResponseSaveDTO.setLastModifiedDate(null);
			conciliacionResponseSaveDTO.setCreatedBy(conciliacionDTO.getCreatedBy());
			conciliacionResponseSaveDTO.setLastModifiedBy(null);
			conciliacionResponseSaveDTO.setEstatus(conciliacionDTO.getEstatus()); // 1.- Finalizado 2.- En Proceso
			conciliacionResponseSaveDTO.setEntidad(conciliacionDTO.getEntidad());
			conciliacionResponseSaveDTO.setCuenta(conciliacionDTO.getCuenta());
			conciliacionResponseSaveDTO.setSubEstatus(conciliacionDTO.getSubEstatus()); // 16 estados
			conciliacionResponseSaveDTO.setSubEstatusDescripcion(conciliacionDTO.getSubEstatusDescripcion());
			conciliacionResponseSaveDTO.setIdAsientoContable(conciliacionDTO.getIdAsientoContable());
			conciliacionResponseSaveDTO.setIdTesoreria(conciliacionDTO.getIdPolizaTesoreria());
			conciliacionResponseSaveDTO.setReporteProcesosNocturnos(null);
			conciliacionResponseSaveDTO.setReporteProveedorTransaccional(null);
			conciliacionResponseSaveDTO.setReporteEstadoCuenta(null);
			conciliacionResponseSaveDTO.setGlobal(null);
			conciliacionResponseSaveDTO.setDevoluciones(null);
			conciliacionResponseSaveDTO.setMovimientosTransito(null);
			conciliacionResponseSaveDTO.setComisiones(null);
			conciliacionResponseSaveDTO.setIdCorresponsal(null != conciliacionDTO.getCorresponsal() ? conciliacionDTO.getCorresponsal().getNombre(): null);
		}
		return conciliacionResponseSaveDTO;
	}

	/**
	 * Construye un objeto de tipo ConciliacionResponseSaveDTO a partir de un objeto
	 * de tipo ConciliacionDTO.
	 * 
	 * @param conciliacionDTO
	 * @return conciliacionResponseSaveDTO
	 */
	public static ConciliacionResponseSaveDTO buildConciliacionResponseSaveDTOFromConciliacionRequestDTO(
			ConciliacionRequestDTO conciliacionRequestDTO, Date createdDate, Date lastModifiedDate, String createdBy) {

		ConciliacionResponseSaveDTO conciliacionResponseSaveDTO = null;
		if (conciliacionRequestDTO != null) {
			conciliacionResponseSaveDTO = new ConciliacionResponseSaveDTO();
//			conciliacionResponseSaveDTO.setFolio(folio); ------> Se genera autoincrement
			conciliacionResponseSaveDTO.setCreatedDate(createdDate);
			conciliacionResponseSaveDTO.setLastModifiedDate(null);
			conciliacionResponseSaveDTO.setCreatedBy(createdBy);
			conciliacionResponseSaveDTO.setLastModifiedBy(null);
			conciliacionResponseSaveDTO.setEstatus(null); // 1.- Finalizado 2.- En Proceso
			conciliacionResponseSaveDTO.setEntidad(new EntidadDTO(conciliacionRequestDTO.getIdEntidad()));
			conciliacionResponseSaveDTO.setCuenta(new CuentaDTO(conciliacionRequestDTO.getIdCuenta()));
			conciliacionResponseSaveDTO.setSubEstatus(null); // 16 subEstados
			conciliacionResponseSaveDTO.setReporteProcesosNocturnos(null);
			conciliacionResponseSaveDTO.setReporteProveedorTransaccional(null);
			conciliacionResponseSaveDTO.setReporteEstadoCuenta(null);
			conciliacionResponseSaveDTO.setGlobal(null);
			conciliacionResponseSaveDTO.setDevoluciones(null);
			conciliacionResponseSaveDTO.setMovimientosTransito(null);
			conciliacionResponseSaveDTO.setComisiones(null);
			// Se setea el corresponsal, si es nulo se setea por default el corresponsal OPEN_PAY
			if(null != conciliacionRequestDTO.getIdCorresponsal())
				conciliacionResponseSaveDTO.setIdCorresponsal(null != conciliacionRequestDTO.getIdCorresponsal() ? conciliacionRequestDTO.getIdCorresponsal().toUpperCase() : null );
			else
				conciliacionResponseSaveDTO.setIdCorresponsal(CorresponsalEnum.OPENPAY.getNombre());
		}
		return conciliacionResponseSaveDTO;
	}

	/**
	 * Construye un objeto de tipo ConciliacionDTO a partir de una entidad de tipo
	 * Conciliacion.
	 * 
	 * @param conciliacion
	 * @return concilicionDTO
	 */
	public static ConciliacionDTO buildConciliacionDTOFromConciliacion(Conciliacion conciliacion) {
		ConciliacionDTO conciliacionDTO = null;
		if (conciliacion != null) {
			conciliacionDTO = new ConciliacionDTO();
			conciliacionDTO.setFolio(conciliacion.getId());
			conciliacionDTO.setCreatedDate(conciliacion.getCreatedDate());
			conciliacionDTO.setLastModifiedDate(conciliacion.getLastModifiedDate());
			conciliacionDTO.setCreatedBy(conciliacion.getCreatedBy());
			conciliacionDTO.setLastModifiedBy(conciliacion.getLastModifiedBy());
			conciliacionDTO.setEstatus(EstatusConciliacionBuilder
					.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			conciliacionDTO.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(conciliacion.getEntidad()));
			conciliacionDTO.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(conciliacion.getCuenta()));
			conciliacionDTO.setSubEstatus(SubEstatusConciliacionBuilder
					.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
			conciliacionDTO.setSubEstatusDescripcion(null);
			conciliacionDTO.setIdAsientoContable(null);
			conciliacionDTO.setIdPolizaTesoreria(null);
			conciliacionDTO.setReporteProcesosNocturnos(null);
			conciliacionDTO.setReporteProveedorTransaccional(null);
			conciliacionDTO.setReporteEstadoCuenta(null);
			conciliacionDTO.setGlobal(null);
			conciliacionDTO.setDevoluciones(null);
			conciliacionDTO.setMovimientosTransito(null);
			conciliacionDTO.setComisiones(null);
		}
		return conciliacionDTO;
	}

	/**
	 * Construye un objeto de tipo ConciliacionDTO a partir de los entities de tipo
	 * Conciliacion, Cuenta y Entidad
	 * 
	 * @param conciliacion
	 * @param cuenta
	 * @param entidad
	 * @return
	 */
	public static ConciliacionDTO buildConciliacionDTOFromConciliacionCuentaAndEntidad(Conciliacion conciliacion,
			Cuenta cuenta, Entidad entidad) {
		ConciliacionDTO conciliacionDTO = null;
		if (conciliacion != null) {
			conciliacionDTO = new ConciliacionDTO();
			conciliacionDTO.setFolio(conciliacion.getId());
			conciliacionDTO.setCreatedDate(conciliacion.getCreatedDate());
			conciliacionDTO.setLastModifiedDate(conciliacion.getLastModifiedDate());
			conciliacionDTO.setCreatedBy(conciliacion.getCreatedBy());
			conciliacionDTO.setLastModifiedBy(conciliacion.getLastModifiedBy());
			conciliacionDTO.setEstatus(EstatusConciliacionBuilder
					.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			conciliacionDTO.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(entidad));
			conciliacionDTO.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(cuenta));
			conciliacionDTO.setSubEstatus(SubEstatusConciliacionBuilder
					.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
			conciliacionDTO.setSubEstatusDescripcion(null);
			conciliacionDTO.setIdAsientoContable(null);
			conciliacionDTO.setIdPolizaTesoreria(null);
			conciliacionDTO.setReporteProcesosNocturnos(null);
			conciliacionDTO.setReporteProveedorTransaccional(null);
			conciliacionDTO.setReporteEstadoCuenta(null);
			conciliacionDTO.setGlobal(null);
			conciliacionDTO.setDevoluciones(null);
			conciliacionDTO.setMovimientosTransito(null);
			conciliacionDTO.setComisiones(null);
			conciliacionDTO.setCorresponsal(null != conciliacion.getProveedor() ? CorresponsalEnum.getByNombre(conciliacion.getProveedor().getNombre()): null);
		}
		return conciliacionDTO;
	}

	/**
	 * 
	 * Construye un objeto tipo Conciliacion a partir de un objeto de tipo
	 * ConciliacionDTO.
	 * 
	 * @param conciliacionDTO
	 * @return conciliacion
	 */
	public static Conciliacion buildConciliacionFromConciliacionResponseSaveDTO(
			ConciliacionResponseSaveDTO conciliacionResponseSaveDTO) {
		Conciliacion conciliacion = null;
		if (conciliacionResponseSaveDTO != null) {
			conciliacion = new Conciliacion();
			conciliacion.setCreatedDate(conciliacionResponseSaveDTO.getCreatedDate());
			conciliacion.setLastModifiedDate(conciliacionResponseSaveDTO.getLastModifiedDate());
			conciliacion.setCreatedBy(conciliacionResponseSaveDTO.getCreatedBy());
			conciliacion.setLastModifiedBy(conciliacionResponseSaveDTO.getLastModifiedBy());
			conciliacion.setEstatus(EstatusConciliacionBuilder
					.buildEstatusConciliacionFromEstatusConciliacionDTO(conciliacionResponseSaveDTO.getEstatus()));
			conciliacion
					.setEntidad(EntidadBuilder.buildEntidadFromEntidadDTO(conciliacionResponseSaveDTO.getEntidad()));
			conciliacion.setCuenta(CuentaBuilder.buildCuentaFromCuentaDTO(conciliacionResponseSaveDTO.getCuenta()));
			conciliacion.setSubEstatus(
					SubEstatusConciliacionBuilder.buildSubEstatusConciliacionFromSubEstatusConciliacionDTO(
							conciliacionResponseSaveDTO.getSubEstatus()));
			conciliacion.setSubEstatusDescripcion(null);
			conciliacion.setIdAsientoContable(null);
			conciliacion.setIdPolizaTesoreria(null);
			conciliacion.setProveedor(new Proveedor(conciliacionResponseSaveDTO.getIdCorresponsal()));
		}
		return conciliacion;
	}

	/**
	 * Construye un objeto de tipo ConsultaConciliacionDTO a partir de una entidad
	 * Conciliacion
	 * 
	 * @param conciliacion
	 * @return consultaConciliacionDTO
	 */
	public static ConsultaConciliacionDTO buildConsultaConciliacionDTOFromConciliacion(Conciliacion conciliacion) {
		ConsultaConciliacionDTO consultaConciliacionDTO = null;
		if (conciliacion != null) {
			consultaConciliacionDTO = new ConsultaConciliacionDTO();
			consultaConciliacionDTO.setFolio(conciliacion.getId());
			consultaConciliacionDTO.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(conciliacion.getCuenta()));
			consultaConciliacionDTO.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(conciliacion.getEntidad()));
			consultaConciliacionDTO.setEstatus(EstatusConciliacionBuilder
					.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			consultaConciliacionDTO.setSubEstatus(SubEstatusConciliacionBuilder
					.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
			consultaConciliacionDTO.setCreatedBy(conciliacion.getCreatedBy());
			consultaConciliacionDTO.setCreatedDate(conciliacion.getCreatedDate());
			consultaConciliacionDTO.setLastModifiedBy(conciliacion.getLastModifiedBy());
			consultaConciliacionDTO.setLastModifiedDate(conciliacion.getCompletedDate());
			consultaConciliacionDTO.setIdAsientoContable(conciliacion.getIdAsientoContable());
			consultaConciliacionDTO.setIdPolizaTesoreria(conciliacion.getIdPolizaTesoreria());
		}
		return consultaConciliacionDTO;
	}

	/**
	 * Construye un objeto de tipo List<ConsultaConciliacionDTO> a partir un entitie
	 * de tipo List<Conciliacion>.
	 * 
	 * @param conciliacionList
	 * @return
	 */
	public static List<ConsultaConciliacionDTO> buildConsultaConciliacionDTOListFromConciliacionList(
			List<Conciliacion> conciliacionList) {
		List<ConsultaConciliacionDTO> ConsultaConciliacionDTOList = null;
		if (conciliacionList != null && !conciliacionList.isEmpty()) {
			ConsultaConciliacionDTOList = new ArrayList<>();
			for (Conciliacion conciliacion : conciliacionList) {
				ConsultaConciliacionDTOList.add(buildConsultaConciliacionDTOFromConciliacion(conciliacion));
			}
		}
		return ConsultaConciliacionDTOList;

	}

	/**
	 * Construye un objeto de tipo ConciliacionDTOList a partir de una entidad de
	 * tipo Conciliacion
	 * 
	 * @param conciliacion
	 * @return conciliacionDTOList
	 */
	public static ConciliacionDTOList buildConciliacionDTOListFromConciliacion(Conciliacion conciliacion,
			List<Reporte> reportes, List<DevolucionConDTO> devolucionConDTOList,
			List<MovTransitoDTO> movTransitoDTOList, List<ComisionesDTO> comisionesDTOList,
			ComisionesTransDTO comisionesTranDTO) {
		ConciliacionDTOList conciliacionDTOList = null;
		if (conciliacion != null) {
			conciliacionDTOList = new ConciliacionDTOList();
			conciliacionDTOList.setFolio(conciliacion.getId());
			conciliacionDTOList.setEstatus(EstatusConciliacionBuilder
					.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			conciliacionDTOList.setSubEstatus(SubEstatusConciliacionBuilder
					.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
			conciliacionDTOList.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(conciliacion.getEntidad()));
			conciliacionDTOList.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(conciliacion.getCuenta()));
			conciliacionDTOList.setReporteProcesosNocturnos(
					ReporteProcesosNocturnosBuilder.buildReporteProcesosNocturnosDTOSetFromReporteSet(reportes));
			conciliacionDTOList.setReporteProveedorTransaccional(ReporteProveedorTransaccionalBuilder
					.buildReporteProveedorTransaccionalDTOFromReporteList(reportes));
			conciliacionDTOList.setReporteEstadoCuenta(
					ReporteEstadoCuentaBuilder.buildReporteEstadoCuentaDTOFromReporteList(reportes));
			conciliacionDTOList.setGlobal(GlobalBuilder.buildGlobalDTOFromGlobal(conciliacion.getGlobal()));
			conciliacionDTOList.setDevoluciones(devolucionConDTOList);
			conciliacionDTOList.setMovimientosTransito(movTransitoDTOList);
			conciliacionDTOList.setComisiones(comisionesDTOList);
			conciliacionDTOList.setComisionesTransacciones(comisionesTranDTO);
			conciliacionDTOList.setCreatedDate(conciliacion.getCreatedDate());
			conciliacionDTOList.setLastModifiedDate(conciliacion.getLastModifiedDate());
			conciliacionDTOList.setCreatedBy(conciliacion.getCreatedBy());
			conciliacionDTOList.setLastModifiedBy(conciliacion.getLastModifiedBy());
			conciliacionDTOList.setIdAsientoContable(conciliacion.getIdAsientoContable());
			conciliacionDTOList.setIdTesoreria(conciliacion.getIdPolizaTesoreria());
		}
		return conciliacionDTOList;
	}

	/**
	 * Convierte un mapa con los totales por tipos movimientos de resumen de
	 * conciliacion en el correspondiente DTO de respuesta de dicho endpoint
	 * 
	 * @param map
	 * @return
	 */
	public static ResumenConciliacionResponseDTO buildResumenConciliacionResponseDTOFromMap(Map<String, Object> map){
		// Objetos necesarios

		ResumenConciliacionResponseDTO resumenConciliacionResponseDTO = null;
		TotalProcesoDTO totalProcesoDTO = null;
		TotalConciliacionesDTO totalConciliacionesDTO = null;
		TotalDevolucionesLiquidadasDTO totalDevolucionesLiquidadasDTO = null;
		// Valida que el mapa no sea nulo o vacio
		if (null != map && !map.isEmpty()) {
			resumenConciliacionResponseDTO = new ResumenConciliacionResponseDTO();

			// Se setean los valores de cada sub-objeto
			totalProcesoDTO = (TotalProcesoDTO) buildTotalProcesoDTOFromMap(new TotalProcesoDTO(), map);
			totalDevolucionesLiquidadasDTO = (TotalDevolucionesLiquidadasDTO) buildTotalProcesoDTOFromMap(
					new TotalDevolucionesLiquidadasDTO(), map);
			totalConciliacionesDTO = (TotalConciliacionesDTO) buildTotalProcesoDTOFromMap(new TotalConciliacionesDTO(),
					map);

			// Se setean los valores al objeto principal
			resumenConciliacionResponseDTO.setTotalProceso(totalProcesoDTO);
			resumenConciliacionResponseDTO.setTotalDevolucionesLiquidadas(totalDevolucionesLiquidadasDTO);
			resumenConciliacionResponseDTO.setTotalConciliaciones(totalConciliacionesDTO);
		}
		return resumenConciliacionResponseDTO;
	}

	/**
	 * Construye un sub-objeto de TotalResumenDTO en base a un mapa de resultados
	 * 
	 * @param obj
	 * @param map
	 * @return
	 */
	public static TotalResumenDTO buildTotalProcesoDTOFromMap(TotalResumenDTO obj, Map<String, Object> map) {
		TotalResumenDTO totalResumenDTO = null;

		// Se crea la instancia correcta
		if (obj instanceof TotalProcesoDTO) {
			totalResumenDTO = new TotalProcesoDTO();
		} else if (obj instanceof TotalDevolucionesLiquidadasDTO) {
			totalResumenDTO = new TotalDevolucionesLiquidadasDTO();
		} else if (obj instanceof TotalConciliacionesDTO) {
			totalResumenDTO = new TotalConciliacionesDTO();
		}

		// Se setean las propiedades
		if (null != totalResumenDTO) {
			totalResumenDTO.setTotal(null != map.get(TotalResumenDTO.Tipos.P_TOTAL.getDescripcion())
					? ((BigInteger) map.get(TotalResumenDTO.Tipos.P_TOTAL.getDescripcion())).longValue()
					: null);
			if (null != map.get(TotalResumenDTO.Tipos.P_FECHA_INICIO.getDescripcion())) {
				totalResumenDTO
						.setFechaInicio((Timestamp) map.get(TotalResumenDTO.Tipos.P_FECHA_INICIO.getDescripcion()));
			}
			if (null != map.get(TotalResumenDTO.Tipos.P_FECHA_FIN.getDescripcion())) {
				totalResumenDTO.setFechaFin((Timestamp) map.get(TotalResumenDTO.Tipos.P_FECHA_FIN.getDescripcion()));
			}
		}
		return totalResumenDTO;
	}

	/**
	 * Convierte un arreglo de objetos con el id de conciliacion y sus movimientos globales en un mapa con la misma informacion
	 * @param objectArray
	 * @return
	 */
	public static Map<Long, Integer> buildConciliacionMovsMapFromObjArray(List<Object[]> objectArray){
		Map<Long, Integer> map = null;
		if(null != objectArray && !objectArray.isEmpty()) {
			map = new HashMap<>();
			for(Object[] obj : objectArray) {
				map.put(Long.parseLong(obj[0].toString()), Integer.parseInt(obj[1].toString()));
			}
		}
		return map;
	}
	
}
