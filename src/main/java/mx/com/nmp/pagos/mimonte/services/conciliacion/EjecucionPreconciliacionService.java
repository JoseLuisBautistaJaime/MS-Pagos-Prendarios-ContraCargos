/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionPreconciliacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 05-Nov-2021 11:03:32 AM
 */
@Service("ejecucionPreconciliacionService")
public interface EjecucionPreconciliacionService {

	/**
	 * Método que regresa una lista de tipo EjecucionPreconciliacionDTO con la
	 * información de la ejecución del proceso de conciliación a partir de un objeto de tipo
	 * FiltroEjecucionPreconciliacionDTO.
	 *
	 * @param filtroEjecucionPreconciliacionDTO
	 * @return
	 */
	public List<EjecucionPreconciliacionDTO> consultarByPropiedades(FiltroEjecucionPreconciliacionDTO filtroEjecucionPreconciliacionDTO);


}