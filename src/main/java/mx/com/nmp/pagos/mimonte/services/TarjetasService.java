package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;

public interface TarjetasService {
	
	List<TarjetaDTO> getTarjetas(String idTarjeta);

	List<TarjetaDTO> addTarjetas(TarjetaDTO tarjeta);
	
	List<TarjetaDTO> updateTarjetas(String idTarjeta);

	TarjetaDTO deleteTarjetas(String idTarjeta);
	
	public int countTarjetasByIdCliente(Integer idCliente);

}
