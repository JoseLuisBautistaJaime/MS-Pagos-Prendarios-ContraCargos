package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.FormatoReporteEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:47 PM
 */
public class LayoutsService {

	public LayoutsService(){

	}

	/**
	 * 
	 * @param idConciliacion
	 * @param tipo
	 */
	public void listByType(Long idConciliacion, TipoLayoutEnum tipo){

	}

	/**
	 * 
	 * @param layout
	 * @param usuario
	 */
	public void save(LayoutDTO layout, String usuario){

	}

	/**
	 * 
	 * @param id
	 * @param usuario
	 */
	public void delete(Long id, String usuario){

	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public void enviarLayoutsPS(Long idConciliacion){

	}

	/**
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @param tipoReporte
	 */
	public byte[] exportarLayout(Long idConciliacion, TipoLayoutEnum tipo, FormatoReporteEnum tipoReporte){
		return null;
	}

}