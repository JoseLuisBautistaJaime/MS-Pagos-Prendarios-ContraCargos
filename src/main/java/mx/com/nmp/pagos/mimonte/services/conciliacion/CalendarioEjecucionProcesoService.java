/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 30-Nov-2021 11:03:32 AM
 */
@Service("calendarioEjecucionProcesoService")
public interface CalendarioEjecucionProcesoService {

	/**
	 * Método que regresa una lista de tipo CalendarioEjecucionProcesoDTO con la
	 * información de las calendarizaciones de los procesos automatizados a partir de un objeto de tipo
	 * FiltroCalendarioEjecucionProcesoDTO.
	 *
	 * @param filtroDTO
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO> consultarByPropiedades(FiltroCalendarioEjecucionProcesoDTO filtroDTO);



}