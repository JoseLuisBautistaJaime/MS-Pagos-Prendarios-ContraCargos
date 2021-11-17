/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionRequestDTO;
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
	 * Método que regresa una lista de tipo ConsultaEjecucionPreconciliacionDTO con la
	 * información de la ejecución del proceso de conciliación a partir de un objeto de tipo
	 * ConsultaEjecucionConciliacionRequestDTO.
	 *
	 * @param consultaEjecucionPreconciliacionRequestDTO
	 * @return
	 */
	public List<ConsultaEjecucionPreconciliacionDTO> consultarByPropiedades(ConsultaEjecucionPreconciliacionRequestDTO consultaEjecucionPreconciliacionRequestDTO);


}