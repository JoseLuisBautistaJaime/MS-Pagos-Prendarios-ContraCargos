package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaDevolucionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.FormatoReporteEnum;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:41 PM
 */
public interface DevolucionesService {

//	public void finalize() throws Throwable {
//
//	}

//	/**
//	 * 
//	 * @param movimientos
//	 * @param usuario
//	 */
//	public void solicitarDevolucion(List<Long> movimientos, String usuario);
//
//	/**
//	 * 
//	 * @param idConciliacion
//	 */
//	public List<DevolucionDTO> listByConciliacion(Long idConciliacion);
//
//	/**
//	 * 
//	 * @param criterios
//	 * @param page
//	 * @param pageSize
//	 */
//	public List<DevolucionDTO> search(ConsultaDevolucionesRequestDTO criterios, Integer page, Integer pageSize);
//
//	/**
//	 * 
//	 * @param criterios
//	 */
//	public Integer count(ConsultaDevolucionesRequestDTO criterios);
//
//	/**
//	 * 
//	 * @param id
//	 * @param fechaLiquidacion
//	 * @param usuario
//	 */
//	public void actualizar(Long id, Date fechaLiquidacion, String usuario);
//
//	/**
//	 * 
//	 * @param id
//	 * @param usuario
//	 */
//	public void liquidar(Long id, String usuario);
//
//	/**
//	 * 
//	 * @param id
//	 * @param usuario
//	 */
//	public void solicitar(Long id, String usuario);
//
//	/**
//	 * 
//	 * @param idConciliacion
//	 * @param formato
//	 */
//	public void exportarDevoluciones(Long idConciliacion, FormatoReporteEnum formato);
	
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
	public List<DevolucionEntidadDTO> solicitarDevoluciones(FolioRequestDTO folios, String usuario);
	
	/**
	 * 
	 * @param devoluciones
	 * @return
	 */
	public List<DevolucionEntidadDTO> actualizacionDevoluciones(List<DevolucionUpdtDTO> devoluciones);
	
	/**
	 * 
	 * @param devoluciones
	 * @return
	 */
	public List<DevolucionEntidadDTO> consulta(DevolucionRequestDTO devoluciones);
	
	/**
	 * 
	 * @param solicitar
	 * @return
	 */
	public List<DevolucionEntidadDTO>  solicitar(DevolucionesIdsMovimientosDTO solicitar);
	
	/**
	 * 
	 * @param marcarDevoluciones
	 * @param createdBy
	 * @return
	 */
	public List<DevolucionConDTO> marcarDevolucion(SolicitarPagosRequestDTO marcarDevoluciones, String createdBy);
	
}