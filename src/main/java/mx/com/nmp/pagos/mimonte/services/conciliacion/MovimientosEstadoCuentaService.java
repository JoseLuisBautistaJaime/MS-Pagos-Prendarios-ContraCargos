package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:40 PM
 */
public class MovimientosEstadoCuentaService {

	public MovimientosEstadoCuentaService(){

	}


	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoEstadoCuentaDTO> listByDates(Date fechaDesde, Date fechaHasta){
		return null;
	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public Integer countByConciliacion(Long idConciliacion){
		return 0;
	}

	/**
	 * 
	 * @param idConciliacion
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoEstadoCuentaDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoEstadoCuentaDTO> search(ConsultaMovEstadoCuentaRequestDTO criteria, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovEstadoCuentaRequestDTO criterios){
		return 0;
	}

	/**
	 * 
	 * @param movimientos
	 */
	public void saveBatch(List<MovimientoEstadoCuentaBatchDTO> movimientos){

	}

}