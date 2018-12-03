package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: TarjetasService
 * Descripcion: Interface que define la operacion encargada de obtener los registros de las tarjetas.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 30/11/2018 12:30 Hrs.
 * @version 0.1
 */
public interface TarjetasService {
	
	/**
	 * Método que obtiene información de las tarjetas.
	 * 
	 * @param idCliente.
	 * @return Response.
	 */
	public Response getTarjetasIdCliente(Integer idCliente);
	
	/**
	 * Método que obtiene información de la tarjeta.
	 * 
	 * @param idCliente.
	 * @param token.
	 * @return Response.
	 */
	public Response getTarjetasTokenIdCliente(Integer idCliente ,String token);
	
	/**
	 * Método que obttiene información de la tarjeta.
	 * 
	 * @param token.
	 * @return.
	 */
	public Response getTarjetasToken(String token);

	/**
	 * Método que registra una tarjeta.
	 * 
	 * @param tarjeta
	 * @return Response.
	 */
	public Response addTarjetas(TarjetaDTO tarjeta);
	
	/**
	 * Método que actualiza el alias de una tarjeta.
	 * 
	 * @param token.
	 * @param alias.
	 * @return Response.
	 */
	public Response updateTarjeta(String token, String alias);

	
	/**
	 * Método que elimina la tarjeta.
	 * 
	 * @param token.
	 * @return Response.
	 */
	public Response deleteTarjeta(String token);
	
	public int countTarjetasByIdCliente(Integer idCliente);

	

}
