/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * @name ReporteBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              Reporte
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 15/05/2019 17:34 hrs.
 * @version 0.1
 */
public abstract class ReporteBuilder {

	private ReporteBuilder() {
		super();
	}

	/**
	 * Construye un obejto de tipo Reporte a partir de un objeto de tipo
	 * SaveEstadoCuentaRequestDTO
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @return
	 */
	public static Reporte buildReporteFromSaveEstadoCuentaRequestDTO(
			final SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO, final String userRequest) {
		Reporte reporte = null;
		if (null != saveEstadoCuentaRequestDTO) {
			reporte = new Reporte();
			reporte.setConciliacion(new Conciliacion((long) saveEstadoCuentaRequestDTO.getFolio()));
			reporte.setCreatedBy(userRequest);
			reporte.setCreatedDate(new Date());
			reporte.setDisponible(true);
			reporte.setFechaDesde(saveEstadoCuentaRequestDTO.getFechaInicial());
			reporte.setFechaHasta(saveEstadoCuentaRequestDTO.getFechaFinal());
			reporte.setId(null);
			reporte.setLastModifiedBy(null);
			reporte.setLastModifiedDate(null);
			reporte.setTipo(TipoReporteEnum.ESTADO_CUENTA);
		}
		return reporte;
	}

}
