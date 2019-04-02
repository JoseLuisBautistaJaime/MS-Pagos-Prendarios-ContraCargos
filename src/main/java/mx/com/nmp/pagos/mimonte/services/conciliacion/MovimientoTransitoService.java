package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransitoDTO;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:42 PM
 */
public class MovimientoTransitoService {

	public MovimientoTransitoService(){

	}

	/**
	 * 
	 * @param movimiento
	 * @param usuario
	 */
	public void save(MovimientoTransitoDTO movimiento, String usuario){

	}

	/**
	 * 
	 * @param movmientos
	 * @param usuario
	 */
	public void marcarDevolucion(List<Long> movmientos, String usuario){

	}

	/**
	 * 
	 * @param movimientos
	 * @param usuario
	 */
	public void solicitarPagos(List<Long> movimientos, String usuario){

	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public List<MovimientoTransitoDTO> listByConciliacion(Long idConciliacion){
		return null;
	}

}