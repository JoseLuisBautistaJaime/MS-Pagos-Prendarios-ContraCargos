package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:38 PM
 */
public class MovimientosProveedorService {

	public MovimientosProveedorService(){

	}


	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoProveedorDTO> listByDates(Date fechaDesde, Date fechaHasta){
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
	public List<MovimientoProveedorDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoProveedorDTO> search(ConsultaMovimientosProveedorRequestDTO criteria, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovimientosProveedorRequestDTO criterios){
		return 0;
	}

	/**
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoProveedorBatchDTO> lista){

	}

}