/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
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
			List<Reporte> reporte, List<DevolucionConDTO> devolucionConDTOList,
			List<MovTransitoDTO> movTransitoDTOList, List<ComisionesDTO> comisionesDTOList) {
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
					ReporteProcesosNocturnosBuilder.buildReporteProcesosNocturnosDTOSetFromReporteSet(reporte));
			conciliacionDTOList.setReporteProveedorTransaccional(ReporteProveedorTransaccionalBuilder
					.buildReporteProveedorTransaccionalDTOFromReporteList(reporte));
			conciliacionDTOList.setReporteEstadoCuenta(
					ReporteEstadoCuentaBuilder.buildReporteEstadoCuentaDTOFromReporteList(reporte));
			conciliacionDTOList.setGlobal(GlobalBuilder.buildGlobalDTOFromGlobal(conciliacion.getGlobal()));
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

}
