/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Nombre: CalendarioEjecucionProcesoBuilder Descripcion: Clase de capa de builder que se
 * encarga de convertir diferentes tipos de objetos y entidades relacionadas con
 * la configuración de ejecución de los procesos automatizados
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 17/03/2022 11:48 hrs.
 * @version 0.1
 */
public abstract class CalendarioEjecucionProcesoBuilder {

	private CalendarioEjecucionProcesoBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo List<CalendarioEjecucionProcesoDTO> a partir una lista
	 * de tipo List<CalendarioEjecucionProceso>.
	 *
	 * @param calendarioEjecucionProcesoList
	 * @return List
	 */
	public static List<CalendarioEjecucionProcesoDTO> buildCalendarioEjecucionProcesoDTOListFromCalendarioEjecucionProcesoList(List<CalendarioEjecucionProceso> calendarioEjecucionProcesoList) {
		List<CalendarioEjecucionProcesoDTO> calendarioEjecucionProcesoDTOList = null;
		if (calendarioEjecucionProcesoList != null && !calendarioEjecucionProcesoList.isEmpty()) {
			calendarioEjecucionProcesoDTOList = new ArrayList<>();
			for (CalendarioEjecucionProceso elemento : calendarioEjecucionProcesoList) {
				calendarioEjecucionProcesoDTOList.add(buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProceso(elemento));
			}
		}
		return calendarioEjecucionProcesoDTOList;
	}

	/**
	 * Construye un objeto de tipo CalendarioEjecucionProcesoDTO a partir de una entidad
	 * CalendarioEjecucionProceso
	 *
	 * @param elemento
	 * @return CalendarioEjecucionProcesoDTO
	 */
	public static CalendarioEjecucionProcesoDTO buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProceso(CalendarioEjecucionProceso elemento) {
		CalendarioEjecucionProcesoDTO resultado = null;
		if (elemento != null) {
			resultado = new CalendarioEjecucionProcesoDTO();
			resultado.setId(elemento.getId());
			resultado.setProceso(new ProcesoDTO(elemento.getProceso().getId(),elemento.getProceso().getDescripcionCorta(),elemento.getProceso().getDescripcion()));
			resultado.setConfiguracionAutomatizacion(elemento.getConfiguracion());
			resultado.setReintentos(elemento.getReintentos());
			resultado.setRangoDiasCoberturaMin(elemento.getRangoDiasCoberturaMin());
			resultado.setRangoDiasCoberturaMax(elemento.getRangoDiasCoberturaMin());
			resultado.setActivo(elemento.getActivo());
			resultado.setCreatedBy(elemento.getCreatedBy());
			resultado.setCreatedDate(elemento.getCreatedDate());
			resultado.setLastModifiedBy(elemento.getLastModifiedBy());
			resultado.setLastModifiedDate(elemento.getLastModifiedDate());
			resultado.setCorresponsal(elemento.getProveedor() != null ? elemento.getProveedor().getNombre() : CorresponsalEnum.OPENPAY);
		}
		return resultado;
	}

    /**
     * Construye un objeto de tipo CalendarioEjecucionProcesoDTO a partir de una entidad
     *  CalendarioEjecucionProcesoRequestDTO
     *
     * @param elementoDTO
     * @return CalendarioEjecucionProceso
     */
    public static CalendarioEjecucionProceso buildCalendarioEjecucionProcesoFromCalendarioEjecucionProcesoDTO(CalendarioEjecucionProcesoDTO elementoDTO) {
		CalendarioEjecucionProceso resultado = null;
        if (elementoDTO != null) {
			resultado = new CalendarioEjecucionProceso();
			resultado.setId(elementoDTO.getId());
			resultado.setProceso(new CatalogoProceso(elementoDTO.getProceso().getId()));
			resultado.setConfiguracion(elementoDTO.getConfiguracionAutomatizacion());
			resultado.setReintentos(elementoDTO.getReintentos());
			resultado.setRangoDiasCoberturaMin(elementoDTO.getRangoDiasCoberturaMin());
			resultado.setRangoDiasCoberturaMax(elementoDTO.getRangoDiasCoberturaMax());
			resultado.setActivo(elementoDTO.getActivo());
			resultado.setCreatedBy(elementoDTO.getCreatedBy());
			resultado.setCreatedDate(elementoDTO.getCreatedDate());
			resultado.setLastModifiedBy(elementoDTO.getLastModifiedBy());
			resultado.setLastModifiedDate(elementoDTO.getLastModifiedDate());
			resultado.setProveedor( elementoDTO.getCorresponsal() != null ? new Proveedor(elementoDTO.getCorresponsal()): new Proveedor(CorresponsalEnum.OPENPAY));
        }
        return resultado;
    }


	/**
	 * Construye un objeto de tipo CalendarioEjecucionProcesoDTO a partir de un objeto
	 * de tipo CalendarioEjecucionProcesoRequestDTO.
	 *
	 * @param elementoRequestDTO
	 * @return CalendarioEjecucionProcesoDTO
	 */
	public static CalendarioEjecucionProcesoDTO buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProcesoRequestDTO( CalendarioEjecucionProcesoRequestDTO elementoRequestDTO) {

		CalendarioEjecucionProcesoDTO resultadoDTO = null;
		if (elementoRequestDTO != null) {
			resultadoDTO = new CalendarioEjecucionProcesoDTO();
			resultadoDTO.setId(elementoRequestDTO.getIdCalendario());
			resultadoDTO.setProceso(new ProcesoDTO(elementoRequestDTO.getProceso()));
			resultadoDTO.setConfiguracionAutomatizacion(elementoRequestDTO.getConfiguracionAutomatizacion());
			resultadoDTO.setReintentos(elementoRequestDTO.getReintentos());
			resultadoDTO.setRangoDiasCoberturaMin(elementoRequestDTO.getRangoDiasCoberturaMin());
			resultadoDTO.setRangoDiasCoberturaMax(elementoRequestDTO.getRangoDiasCoberturaMin());
			resultadoDTO.setActivo(elementoRequestDTO.getActivo() != null ? elementoRequestDTO.getActivo() : Boolean.TRUE);
			resultadoDTO.setCreatedBy(null);
			resultadoDTO.setCreatedDate(null);
			resultadoDTO.setLastModifiedBy(null);
			resultadoDTO.setLastModifiedDate(null);
			resultadoDTO.setCorresponsal(elementoRequestDTO.getCorresponsal() != null ? CorresponsalEnum.getByNombre(elementoRequestDTO.getCorresponsal().toUpperCase()) : CorresponsalEnum.OPENPAY);
		}
		return resultadoDTO;
	}

	
}
