/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TrazadoEjecucionConciliacion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 03-Nov-2021 17:03:32 AM
 */
@Service("ejecucionConciliacionService")
public interface EjecucionConciliacionService {

	/**
	 * Método que regresa una lista de tipo EjecucionConciliacionDTO con la
	 * información de la ejecución del proceso de conciliación a partir de un objeto de tipo
	 * FiltroEjecucionConciliacionDTO.
	 *
	 * @param filtroEjecucionConciliacionDTO
	 * @return
	 */
	public List<EjecucionConciliacionDTO> consultarByPropiedades(FiltroEjecucionConciliacionDTO filtroEjecucionConciliacionDTO);

	/**
	 * Método que regresa un objeto de tipo EjecucionConciliacionDTO con la
	 * información de la ejecución del proceso de conciliación a partir de su id.
	 *
	 * @param idEjecucionConciliacion
	 * @return
	 */
	public EjecucionConciliacionDTO consultarByIdEjecucion(Long idEjecucionConciliacion);


	/**
	 * Metodo que guarda los datos de una ejecución del proceso de conciliación.
	 */
	public void guardarEjecucionConciliacion(TrazadoEjecucionConciliacion trazadoEjecucionConciliacion, String usuario);

	/**
	 * Se encarga de guardar/actualizar una nueva EjecucionConciliacion
	 * @param ejecucionConciliacion
	 * @param registerBy
	 * @return
	 * @throws ConciliacionException
	 */
	public EjecucionConciliacion save(EjecucionConciliacion ejecucionConciliacion, String registerBy) throws ConciliacionException ;

	/**
	 *  Método que regresa un objeto de tipo EjecucionConciliacion con la
	 *  información de la ejecución del proceso de conciliación a partir del id de la conciliacion.
	 * @return
	 * @throws ConciliacionException
	 */
	public EjecucionConciliacion consultarByIdConciliacion(Long idConciliacion) throws ConciliacionException;


	}