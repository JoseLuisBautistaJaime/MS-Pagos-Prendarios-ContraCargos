/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProveedorTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
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

	public ConciliacionBuilder() {
		super();
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
			conciliacionResponseSaveDTO.setSubEstatus(null);
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
			conciliacionDTO.setSubEstatus(SubEstatusConciliacionBuilder.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
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
			conciliacion.setSubEstatus(SubEstatusConciliacionBuilder.buildSubEstatusConciliacionFromSubEstatusConciliacionDTO(conciliacionResponseSaveDTO.getSubEstatus()));
//			conciliacion.setSubEstatusDescripcion(conciliacionResponseSaveDTO.getSubEstatusDescripcion());
//			conciliacion.setIdAsientoContable(conciliacionResponseSaveDTO.getIdAsientoContable());
//			conciliacion.setIdPolizaTesoreria(conciliacionResponseSaveDTO.getIdTesoreria());
		}
		return conciliacion;
	}
	
	/**
	 * Construye un objeto de tipo ConsultaConciliacionDTO a partir de una entidad Conciliacion
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
			consultaConciliacionDTO.setEstatus(EstatusConciliacionBuilder.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			consultaConciliacionDTO.setSubEstatus(SubEstatusConciliacionBuilder.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(conciliacion.getSubEstatus()));
//			consultaConciliacionDTO.setSubEstatusDescripcion(conciliacion.getSubEstatusDescripcion());
//			consultaConciliacionDTO.setIdAsientoContable(conciliacion.getIdAsientoContable());
//			consultaConciliacionDTO.setIdPolizaTesoreria(conciliacion.getIdPolizaTesoreria());
//			consultaConciliacionDTO.setNumeroMovimientos(null);
			consultaConciliacionDTO.setCreatedBy(conciliacion.getCreatedBy());
			consultaConciliacionDTO.setCreatedDate(conciliacion.getCreatedDate());
			consultaConciliacionDTO.setLastModifiedBy(conciliacion.getLastModifiedBy());
			consultaConciliacionDTO.setLastModifiedDate(conciliacion.getCompletedDate());
		}
		return consultaConciliacionDTO;
	}
	
	
	public static List<ConsultaConciliacionDTO> buildConsultaConciliacionDTOListFromConciliacionList(List<Conciliacion> conciliacionList){
		List<ConsultaConciliacionDTO> ConsultaConciliacionDTOList = null;
		if(conciliacionList != null && !conciliacionList.isEmpty()) {
			ConsultaConciliacionDTOList = new ArrayList<>();
			for( Conciliacion conciliacion : conciliacionList) {
				ConsultaConciliacionDTOList.add(buildConsultaConciliacionDTOFromConciliacion(conciliacion));
			}
		}
		return ConsultaConciliacionDTOList;
		
	}
	
	
	/**
	 * Construye una entidad de tipo Conciliacion a partir de un objeto de tipo ConsultaConciliacionDTO.
	 * @param consultaConciliacionDTO
	 * @return conciliacion
	 */
	public static Conciliacion buildConciliacionFromConsultaConciliacionDTO(ConsultaConciliacionDTO consultaConciliacionDTO) {
		Conciliacion conciliacion = null;
		if(consultaConciliacionDTO != null) {
			conciliacion = new Conciliacion();
			conciliacion.setId(consultaConciliacionDTO.getFolio());
			conciliacion.setCuenta(CuentaBuilder.buildCuentaFromCuentaDTO(consultaConciliacionDTO.getCuenta()));
			conciliacion.setEntidad(EntidadBuilder.buildEntidadFromEntidadDTO(consultaConciliacionDTO.getEntidad()));
			conciliacion.setEstatus(EstatusConciliacionBuilder.buildEstatusConciliacionFromEstatusConciliacionDTO(consultaConciliacionDTO.getEstatus()));
//			conciliacion.setNumeroMovimientos();
			conciliacion.setCreatedBy(consultaConciliacionDTO.getCreatedBy());
			conciliacion.setCreatedDate(consultaConciliacionDTO.getCreatedDate());
			conciliacion.setLastModifiedBy(consultaConciliacionDTO.getLastModifiedBy());
			conciliacion.setLastModifiedDate(consultaConciliacionDTO.getLastModifiedDate());
		}
		return conciliacion;
	}
	
	/**
	 * Construye un objeto de tipo ConciliacionDTOList a partir de una entidad de tipo Conciliacion
	 * @param conciliacion
	 * @return conciliacionDTOList
	 */
	public static ConciliacionDTOList buildConciliacionDTOListFromConciliacion(Conciliacion conciliacion,
			Global global,
			Reporte reporte,
			List<DevolucionConDTO> devolucionConDTOList, 
			List<MovTransitoDTO> movTransitoDTOList,
			List<ComisionesDTO> comisionesDTOList) {
		ConciliacionDTOList conciliacionDTOList = null;
		if(conciliacion != null) {
			conciliacionDTOList = new ConciliacionDTOList();
			conciliacionDTOList.setFolio(conciliacion.getId());
			conciliacionDTOList.setEstatus(EstatusConciliacionBuilder.buildEstatusConciliacionDTOFromEstatusConciliacion(conciliacion.getEstatus()));
			conciliacionDTOList.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(conciliacion.getEntidad()));
			conciliacionDTOList.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(conciliacion.getCuenta()));
			conciliacionDTOList.setReporteProcesosNocturnos(ReporteProcesosNocturnosBuilder.buildReporteProcesosNocturnosDTOSetFromReporteSet(conciliacion.getReportes()));
			conciliacionDTOList.setReporteProveedorTransaccional(ReporteProveedorTransaccionalBuilder.buildReporteProveedorTransaccionalDTOSetFromReporteSet(conciliacion.getReportes()));
			conciliacionDTOList.setReporteEstadoCuenta(ReporteEstadoCuentaBuilder.buildReporteEstadoCuentaDTOSetFromReporteSet(conciliacion.getReportes()));
			conciliacionDTOList.setGlobal(GlobalBuilder.buildGlobalDTOSetFromGlobalSet(conciliacion.getGlobal()));
			conciliacionDTOList.setDevoluciones(devolucionConDTOList);
			conciliacionDTOList.setMovimientosTransito(movTransitoDTOList);
			conciliacionDTOList.setComisiones(comisionesDTOList);
			conciliacionDTOList.setCreatedDate(conciliacion.getCreatedDate());
			conciliacionDTOList.setLastModifiedDate(conciliacion.getLastModifiedDate());
			conciliacionDTOList.setCreatedBy(conciliacion.getCreatedBy());
			conciliacionDTOList.setLastModifiedBy(conciliacion.getLastModifiedBy());
		}
		return conciliacionDTOList;
	}
	
	public static List<ConciliacionDTOList> buildConciliacionDTOListFromConciliacionList(List<Conciliacion> conciliacionList){
		List<ConciliacionDTOList> ConciliacionDTOList = null;
		if(conciliacionList != null && !conciliacionList.isEmpty()) {
			ConciliacionDTOList = new ArrayList<>();
			for( Conciliacion conciliacion : conciliacionList) {
				ConciliacionDTOList.add(buildConciliacionDTOListFromConciliacion(conciliacion, null, null, null, null, null));
			}
		}
		return ConciliacionDTOList;
		
	}

	/**
	 * Construye una entidad de tipo Conciliacion a partir de un objeto de tipo ConciliacionDTOList
	 * @param conciliacionDTOList
	 * @return conciliacion
	 */
	@SuppressWarnings("unchecked")
	public static Conciliacion buildConciliacionFromConciliacionDTOList (ConciliacionDTOList conciliacionDTOList) {
		Conciliacion conciliacion = null;
		if(conciliacionDTOList != null) {
			conciliacion = new Conciliacion();
			conciliacion.setId(conciliacionDTOList.getFolio());
			conciliacion.setEstatus(EstatusConciliacionBuilder.buildEstatusConciliacionFromEstatusConciliacionDTO(conciliacionDTOList.getEstatus()));
			conciliacion.setEntidad(EntidadBuilder.buildEntidadFromEntidadDTO(conciliacionDTOList.getEntidad()));
			conciliacion.setCuenta(CuentaBuilder.buildCuentaFromCuentaDTO(conciliacionDTOList.getCuenta()));
			conciliacion.setReportes((Set<Reporte>) conciliacionDTOList.getReporteProcesosNocturnos());
			conciliacion.setReportes((Set<Reporte>) conciliacionDTOList.getReporteEstadoCuenta());
			conciliacion.setReportes((Set<Reporte>) conciliacionDTOList.getReporteProveedorTransaccional());
			conciliacion.setGlobal((Set<Global>) conciliacionDTOList.getGlobal());
//			conciliacion.getMovi
//			conciliacion.setMovimientosTransito(movimientosTransito);
//			conciliacion.setComisiones(comisiones);
			conciliacion.setCreatedDate(conciliacionDTOList.getCreatedDate());
			conciliacion.setLastModifiedDate(conciliacionDTOList.getLastModifiedDate());
			conciliacion.setCreatedBy(conciliacionDTOList.getCreatedBy());
			conciliacion.setLastModifiedBy(conciliacionDTOList.getLastModifiedBy());
		}
		return conciliacion;
	}

	public static ConciliacionDTOList buildConciliacionDTOListFromConciliacion(Conciliacion conciliacion,
			GlobalDTO buildGlobalDTOFromGlobal, ConciliacionDTOList buildReporteProcesosNocturnosDTOFromReporte,
			ReporteProveedorTransaccionalDTO buildReporteProveedorTransaccionalDTOFromReporte,
			ReporteEstadoCuentaDTO buildReporteEstadoCuentaDTOFromReporte,
			List<DevolucionConDTO> buildReporteEstadoCuentaDTOListFromReporteList,
			List<MovTransitoDTO> buildMovTransitoDTOListFromMovimientoTransitoList,
			List<ComisionesDTO> buildComisionesDTOListFromMovimientoComisionList) {
		// TODO Auto-generated method stub
		return null;
	}

}
