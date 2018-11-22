package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.TransaccionDTO;

/**
 * Nombre: TransaccionService
 * Descripcion: Interfaz que define la operacion encargada de registrar una transaccion.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 21/11/2018 16:53 hrs.
 * @version 0.1
 */
public interface TransaccionService {

	/**
	 * Metodo que se encarga de registrar una transaccion
	 * 
	 * @param transaccionDTO de tipo TransaccionDTO a ser guardado
	 * @return valor de tipo TransaccionDTO que fue guardado
	 */
	public abstract TransaccionDTO saveTransaccion(TransaccionDTO transaccionDTO);
	
}
