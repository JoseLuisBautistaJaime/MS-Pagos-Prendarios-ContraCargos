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
	 * Construye un objeto de tipo List<EjecucionConciliacionDTO> a partir un entitie
	 * de tipo List<EjecucionConciliacion>.
	 *
	 * @param ejecucionConciliacionList
	 * @return
	 */
	public static List<EjecucionConciliacionDTO> buildEjecucionConciliacionDTOListFromEjecucionConciliacionList(List<EjecucionConciliacion> ejecucionConciliacionList) {
		List<EjecucionConciliacionDTO> EjecucionConciliacionDTOList = null;
		if (ejecucionConciliacionList != null && !ejecucionConciliacionList.isEmpty()) {
			EjecucionConciliacionDTOList = new ArrayList<>();
			for (EjecucionConciliacion elemento : ejecucionConciliacionList) {
				EjecucionConciliacionDTOList.add(buildEjecucionConciliacionDTOFromEjecucionConciliacion(elemento));
			}
		}
		return EjecucionConciliacionDTOList;
	}

	/**
	 * Construye un objeto de tipo EjecucionConciliacionDTO a partir de una entidad
	 * EjecucionConciliacion
	 *
	 * @param ejecucionConciliacion
	 * @return consultaEjecucionConciliacionDTO
	 */
	public static EjecucionConciliacionDTO buildEjecucionConciliacionDTOFromEjecucionConciliacion(EjecucionConciliacion ejecucionConciliacion) {
		EjecucionConciliacionDTO consultaEjecucionConciliacionDTO = null;
		if (ejecucionConciliacion != null) {
			consultaEjecucionConciliacionDTO = new EjecucionConciliacionDTO();
			consultaEjecucionConciliacionDTO.setId(ejecucionConciliacion.getId());
			consultaEjecucionConciliacionDTO.setConciliacion(new ConciliacionEjecucionDTO(ejecucionConciliacion.getConciliacion().getFolio(),ejecucionConciliacion.getConciliacion().getId()));
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

    /**
     * Construye un objeto de tipo EjecucionConciliacion a partir de una entidad
     *  EjecucionConciliacionDTO
     *
     * @param ejecucionConciliacionDTO
     * @return ejecucionConciliacion
     */
    public static EjecucionConciliacion buildEjecucionConciliacionFromEjecucionConciliacionDTO(EjecucionConciliacionDTO ejecucionConciliacionDTO) {
        EjecucionConciliacion ejecucionConciliacion = null;
        if (ejecucionConciliacionDTO != null) {
            ejecucionConciliacion = new EjecucionConciliacion();
            Conciliacion conciliacion = new Conciliacion();
            conciliacion.setId(ejecucionConciliacionDTO.getConciliacion().getFolio());
            conciliacion.setFolio(ejecucionConciliacionDTO.getConciliacion().getFolioConciliacion());
            ejecucionConciliacion.setId(ejecucionConciliacionDTO.getId());
            ejecucionConciliacion.setConciliacion(conciliacion);
            ejecucionConciliacion.setEstatus(EstatusEjecucionConciliacionBuilder.buildEstatusEjecucionConciliacionFromEstatusEjecucionConciliacionDTO(ejecucionConciliacionDTO.getEstatus()));
            ejecucionConciliacion.setFechaEjecucion(ejecucionConciliacionDTO.getFechaEjecucion());
            ejecucionConciliacion.setFechaPeriodoInicio(ejecucionConciliacionDTO.getFechaPeriodoInicio());
            ejecucionConciliacion.setFechaPeriodoFin(ejecucionConciliacionDTO.getFechaPeriodoFin());
            ejecucionConciliacion.setCreatedBy(ejecucionConciliacionDTO.getCreatedBy());
            ejecucionConciliacion.setCreatedDate(ejecucionConciliacionDTO.getCreatedDate());
            ejecucionConciliacion.setLastModifiedBy(ejecucionConciliacionDTO.getLastModifiedBy());
            ejecucionConciliacion.setLastModifiedDate(ejecucionConciliacionDTO.getLastModifiedDate());
            ejecucionConciliacion.setProveedor( ejecucionConciliacionDTO.getCorresponsal() != null ? new Proveedor(ejecucionConciliacionDTO.getCorresponsal()): new Proveedor(CorresponsalEnum.OPENPAY));
        }
        return ejecucionConciliacion;
    }
	
}
