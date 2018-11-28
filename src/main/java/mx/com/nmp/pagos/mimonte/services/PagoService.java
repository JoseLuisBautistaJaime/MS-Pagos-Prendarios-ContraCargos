package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;

/**
 * Nombre: PagoService
 * Descripcion: Interfaz que define la operacion encargada de realizar un pago de una o mas partidas/contratos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 20/11/2018 13:45 hrs.
 * @version 0.1
 */
public interface PagoService {

	/**
	 * Metodo que se encarga de registrar un pago
	 * 
	 * @param Objeto tipo pagoDTO
	 * @return Objeto PagoRequestDTO que fue guardado
	 */
	public abstract PagoResponseDTO savePago (PagoRequestDTO pagoRequestDTO) throws PagoException;
	
}
