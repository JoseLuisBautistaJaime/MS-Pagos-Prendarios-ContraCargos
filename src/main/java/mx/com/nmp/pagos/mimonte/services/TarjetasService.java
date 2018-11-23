package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;

public interface TarjetasService {
	
	List<TarjetaDTO> getTarjetas(Integer idCliente);
	
	List<TarjetaDTO> getTarjeta(String token);

	TarjeDTO addTarjetas(TarjetaDTO tarjeta);
	
	TarjeDTO updateTarjetas(String idTarjeta);

	TarjetaDTO deleteTarjetas(String idTarjeta);
	
	public int countTarjetasByIdCliente(Integer idCliente);

}
