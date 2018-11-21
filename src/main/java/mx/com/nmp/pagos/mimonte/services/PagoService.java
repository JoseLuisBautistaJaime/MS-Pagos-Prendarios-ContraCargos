package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.PagoDTO;

/**
 * Nombre: PagoService
 * Descripcion: Interfaz que define la operacion encargada de realizar un pago de una o mas partidas/contratos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 20/11/2018 13:45 hrs.
 * @version 0.1
 */
public interface PagoService {

	/**
	 * Metodo que se encarga de registrar un pago
	 * @param pagoDTO
	 * @return Objeto PagoDTO que fue guardado
	 */
	public abstract PagoDTO savePago (PagoDTO pagoDTO);
	
}
