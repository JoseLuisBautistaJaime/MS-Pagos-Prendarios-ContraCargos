/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.TrazadoEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TrazadoEjecucionConciliacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Nombre: TrazadoEjecucionConciliacionBuilder Descripcion: Clase de capa de builder que se
 * encarga de convertir diferentes tipos de objetos y entidades relacionadas con el trazado de
 * la ejecución del proceso de conciliación
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 09/11/2021 11:48 hrs.
 * @version 0.1
 */
public abstract class TrazadoEjecucionConciliacionBuilder {

	private TrazadoEjecucionConciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo List<ConsultaEjecucionConciliacionDTO> a partir un entitie
	 * de tipo List<EjecucionConciliacion>.
	 *
	 * @param trazadoEjecucionConciliacionList
	 * @return
	 */
	public static List<TrazadoEjecucionConciliacionDTO> buildTrazadoEjecucionConciliacionDTOListFromTrazadoEjecucionConciliacionList(List<TrazadoEjecucionConciliacion> trazadoEjecucionConciliacionList) {
		List<TrazadoEjecucionConciliacionDTO> trazadoEjecucionConciliacionDTOList = null;
		if (trazadoEjecucionConciliacionList != null && !trazadoEjecucionConciliacionList.isEmpty()) {
			trazadoEjecucionConciliacionDTOList = new ArrayList<>();
			for (TrazadoEjecucionConciliacion elemento : trazadoEjecucionConciliacionList) {
				trazadoEjecucionConciliacionDTOList.add(buildTrazadoEjecucionConciliacionDTOFromTrazadoEjecucionConciliacion(elemento));
			}
		}
		return trazadoEjecucionConciliacionDTOList;
	}

	/**
	 * Construye un objeto de tipo TrazadoEjecucionConciliacionDTO a partir de una entidad
	 * TrazadoEjecucionConciliacion
	 *
	 * @param trazadoEjecucionConciliacion
	 * @return trazadoEjecucionConciliacionDTO
	 */
	public static TrazadoEjecucionConciliacionDTO buildTrazadoEjecucionConciliacionDTOFromTrazadoEjecucionConciliacion(TrazadoEjecucionConciliacion trazadoEjecucionConciliacion) {
		TrazadoEjecucionConciliacionDTO trazadoEjecucionConciliacionDTO = null;
		if (trazadoEjecucionConciliacion != null) {
			trazadoEjecucionConciliacionDTO = new TrazadoEjecucionConciliacionDTO();
			trazadoEjecucionConciliacionDTO.setId(trazadoEjecucionConciliacion.getId());
			trazadoEjecucionConciliacionDTO.setEjecucionConciliacion(EjecucionConciliacionBuilder.buildEjecucionConciliacionDTOFromEjecucionConciliacion(trazadoEjecucionConciliacion.getEjecucionConciliacion()));
			trazadoEjecucionConciliacionDTO.setEstatusEjecucion(EstatusEjecucionConciliacionBuilder.buildEstatusEjecucionConciliacionDTOFromEstatusEjecucionConciliacion(trazadoEjecucionConciliacion.getEstatus()));
			trazadoEjecucionConciliacionDTO.setEstatusDescripcion(trazadoEjecucionConciliacion.getEstatusDescripcion());
			trazadoEjecucionConciliacionDTO.setFechaFin(trazadoEjecucionConciliacion.getFechaFin());
			trazadoEjecucionConciliacionDTO.setFechaInicio(trazadoEjecucionConciliacion.getFechaInicio());
		}
		return trazadoEjecucionConciliacionDTO;
	}
	
}
