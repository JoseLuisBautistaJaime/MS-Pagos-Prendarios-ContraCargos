package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:41 PM
 */
public interface DevolucionesService {

	/**
	 * 
	 * @param folio
	 * @return
	 */
	public List<DevolucionConDTO> consultaDevolucion(Integer folio);
	
	/**
	 * 
	 * @param liquidarDevoluciones
	 * @param usuario
	 * @return
	 */
	public List<DevolucionEntidadDTO> liquidarDevoluciones(LiquidacionMovimientosRequestDTO liquidarDevoluciones, String usuario);
	
	/**
	 * 
	 * @param folios
	 * @param usuario
	 * @return
	 */
	public List<DevolucionEntidadDTO> solicitarDevoluciones(FolioRequestDTO folios, String usuario) throws ConciliacionException;

	/**
	 * 
	 * @param devoluciones
	 * @return
	 */
	public List<DevolucionEntidadDTO> consulta(DevolucionRequestDTO devoluciones);
	
	/**
	 * 
	 * @param solicitar
	 * @param modifiedBy
	 * @return
	 */
	public List<DevolucionEntidadDTO> solicitarDevoluciones(DevolucionesIdsMovimientosDTO solicitar, String modifiedBy);
	
	/**
	 * 
	 * @param marcarDevoluciones
	 * @param createdBy
	 * @return
	 */
	public List<DevolucionConDTO> marcarDevolucion(SolicitarPagosRequestDTO marcarDevoluciones, String createdBy);

	/**
	 * Se encarga de actualizar las devoluciones
	 * @param devolucionUpdtDTO
	 * @param modifiedBy 
	 * @return
	 * @throws ConciliacionException
	 */
	public List<DevolucionEntidadDTO> actualizar(List<DevolucionUpdtDTO> devolucionUpdtDTO, String modifiedBy) throws ConciliacionException;

}