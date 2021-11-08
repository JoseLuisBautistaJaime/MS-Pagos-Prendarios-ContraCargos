/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;

import java.util.*;

/**
 * Nombre: EjecucionConciliacionBuilder Descripcion: Clase de capa de builder que se
 * encarga de convertir diferentes tipos de objetos y entidades relacionadas con
 * la ejecución del proceso de conciliación
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/11/2021 11:48 hrs.
 * @version 0.1
 */
public abstract class EjecucionConciliacionBuilder {

	private EjecucionConciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo List<ConsultaEjecucionConciliacionDTO> a partir un entitie
	 * de tipo List<EjecucionConciliacion>.
	 *
	 * @param ejecucionConciliacionList
	 * @return
	 */
	public static List<ConsultaEjecucionConciliacionDTO> buildConsultaEjecucionConciliacionDTOListFromEjecucionConciliacionList(List<EjecucionConciliacion> ejecucionConciliacionList) {
		List<ConsultaEjecucionConciliacionDTO> ConsultaEjecucionConciliacionDTOList = null;
		if (ejecucionConciliacionList != null && !ejecucionConciliacionList.isEmpty()) {
			ConsultaEjecucionConciliacionDTOList = new ArrayList<>();
			for (EjecucionConciliacion elemento : ejecucionConciliacionList) {
				ConsultaEjecucionConciliacionDTOList.add(buildConsultaEjecucionConciliacionDTOFromEjecucionConciliacion(elemento));
			}
		}
		return ConsultaEjecucionConciliacionDTOList;
	}

	/**
	 * Construye un objeto de tipo ConsultaEjecucionConciliacionDTO a partir de una entidad
	 * EjecucionConciliacion
	 *
	 * @param ejecucionConciliacion
	 * @return consultaEjecucionConciliacionDTO
	 */
	public static ConsultaEjecucionConciliacionDTO buildConsultaEjecucionConciliacionDTOFromEjecucionConciliacion(EjecucionConciliacion ejecucionConciliacion) {
		ConsultaEjecucionConciliacionDTO consultaEjecucionConciliacionDTO = null;
		if (ejecucionConciliacion != null) {
			consultaEjecucionConciliacionDTO = new ConsultaEjecucionConciliacionDTO();
			consultaEjecucionConciliacionDTO.setId(ejecucionConciliacion.getId());
			consultaEjecucionConciliacionDTO.setConciliacion(ConciliacionBuilder.buildConciliacionDTOFromConciliacion(ejecucionConciliacion.getConciliacion()));
			consultaEjecucionConciliacionDTO.setCuenta(CuentaBuilder.buildCuentaDTOFromCuenta(ejecucionConciliacion.getCuenta()));
			consultaEjecucionConciliacionDTO.setEntidad(EntidadBuilder.buildEntidadDTOFromEntidad(ejecucionConciliacion.getEntidad()));
			consultaEjecucionConciliacionDTO.setEstatus(EstatusEjecucionConciliacionBuilder.buildEstatusEjecucionConciliacionDTOFromEstatusEjecucionConciliacion(ejecucionConciliacion.getEstatus()));
			consultaEjecucionConciliacionDTO.setFechaEjecucion(ejecucionConciliacion.getFechaEjecucion());
			consultaEjecucionConciliacionDTO.setFechaPeriodoInicio(ejecucionConciliacion.getFechaPeriodoInicio());
			consultaEjecucionConciliacionDTO.setFechaPeriodoFin(ejecucionConciliacion.getFechaPeriodoFin());
			consultaEjecucionConciliacionDTO.setCreatedBy(ejecucionConciliacion.getCreatedBy());
			consultaEjecucionConciliacionDTO.setCreatedDate(ejecucionConciliacion.getCreatedDate());
			consultaEjecucionConciliacionDTO.setLastModifiedBy(ejecucionConciliacion.getLastModifiedBy());
			consultaEjecucionConciliacionDTO.setLastModifiedDate(ejecucionConciliacion.getLastModifiedDate());
			consultaEjecucionConciliacionDTO.setCorresponsal(ejecucionConciliacion.getProveedor() != null ? ejecucionConciliacion.getProveedor().getNombre() : CorresponsalEnum.OPENPAY);
		}
		return consultaEjecucionConciliacionDTO;
	}
	
}
