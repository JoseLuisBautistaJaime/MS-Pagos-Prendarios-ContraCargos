package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;

/**
 * Nombre: ClienteService
 * Descripcion: Interfaz que contiene las operaciones encargadas de realizar consultas sobre la entidad Cliente
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 27/11/2018 11:08 hrs.
 * @version 0.1
 */
public interface ClienteService {

	/**
	 * Metodo que regresa un objeto de tipo ClienteDTO con un id especificado como parametro
	 * 
	 * @param Integer idCliente
	 * @return Object ClienteDTO
	 */
	public ClienteDTO getClienteById(Integer idCliente);
	
}
