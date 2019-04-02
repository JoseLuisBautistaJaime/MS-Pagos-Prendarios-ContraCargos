package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaDevolucionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.FormatoReporteEnum;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:41 PM
 */
public class DevolucionesService {

	public DevolucionesService(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param movimientos
	 * @param usuario
	 */
	public void solicitarDevolucion(List<Long> movimientos, String usuario){

	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public List<DevolucionDTO> listByConciliacion(Long idConciliacion){
		return null;
	}

	/**
	 * 
	 * @param criterios
	 * @param page
	 * @param pageSize
	 */
	public List<DevolucionDTO> search(ConsultaDevolucionesRequestDTO criterios, Integer page, Integer pageSize){
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaDevolucionesRequestDTO criterios){
		return 0;
	}

	/**
	 * 
	 * @param id
	 * @param fechaLiquidacion
	 * @param usuario
	 */
	public void actualizar(Long id, Date fechaLiquidacion, String usuario){

	}

	/**
	 * 
	 * @param id
	 * @param usuario
	 */
	public void liquidar(Long id, String usuario){

	}

	/**
	 * 
	 * @param id
	 * @param usuario
	 */
	public void solicitar(Long id, String usuario){

	}

	/**
	 * 
	 * @param idConciliacion
	 * @param formato
	 */
	public void exportarDevoluciones(Long idConciliacion, FormatoReporteEnum formato){

	}

}