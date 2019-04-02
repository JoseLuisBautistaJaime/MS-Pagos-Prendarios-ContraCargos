package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:37 PM
 */
public class MovimientosMidasService {

	public MovimientosMidasService(){

	}


	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoMidasDTO> listByDates(Date fechaDesde, Date fechaHasta){
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
	public List<MovimientoMidasDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoMidasDTO> search(ConsultaMovimientosMidasRequestDTO criteria, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovimientosMidasRequestDTO criterios){
		return 0;
	}

	/**
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoMidasBatchDTO> lista){

	}

}